package Service;

import Model.User;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import static Utilities.Utility.gson;
import static Utilities.Utility.jdbcTemplate;

public class UserService {

    public static String login(UserLoginModel userLoginModel) {
        String sql = "select * from public.get_user_by_email(:email);";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userLoginModel);
        String userString = jdbcTemplate.queryForObject(sql, params, String.class);
        BaseResponse result = gson.fromJson(userString, BaseResponse.class);

        return userString;
    }
}
