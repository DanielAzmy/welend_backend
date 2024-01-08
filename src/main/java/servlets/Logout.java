package servlets;

import DTO.SessionDTO;
import ResponseModel.BaseResponse;
import Enum.ResponseStatus;
import Service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static Utilities.Utility.*;

@WebServlet(urlPatterns = "/api/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            log.info("Enter Logout function");
            jdbcTemplate = DbConnection.getConnection();
            String requestBody = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));
            SessionDTO request = gson.fromJson(requestBody, SessionDTO.class);

            UserService.logout(request.getToken());

            BaseResponse result = new BaseResponse(ResponseStatus.Success.toString(), "Logout Successfully.", null);
            handleResponse(resp, result);
        } catch (Exception e) {
            log.info("Error in Logout function");
            BaseResponse result = new BaseResponse(ResponseStatus.Error.toString(), e.getMessage(),null);
            handleResponse(resp, result);
        }
    }
}
