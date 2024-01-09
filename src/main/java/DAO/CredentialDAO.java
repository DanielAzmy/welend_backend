package DAO;

import RequestModel.Credential;
import ResponseModel.BaseResponse;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import static Utilities.Utility.*;

public class CredentialDAO {
    public static BaseResponse getCredentialsByName(Credential Credential) {
        String sql = "select get_credentials_by_name(:name);";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(Credential);
        String result = jdbcTemplate.queryForObject(sql, params, String.class);
        return gson.fromJson(result, BaseResponse.class);
    }
}
