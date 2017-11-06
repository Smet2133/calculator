package calc;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CalcServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        if((boolean)request.getSession().getAttribute("authorized") != true){
            response.sendRedirect("/calculator");
        }


        double arg1Value = (request.getParameter("arg1") == null) ? 0 : Double.parseDouble(request.getParameter("arg1"));
        double arg2Value = (request.getParameter("arg2") == null) ? 0 : Double.parseDouble(request.getParameter("arg2"));
        String operationValue = (request.getParameter("operation") == null) ? "+" : request.getParameter("operation");
        double result = 0;
        boolean validResult = false;
        switch (operationValue) {
            case "+": result = arg1Value + arg2Value;
            break;
            case "-": result = arg1Value - arg2Value;
            break;
            case "/": result = arg1Value / arg2Value;
            break;
            case "*": result = arg1Value * arg2Value;
        }


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Calculator</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <p>Calculator</p>\n" +
                "    <form method=\"GET\"\n" +
                "          action=\"Calc.do\">\n" +
                "        Argument 1:\n");

        out.println(" <input type=\"text\" name=\"arg1\" value=\"" + arg1Value + "\"> ");
        out.println("  <br>\n" +
                "        Argument 2:");
        out.println(" <input type=\"text\" name=\"arg2\" value=\"" + arg2Value + "\"> <br>");
        out.println("Operation:\n" +
                "        <select name=\"operation\" value=\"" + operationValue + "\">\n" +
                "            <option value=\"+\" " + ((operationValue.equals("+")) ? "selected" : "") + "> + </option>\n" +
                "            <option value=\"-\" " + ((operationValue.equals("-")) ? "selected" : "") + "> - </option>\n" +
                "            <option value=\"*\" " + ((operationValue.equals("*")) ? "selected" : "") + "> * </option>\n" +
                "            <option value=\"/\" " + ((operationValue.equals("/")) ? "selected" : "") + "> / </option>\n" +
                "        </select>\n" +
                "        <br>");
        out.println("Result:\n" + result +
                "        <br><br>\n" +
                "        <input type=\"SUBMIT\">\n" +
                "    </form>\n" +
                "\n" +
                "\n" +
                      "Authorized:"  + request.getSession().getAttribute("authorized") +
                "<form method=\"POST\"\n" +
                "action=\"Logout.do\">\n" +
                "<input type=\"SUBMIT\" name=\"logout\">\n" +
                "</form>" +
                "</body>\n" +
                "</html>");

    }
}