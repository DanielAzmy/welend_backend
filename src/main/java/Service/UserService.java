package Service;

import DAO.SessionDAO;
import DAO.UserDAO;
import DTO.UserRegisterInputDTO;
import Enum.ResponseStatus;
import Model.Session;
import Model.User;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

import static Utilities.Utility.log;

public class UserService {
    private static final UserDAO userDAO = new UserDAO();

    public static String login(UserLoginModel userLoginModel) throws Exception {
        User user = UserDAO.getUserByEmail(userLoginModel);

        if (user == null) {
            throw new Exception("Invalid Email or Password.");
        }

        if (!BCrypt.checkpw(userLoginModel.getPassword(), user.getPassword())) {
            throw new Exception("Invalid Email or Password.");
        }

        Session session = JWTService.generateToken(user.getId());
        if (session == null) {
            throw new SQLException("Error in session creation.");
        }
        SessionDAO.saveSession(session);
        return session.getToken();
    }

    public static String userRegister(UserRegisterInputDTO userRegisterInputDTO) throws SQLException {
        try {
            log.info("Enter userRegister function...");
            userRegisterInputDTO.setPassword(BCrypt.hashpw(userRegisterInputDTO.getPassword(), BCrypt.gensalt()));

            User user = new User(userRegisterInputDTO.getEmail(),
                    userRegisterInputDTO.getPassword(),
                    userRegisterInputDTO.getNidFrontSide(),
                    userRegisterInputDTO.getnidBackSide(),
                    userRegisterInputDTO.getSelfie()
            );
            if (userDAO.register(user) == 1) {
                throw new SQLException("Something went wrong");
            } else
                return ResponseStatus.Success.toString();

        } catch (SQLException e) {
            throw new SQLException("This Email is already Exist.");
        }
    }
}