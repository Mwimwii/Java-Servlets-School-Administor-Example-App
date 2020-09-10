package kic.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user0
 */
public class LoginController {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String httpMethod = request.getMethod();
        if (httpMethod.equalsIgnoreCase("get")) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        } else if (httpMethod.equalsIgnoreCase("post")) {
            String userName = request.getParameter("userName");
            String secret = request.getParameter("secret");
            if (userName.equals("1234") && secret.equals("1234")) {
                request.getSession().setAttribute("userName", userName);
                response.sendRedirect("dashboard");
            } else {
                response.sendRedirect("login.form");
            }
        } else {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, 
                    "The HTTP method used is not supported!");
        }
    }

}
