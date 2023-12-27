package Service;

<<<<<<< HEAD
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
=======
import DAO.SessionDAO;
import DAO.UserDAO;
import Model.Session;
import Model.User;
import RequestModel.UserLoginModel;

import java.sql.SQLException;

public class UserService {
    public static String login(UserLoginModel userLoginModel) throws SQLException {
        User user = UserDAO.getUserByEmail(userLoginModel);
>>>>>>> b574c25f3ed84f695872f6c75e564ffa1c417f71

        Session session = JWTService.generateToken(user.getId());
        if (session == null) {
            throw new SQLException("Error in session creation.");
        }
        SessionDAO.saveSession(session);
        return session.toString();
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
