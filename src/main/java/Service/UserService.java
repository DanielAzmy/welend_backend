package Service;

import DAO.*;
import DTO.UserRegisterInputDTO;
import Enum.*;
import Model.Session;
import Model.User;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import com.google.gson.internal.LazilyParsedNumber;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static Service.JWTService.checkTokenReturnId;
import static Utilities.Utility.*;

public class UserService {

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
            if (UserDAO.register(user) == 1) {
                throw new SQLException("Something went wrong");
            } else
                return ResponseStatus.Success.toString();

        } catch (SQLException e) {
            throw new SQLException("This Email is already Exist.");
        }
    }

    public static void logout(String token) throws SQLException {
        Long id = checkTokenReturnId(token);
        Session session = compareTokenWithStoredSession(id, token);
        UserDAO.changeSessionStatus(session.getId(), SessionStatus.LOGOUT.toString());
    }

    public static Session compareTokenWithStoredSession(Long id, String token) throws SQLException {
        Session session = UserDAO.getUserSession(id);
        if (session == null || !session.getToken().equals(token)) {
            throw new SQLException("Unauthorized");
        }
        return session;
    }

    public static Session mapToSession(BaseResponse response) {
        if (!response.getStatus().equals("Error")) {
            Session session = new Session();
            Map<String, Object> sessionMap = convertJsonObjectToMap(response.getData());

            Integer id = (Integer) sessionMap.get("id");
            LazilyParsedNumber userId = (LazilyParsedNumber) sessionMap.get("user_id");

            session.setId(Long.valueOf(id));
            session.setUserId(userId.longValue());
            session.setToken((String) sessionMap.get("token"));
            session.setStatus((String) sessionMap.get("status"));

            String creationTime = (String) sessionMap.get("creation_date");
            session.setCreationTime(LocalDateTime.parse(creationTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS")));

            return session;
        } else {
            return null;
        }
    }

    public static User mapToUser(BaseResponse userRow) {
        if (!userRow.getStatus().equals("Error")) {
            User user = new User();
            Map<String, Object> userMap = convertJsonObjectToMap(userRow.getData());

            Integer id = (Integer) userMap.get("id");
            user.setId(Long.valueOf(id));
            user.setEmail((String) userMap.get("email"));
            user.setPassword((String) userMap.get("password"));
            user.setFullName((String) userMap.get("full_name"));
            return user;
        } else {
            return null;
        }
    }
}