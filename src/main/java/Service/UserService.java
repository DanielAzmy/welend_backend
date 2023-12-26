package Service;

import RequestModel.UserLoginModel;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import static Utilities.Utility.*;

public class UserService {

    public static String login(UserLoginModel userLoginModel) {
        String sql = "select * from public.api_list_all_customers(:token, :offset, :limitRows, :filterBy, :filterValue)";

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userLoginModel);

        return jdbcTemplate.queryForObject(sql, params, String.class);
    }
}
