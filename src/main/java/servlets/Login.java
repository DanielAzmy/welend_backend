package servlets;

import RequestModel.UserLoginModel;
import ResponseModel.BaseResponse;
import Service.UserService;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static Utilities.Utility.*;

@WebServlet(urlPatterns = "/api/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            log.info("Enter Login function");
            jdbcTemplate = DbConnection.getConnection();
            String requestBody = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));
            UserLoginModel userLoginModel = gson.fromJson(requestBody, UserLoginModel.class);

            String responseBody = UserService.login(userLoginModel);
            BaseResponse result = gson.fromJson(responseBody, BaseResponse.class);
            handleResponse(resp, result);
        } catch (Exception e) {
            log.info("Enter Login function");
            throw new RuntimeException();
        }
    }
}
