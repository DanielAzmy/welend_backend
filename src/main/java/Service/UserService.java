package Service;

import DAO.UserDAO;
import DTO.UserRegisterInputDTO;
import Model.User;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.sql.SQLException;

import static Utilities.Utility.gson;
import static Utilities.Utility.jdbcTemplate;

public class UserService {
    UserDAO userDAO;

    public static String login(UserLoginModel userLoginModel) {
        String sql = "select * from public.get_user_by_email(:email);";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userLoginModel);
        String userString = jdbcTemplate.queryForObject(sql, params, String.class);
        BaseResponse result = gson.fromJson(userString, BaseResponse.class);

        return userString;
    }
    public void userRegister(UserRegisterInputDTO userRegisterInputDTO) throws SQLException {
        try {
            userRegisterInputDTO.setPassword(BCrypt.hashpw(userRegisterInputDTO.getPassword(), BCrypt.gensalt()));

            User user = new User(userRegisterInputDTO.getEmail(),
                    userRegisterInputDTO.getPassword(),
                    userRegisterInputDTO.getNidFrontSide(),
                    userRegisterInputDTO.getnidBackSide(),
                    userRegisterInputDTO.getSelfie()
            );
//            if (userDAO.)

        } catch (Exception e) {
            throw new SQLException(e);
        }

    }
}
