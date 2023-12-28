package Service;

import DAO.UserDAO;
import DTO.UserRegisterInputDTO;
import Model.User;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import com.sun.mail.iap.Response;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import DAO.SessionDAO;
import Model.Session;
import java.sql.SQLException;
import static Utilities.Utility.gson;
import static Utilities.Utility.jdbcTemplate;
import  Enum.ResponseStatus;

    public class UserService {
    static UserDAO userDAO  = new UserDAO();
        public static String login(UserLoginModel userLoginModel) throws SQLException {
            User user = UserDAO.getUserByEmail(userLoginModel);

            Session session = JWTService.generateToken(user.getId());
            if (session == null) {
                throw new SQLException("Error in session creation.");
            }
            SessionDAO.saveSession(session);
            return session.toString();
        }

        public static String userRegister(UserRegisterInputDTO userRegisterInputDTO) throws SQLException {
            try {
                userRegisterInputDTO.setPassword(BCrypt.hashpw(userRegisterInputDTO.getPassword(), BCrypt.gensalt()));

                User user = new User(userRegisterInputDTO.getEmail(),
                        userRegisterInputDTO.getPassword(),
                        userRegisterInputDTO.getNidFrontSide(),
                        userRegisterInputDTO.getnidBackSide(),
                        userRegisterInputDTO.getSelfie()
                );
                if (userDAO.register(user) == 1)
                {
                    throw new SQLException("something went wrong");
                }else
                        return ResponseStatus.Success.toString();

            } catch (SQLException e) {
                throw new SQLException("this email or username is already taken");
            }

        }
    }

