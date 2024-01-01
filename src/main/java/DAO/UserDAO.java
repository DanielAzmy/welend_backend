package DAO;

import Model.User;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import Enum.ResponseStatus;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import servlets.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        } catch (SQLException e) {
            throw new SQLException("something went wrong");
        } finally {
            con.close();
        }
        return 0;
    }

    public static User getUserByEmail(UserLoginModel userLoginModel) {
        String sql = "select * from public.get_user_by_email(:email);";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userLoginModel);
        String userString = jdbcTemplate.queryForObject(sql, params, String.class);
        BaseResponse data = gson.fromJson(userString, BaseResponse.class);
        return mapToUser(data);
    }

    public static User mapToUser(BaseResponse userRow) {
        if (!userRow.getStatus().equals("Error")) {
            User user = new User();
            JsonArray userData = (JsonArray) userRow.getData();
            JsonObject jsonObject = userData.get(0).getAsJsonObject();

            user.setId(jsonObject.getAsJsonPrimitive("id").getAsLong());
            user.setEmail(jsonObject.getAsJsonPrimitive("email").getAsString());
            user.setPassword(jsonObject.getAsJsonPrimitive("password").getAsString());
            return user;
        } else {
            return null;
        }
    }

    public User getUser(String columnName, T columnValue) throws SQLException {
        try {
            con = DbConnection.getNewConnection();
            String query = "select * from project.users where " + columnName + " = '" + columnValue + "'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            boolean check = false;
            while (rs.next()) {
                check = true;
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNidFrontSide(rs.getString("nid_front_side"));
                user.setNidBackSide(rs.getString("nid_back_side"));
                user.setSelfie(rs.getString("selfie"));
            }
            return check ? user : null;
        } catch (SQLException e) {
            throw new SQLException("something went wrong");
        } finally {
            con.close();
        }
    }
}
