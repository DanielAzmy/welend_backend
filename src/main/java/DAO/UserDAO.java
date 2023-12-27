package DAO;

import Model.User;
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
    }
}
