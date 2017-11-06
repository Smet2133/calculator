package calc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if((boolean)request.getSession().getAttribute("authorized") == true){
            response.sendRedirect("/calculator/Calc.do");
            return;
        }

        if(request.getParameter("login") != null && request.getParameter("password") != null &&
                request.getParameter("login").equals("user") && request.getParameter("password").equals("pass")){
            request.getSession().setAttribute("authorized", Boolean.TRUE);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Calc.do");
            rd.forward(request, response);
        } else {
            response.sendRedirect("/calculator");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
