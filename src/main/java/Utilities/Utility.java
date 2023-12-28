package Utilities;

import RequestModel.RequestInputModel;
import ResponseModel.BaseResponse;
import com.google.gson.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Logger;

public class Utility {
    public static Gson gson;
    public static Logger log = Logger.getLogger(Utility.class.getName());
    public static NamedParameterJdbcTemplate jdbcTemplate;
    public static HttpClient httpClient;
    public static HttpRequest.Builder builder = HttpRequest.newBuilder();

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(BaseResponse.class, new CustomDeserializer())
                .create();
        httpClient = HttpClient.newHttpClient();
    }

    public static void handleResponse(HttpServletResponse response, Object responseData) throws IOException {
        PrintWriter output = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String responseJson = gson.toJson(responseData);
        output.print(responseJson);
        output.flush();
    }

    public static class CustomDeserializer implements JsonDeserializer<BaseResponse> {
        @Override
        public BaseResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatus(jsonObject.get("status").getAsString());
            baseResponse.setMessage(jsonObject.get("message").getAsString());

            if (jsonObject.get("token") != null) {
                baseResponse.setToken(jsonObject.get("token").getAsString());
            }

            JsonElement dataElement = jsonObject.get("data");
            if (dataElement != null && dataElement.isJsonArray()) {
                JsonArray dataArray = dataElement.getAsJsonArray();
                dataArray.forEach(element -> {
                    if (element.isJsonObject()) {
                        JsonObject item = element.getAsJsonObject();
                        if (item.has("id") && item.get("id").isJsonPrimitive()) {
                            JsonPrimitive idPrimitive = item.getAsJsonPrimitive("id");
                            if (idPrimitive.isNumber()) {
                                item.addProperty("id", idPrimitive.getAsNumber().intValue());
                            }
                        }
                    }
                });
                baseResponse.setData(dataArray);
            } else {
                baseResponse.setData(null);
            }

            return baseResponse;
        }
    }

// ? to call the postrequest function
//    RequestInputModel<CredsModel> myRequest = new RequestInputModel<>();
//
//            myRequest.setURL("https://api.doxter.ai/auth/tokenAuth/");
//
//    List<String[]> headers = new ArrayList<>();
//            headers.add(new String[]{"Content-Type", "application/json"});
//            myRequest.setHeaders(headers);
//
//    CredsModel creds = new CredsModel("Oasis-Accelerator", "ucXGUfTjvNPc2VFg");
//            myRequest.setBody(creds);
//
//    String response = postRequest(myRequest);

    public static <T> String postRequest(RequestInputModel<T> requestInput) {
        try {
            String url = requestInput.getURL();

            String body = gson.toJson(requestInput.getBody());

            HttpRequest httpRequest = buildRequest(url, body, requestInput.getHeaders());

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static <T> String getRequest(RequestInputModel<T> requestInput) {
        try {
            String url = requestInput.getURL();

            HttpRequest httpRequest = buildRequest(url, null, requestInput.getHeaders());

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static HttpRequest buildRequest(String url, String body, List<String[]> headers) throws Exception {
        builder = HttpRequest.newBuilder().uri(new URI(url));
        if (body != null) {
            builder.POST(HttpRequest.BodyPublishers.ofString(body));
        } else {
            builder.GET();
        }

        for (String[] header : headers) {
            if (header.length == 2) {
                builder = builder.header(header[0], header[1]);
            }
        }
        return builder.build();
    }
}