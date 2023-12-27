package Service;

import DAO.SessionDAO;
import DAO.UserDAO;
import Model.Session;
import Model.User;
import RequestModel.UserLoginModel;
import Enum.ResponseStatus;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.logging.Level;

import static Utilities.Utility.*;

public class UserService {
    public static String login(UserLoginModel userLoginModel) throws SQLException {
        User user = UserDAO.getUserByEmail(userLoginModel);
        if(user == null || !BCrypt.checkpw(userLoginModel.getPassword(), user.getPassword())){
            throw new SQLException("wrong credentials");
        }

        Session session = JWTService.generateToken(user.getId());
        if (session == null) {
            throw new SQLException("Error in session creation.");
        }

        SessionDAO.saveSession(session);
        return session.getToken();
    }
}
