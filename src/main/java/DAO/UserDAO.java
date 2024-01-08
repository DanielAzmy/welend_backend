package DAO;

import DTO.SessionDTO;
import Model.Session;
import Model.User;
import Enum.SessionStatus;
import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import com.google.gson.internal.LazilyParsedNumber;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import servlets.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static Service.UserService.mapToSession;
import static Service.UserService.mapToUser;
import static Utilities.Utility.*;

public class UserDAO<T> {
    public static Connection con;

    public static int register(User user) throws SQLException {
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
        BaseResponse response = gson.fromJson(userString, BaseResponse.class);
        return mapToUser(response);
    }

    public static Session getUserSession(Long id) throws SQLException {
        try {
            String sql = "select get_user_session(:id);";
            SessionDTO sessionDTO = new SessionDTO(Math.toIntExact(id));
            BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(sessionDTO);
            String sessionString = jdbcTemplate.queryForObject(sql, params, String.class);
            BaseResponse response = gson.fromJson(sessionString, BaseResponse.class);
            return mapToSession(response);
        } catch (Exception e) {
            log.info("Error in getUserSession...");
            throw new SQLException(e.getMessage());
        }
    }

    public static String changeSessionStatus(long id, String status) {
        String sql = "select change_session_status(:id, :status);";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(new Session(id, status));
        return jdbcTemplate.queryForObject(sql, params, String.class);
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
