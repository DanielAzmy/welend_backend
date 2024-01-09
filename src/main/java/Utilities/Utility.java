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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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


    public static Map<String, Object> convertJsonObjectToMap(Object data) {
        JsonArray jsonArray = (JsonArray) data;
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

        Map<String, Object> map = new HashMap<>();
        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (value instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) value;
                if (jsonPrimitive.isNumber()) {
                    map.put(key, jsonPrimitive.getAsNumber());
                } else if (jsonPrimitive.isString()) {
                    map.put(key, jsonPrimitive.getAsString());
                } else if (jsonPrimitive.isBoolean()) {
                    map.put(key, jsonPrimitive.getAsBoolean());
                }
            } else if (value.isJsonObject()) {
                map.put(key, convertJsonObjectToMap(value.getAsJsonObject()));
            }
        }

        return map;
    }

// ? to call the postrequest function
//    RequestInputModel<CredentialsDTO> myRequest = new RequestInputModel<>();
//
//            myRequest.setURL("https://api.doxter.ai/auth/tokenAuth/");
//
//    List<String[]> headers = new ArrayList<>();
//            headers.add(new String[]{"Content-Type", "application/json"});
//            myRequest.setHeaders(headers);
//
//    CredentialsDTO creds = new CredentialsDTO("Oasis-Accelerator", "ucXGUfTjvNPc2VFg");
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