package Service;

import DAO.SessionDAO;
import DAO.UserDAO;
import Model.Session;
import Model.User;
import RequestModel.UserLoginModel;

import java.sql.SQLException;

public class UserService {
    public static String login(UserLoginModel userLoginModel) throws SQLException {
        User user = UserDAO.getUserByEmail(userLoginModel);

        Session session = JWTService.generateToken(user.getId());
        if (session == null) {
            throw new SQLException("Error in session creation.");
        }
        SessionDAO.saveSession(session);
        return session.toString();
    }
}
