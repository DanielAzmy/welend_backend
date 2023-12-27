package DAO;

import Model.Session;
import ResponseModel.BaseResponse;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.Timestamp;

import static Utilities.Utility.gson;
import static Utilities.Utility.jdbcTemplate;

public class SessionDAO {
    public static void saveSession(Session session) {
        String sql = "select * from public.create_new_session(:token, :userId, :creationDate, :status)";

        Timestamp timestamp = Timestamp.valueOf(session.getCreationTime());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token", session.getToken());
        params.addValue("userId", (int) session.getUserId());
        params.addValue("creationDate", timestamp);
        params.addValue("status", session.getStatus());
        String result = jdbcTemplate.queryForObject(sql, params, String.class);

        BaseResponse response = gson.fromJson(result, BaseResponse.class);
    }
}
