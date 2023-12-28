package servlets;

import DTO.UserRegisterInputDTO;
import ResponseModel.BaseResponse;
import Service.UserService;
import Enum.ResponseStatus;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static Utilities.Utility.*;

@WebServlet(urlPatterns = "/api/signup")
public class Signup extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        try(BufferedReader reader = req.getReader()){
            log.info("Enter Signup function");
            jdbcTemplate = DbConnection.getConnection();
            String requestBody = reader.lines().collect(Collectors.joining("\n"));
            log.info(requestBody);
            UserRegisterInputDTO userRegisterInputDTO = gson.fromJson(requestBody, UserRegisterInputDTO.class);
            String responseBody = UserService.userRegister(userRegisterInputDTO);
            BaseResponse result = new BaseResponse(ResponseStatus.Success.toString(), "Login Successfully.", responseBody);

            handleResponse(resp,result);

        } catch (Exception e) {
            log.info("Error in Signup function");
            BaseResponse result = new BaseResponse(ResponseStatus.Error.toString(), e.getMessage(),null);
            handleResponse(resp, result);
        }

    }
}
