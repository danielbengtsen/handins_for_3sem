/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Danie
 */
@WebServlet(urlPatterns = {"/maketable"})
public class MakeTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sTD = "<td>";
        String eTD = "</td>";
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MakeTable</title>");  
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MakeTable at " + request.getContextPath() + "</h1>");
            out.println("<table>");
                out.println("<tr>");
                    out.println("<th>Header</th>");
                    out.println("<th>Value</th>");
                out.println("</tr>");
                out.println("<tr>");
                    out.println("<td>host</td>");
                    out.println(sTD + request.getHeader("host") + eTD);
                out.println("</tr>");
                out.println("<tr>");
                    out.println(sTD + "connection" + eTD);
                    out.println(sTD + request.getHeader("connection") + eTD);
                out.println("</tr>");
                out.println("<tr>");
                    out.println(sTD + "cache-control" + eTD);
                    out.println(sTD + request.getHeader("cache-control") + eTD);
                out.println("</tr>");
                out.println("<tr>");
                    out.println(sTD + "accept" + eTD);
                    out.println(sTD + request.getHeader("accept") + eTD);
                out.println("</tr>");
                out.println("<tr>");
                    out.println(sTD + "user-agent" + eTD);
                    out.println(sTD + request.getHeader("user-agent") + eTD);
                out.println("</tr>");
                out.println("<tr>");
                    out.println(sTD + "accept-encoding" + eTD);
                    out.println(sTD + request.getHeader("accept-encoding") + eTD);
                out.println("</tr>");
                out.println("<tr>");
                    out.println(sTD + "accept-language" + eTD);
                    out.println(sTD + request.getHeader("accept-language") + eTD);
                out.println("</tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
