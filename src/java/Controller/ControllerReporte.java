/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.ReporteDAO;
import Modelo.ReporteFecha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author ASUS X
 */
@WebServlet(name = "ControllerReporte", urlPatterns = {"/ControllerReporte"})
public class ControllerReporte extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerReporte</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerReporte at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
           String action="";
        action=request.getParameter("action");
        switch (action) {
            case "":
                    
                break;
            default:
                throw new AssertionError();
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String action="";
        action=request.getParameter("action");
        switch (action) {
            case "BuscarReporte":
                    
                     Map<String,String> errores=new HashMap();
                    String fechaStringInicio=request.getParameter("fecha_inicio");
                    String fechaStringFin=request.getParameter("fecha_ultimo");
                     if(fechaStringInicio==null || fechaStringInicio.isBlank()){
                        errores.put("fechaString","La fecha inicio es necesario");
                     }
                     if(fechaStringFin == null || fechaStringFin.isBlank()){
                        errores.put("fechaString","La fecha fin es necesario");
                     }
                     if(errores.isEmpty()){
                         SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date fechaInicio =  (Date) formatoFecha.parse(fechaStringInicio);
                            Date fechaFin=(Date) formatoFecha.parse(fechaStringFin);
                            List<ReporteFecha> listaReporte=new ArrayList();
                            ReporteDAO reporteDAO=new ReporteDAO();
                            listaReporte=reporteDAO.guardar(new java.sql.Date(fechaInicio.getTime()),new java.sql.Date(fechaFin.getTime()));
                            request.setAttribute("listaReporte", listaReporte);
                            getServletContext().getRequestDispatcher("/reporteVenta.jsp").forward(request, response);
                        } catch (ParseException ex) {
                             JOptionPane.showMessageDialog(null, ex);
                             getServletContext().getRequestDispatcher("/reporteVenta.jsp").forward(request, response);
                        }
                     
                     }else{
                        request.setAttribute("errores", errores);
                         getServletContext().getRequestDispatcher("/reporteVenta.jsp").forward(request, response);
                     }
                break;
            default:
                throw new AssertionError();
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
