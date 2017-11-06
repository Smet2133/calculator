package calc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("authorized") != null && (boolean)request.getSession().getAttribute("authorized") == true){
            response.sendRedirect("/calculator/Calc.do");
            return;
        }

        if(request.getParameter("login") != null && request.getParameter("password") != null &&
                request.getParameter("login").equals("user") && request.getParameter("password").equals("pass")){
            request.getSession().setAttribute("authorized", Boolean.TRUE);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Calc.do");
            rd.forward(request, response);
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Login</title>\n" +
                    "</head>\n" +
                    "<body>\n" + "<p> Please insert your login/password </p>" +
                    "    <form method=\"POST\"\n" +
                    "      action=\"Authorization.do\">\n" +
                    "    Login:\n" +
                    "    <input type=\"text\" name=\"login\">\n" +
                    "    <br>\n" +
                    "    Password:\n" +
                    "    <input type=\"text\" name=\"password\">\n" +
                    "    <br>\n" +
                    "    <input type=\"SUBMIT\">\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>"
            );

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
