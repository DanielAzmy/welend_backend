package DAO;

import Model.User;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import servlets.DbConnection;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

<<<<<<< HEAD
import java.sql.ResultSet;
import java.util.ArrayList;

import static Utilities.Utility.gson;
import static Utilities.Utility.jdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static Utilities.Utility.gson;
import static Utilities.Utility.jdbcTemplate;

public class UserDAO<T> {
    public static Connection con;

    public int register(User user) throws SQLException {
        try {
            con = DbConnection.getNewConnection();
            String query = "INSERT INTO public.user (email, password, nid_front_side, nid_back_side, selfie)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNidFrontSide());
            ps.setString(4, user.getNidBackSide());
            ps.setString(5, user.getSelfie());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new SQLException("something went wrong");
        }finally {
            con.close();
        }
        return 0;
    }


        public static User getUserByEmail (UserLoginModel userLoginModel){
            String sql = "select * from public.get_user_by_email(:email);";
            BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userLoginModel);
            String userString = jdbcTemplate.queryForObject(sql, params, String.class);
            Object data = gson.fromJson(userString, Object.class);
            return mapToUser(data);
        }

    public User getUser(String columnName , T columnValue) throws SQLException{
        try{
            con = DbConnection.getNewConnection();
            String query = "select * from project.users where " + columnName + " = '" + columnValue+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            boolean check = false;
            while (rs.next()){
                check = true;
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNidFrontSide(rs.getString("nid_front_side"));
                user.setNidBackSide(rs.getString("nid_back_side"));
                user.setSelfie(rs.getString("selfie"));
            }
            return check ? user : null;
        }catch (SQLException e){
            throw new SQLException("something went wrong");
        }finally {
            con.close();
=======


public class UserDAO {
    public static User getUserByEmail(UserLoginModel userLoginModel) {
        String sql = "select * from public.get_user_by_email(:email);";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userLoginModel);
        String userString = jdbcTemplate.queryForObject(sql, params, String.class);
        BaseResponse response = gson.fromJson(userString, BaseResponse.class);
        return mapToUser(response.getData());
    }

    public static User mapToUser(Object userRow) {
        if (userRow != null) {
            User user = new User();
            JsonArray userData = (JsonArray) userRow;
            JsonObject jsonObject = userData.get(0).getAsJsonObject();

            user.setId(jsonObject.getAsJsonPrimitive("id").getAsLong());
            user.setEmail(jsonObject.getAsJsonPrimitive("email").getAsString());
            user.setPassword(jsonObject.getAsJsonPrimitive("password").getAsString());
            return user;
        } else {
            return null;
>>>>>>> 6c3c7ebd6ba6cce038cc47cb9065fe6702f1b1bc
        }
    }

        public static User mapToUser (Object userRow){
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
        }

}

