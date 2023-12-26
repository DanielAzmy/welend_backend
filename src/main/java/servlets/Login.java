package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static Utilities.Utility.log;
@WebServlet(urlPatterns = "/api/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            log.info("Enter Login function");

        } catch (Exception e) {
            log.info("Enter Login function");
            throw new RuntimeException();
        }
    }
}
