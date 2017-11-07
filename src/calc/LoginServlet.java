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
                    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"loginStyle.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form method=\"POST\"\n" +
                    "      action=\"Authorization.do\">\n" +
                    "\n" +
                    "    <div class=\"imgcontainer\">\n" +
                    "        <img src=\"img.png\" alt=\"Avatar\" class=\"avatar\">\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div class=\"container\">\n" +
                    "        <label><b>Username</b></label>\n" +
                    "        <input type=\"text\" placeholder=\"Enter Username\" name=\"login\" required>\n" +
                    "\n" +
                    "        <label><b>Password</b></label>\n" +
                    "        <input type=\"password\" placeholder=\"Enter Password\" name=\"password\" required>\n" +
                    "\n" +
                    "        <button type=\"submit\">Login</button>\n" +
                    "    </div>\n" +
                    "\n" +
                    "\n" +
                    "    <!--\n" +
                    "    Login:\n" +
                    "    <input type=\"text\" name=\"login\">\n" +
                    "    <br>\n" +
                    "    Password:\n" +
                    "    <input type=\"text\" name=\"password\">\n" +
                    "    <br>\n" +
                    "    <input type=\"SUBMIT\">\n" +
                    "    -->\n" +
                    "\n" +
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
