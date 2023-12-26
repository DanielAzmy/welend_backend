package Utilities;

import ResponseModel.BaseResponse;
import com.google.gson.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Utility {
    public static Gson gson;
    public static Logger log = Logger.getLogger(Utility.class.getName());
    public static NamedParameterJdbcTemplate jdbcTemplate;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(BaseResponse.class, new CustomDeserializer())
                .create();

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
}