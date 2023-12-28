package DAO;

import Model.Session;
import ResponseModel.BaseResponse;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Timestamp;

import static Utilities.Utility.*;

public class SessionDAO {
    public static void saveSession(Session session) {
        try {
            String sql = "select * from public.create_new_session(:token, :userId, :creationTime, :status)";

            Timestamp timestamp = Timestamp.valueOf(session.getCreationTime());

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("token", session.getToken());
            params.addValue("userId", (int) session.getUserId());
            params.addValue("creationTime", timestamp);
            params.addValue("status", session.getStatus());
            String result = jdbcTemplate.queryForObject(sql, params, String.class);

            BaseResponse response = gson.fromJson(result, BaseResponse.class);
        } catch (Exception e) {
            log.info("Error in Saving Session function...");
            throw new RuntimeException();
        }
    }
}
