package calc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Properties properties = Utilities.getProperties();
        String fileString;


        try {
            DriverManager.getConnection("jdbc:h2:~myDB", "test", "test");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(isAuthorized(request)){
            //redirect
            response.sendRedirect("/calculator/Calc.do");
        } else {
            if(request.getParameter("login") == null || request.getParameter("password") == null){
                //first time
                //show plain index.html
                printResponse(response, "");
            } else {
                //not first time
                if (loginApproved(request.getParameter("login"), request.getParameter("password"))) {
                    request.getSession().setAttribute("login", request.getParameter("login"));
                    //redirect
                    response.sendRedirect("/calculator/Calc.do");
                } else {
                    //add info to session to notify
                    //show index.html
                    printResponse(response, "Login or password is incorrect");
                }
            }

        }
    }

    private boolean loginApproved(String login, String password) {
        if(login.equals("user") && password.equals("pass"))
            return true;
        else
            return false;
    }

    private void printResponse(HttpServletResponse response, String notification) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fileString;
        fileString = Utilities.inputStreamToString(Utilities.inputStreamResources("html/index.html"));
        fileString = fileString.replaceAll("\\$\\{notification}", notification);
        out.println(fileString);
        out.close();
    }

    private boolean isAuthorized(HttpServletRequest request) {
        return request.getSession().getAttribute("login") != null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
