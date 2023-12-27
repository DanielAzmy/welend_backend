package DAO;

import Model.User;
<<<<<<< HEAD
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import servlets.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO<T> {
    public static Connection con;

    public int register(User user) throws SQLException{
        try {
                con = DbConnection.getNewConnection();
            String query = "INSERT INTO public.user (email, password, nid_front_side, nid_back_side, selfie)"
                         + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString (1,user.getEmail());
            ps.setString (2,user.getPassword());
            ps.setByte   (3,user.getNidFrontSide());
            ps.setByte   (4,user.getNidBackSide());
            ps.setByte   (5,user.getSelfie());
            if (ps.executeUpdate()==1){
                User userWithId = getUser
            }

        } catch (Exception e) {

        }
=======
import RequestModel.UserLoginModel;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.util.ArrayList;

import static Utilities.Utility.gson;
import static Utilities.Utility.jdbcTemplate;

public class UserDAO {
    public static User getUserByEmail(UserLoginModel userLoginModel) {
        String sql = "select * from public.get_user_by_email(:email);";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userLoginModel);
        String userString = jdbcTemplate.queryForObject(sql, params, String.class);
        Object data = gson.fromJson(userString, Object.class);
        return mapToUser(data);
    }

    public static User mapToUser(Object userRow) {
        User user = new User();
        LinkedTreeMap userData = (LinkedTreeMap) ((ArrayList<?>) userRow).get(0);
        long id = Math.round((Double) userData.get("id"));
        user.setId(id);
        user.setEmail((String) userData.get("email"));
        user.setPassword((String) userData.get("password"));
        user.setArea((String) userData.get("area"));
        user.setBirthDate((String) userData.get("birth_date"));
        user.setFullName((String) userData.get("full_name"));
        user.setFirstName((String) userData.get("first_name"));
        user.setGovernorate((String) userData.get("governorate"));
        user.setNidSerialNumber((String) userData.get("nid_serial_number"));
        user.setGender((String) userData.get("gender"));
        return user;
>>>>>>> b574c25f3ed84f695872f6c75e564ffa1c417f71
    }
}
