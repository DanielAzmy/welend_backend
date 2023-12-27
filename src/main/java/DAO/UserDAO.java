package DAO;

import Model.User;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import static Utilities.Utility.gson;
import static Utilities.Utility.jdbcTemplate;

public class UserDAO {
    public static User getUserByEmail(UserLoginModel userLoginModel) {
        String sql = "select * from public.get_user_by_email(:email);";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userLoginModel);
        String userString = jdbcTemplate.queryForObject(sql, params, String.class);
        BaseResponse response = gson.fromJson(userString, BaseResponse.class);
        return mapToUser(response.getData());
    }

    public static User mapToUser(Object userRow) {
        if (userRow != null) {
            User user = new User();
            JsonArray userData = (JsonArray) userRow;
            JsonObject jsonObject = userData.get(0).getAsJsonObject();

            user.setId(jsonObject.getAsJsonPrimitive("id").getAsLong());
            user.setEmail(jsonObject.getAsJsonPrimitive("email").getAsString());
            user.setPassword(jsonObject.getAsJsonPrimitive("password").getAsString());
            return user;
        } else {
            return null;
        }
    }
}
