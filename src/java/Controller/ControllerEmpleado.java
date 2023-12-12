/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import static Controller.GestionPedido.esEntero;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
@WebServlet(name = "ControllerEmpleado", urlPatterns = {"/ControllerEmpleado"})
public class ControllerEmpleado extends HttpServlet {

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
            out.println("<title>Servlet ControllerEmpleado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerEmpleado at " + request.getContextPath() + "</h1>");
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
         String action="";
        action=request.getParameter("action");
        switch (action) {
            case "listaClientes":
                        listarClientes(request,response);
                break;
            case "update":
                           String id_empleado=request.getParameter("idCliente");
                           Empleado empleado=new Empleado();
                           EmpleadoDAO empleadoDAO=new EmpleadoDAO();
                           empleado=empleadoDAO.BuscarPorId(Integer.parseInt(id_empleado));
                           List<Empleado> listaEmpleados =empleadoDAO.listar(); 
                           request.setAttribute("empleado", empleado);
                           request.setAttribute("ListaEmpleados", listaEmpleados);
                            getServletContext().getRequestDispatcher("/Empleado.jsp").forward(request, response);
                break;
            case "delete":
                           String id=request.getParameter("idCliente");
                           empleadoDAO=new EmpleadoDAO();
                           empleadoDAO.cambiarEstado(Integer.parseInt(id));
                           listaEmpleados =empleadoDAO.listar(); 
                           request.setAttribute("ListaEmpleados", listaEmpleados);
                           getServletContext().getRequestDispatcher("/Empleado.jsp").forward(request, response);
                    break;
            case "salir":
                             // Obtiene la sesión actual o crea una nueva si no existe
                                HttpSession session = request.getSession(false);

                                if (session != null) {
                                    // Invalida la sesión (cierra sesión)
                                    session.invalidate();
                                }
                                // Redirecciona a la página de inicio de sesión u otra página
                                response.sendRedirect("Login.jsp");
                            
                    break;
            default:
                throw new AssertionError();
        }
        
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
        
   
        String action="";
        action=request.getParameter("action");

        switch (action) {
            case "loguearse":
                        Loguearse(request,response);
                break;
            case "Agregar":
            {
                try {
                    agregarEmpleado(request,response);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ControllerEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
  
            
            case "Modificar":
                      ModificarEmpleado(request, response);
            default:
                throw new AssertionError();
        
        }
        
        
    }
    
     protected void ModificarEmpleado(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
        String id_cliente=req.getParameter("id_cliente");
        String nombre=req.getParameter("nombre");
        String apellido=req.getParameter("apellido");
        String telefono=req.getParameter("telefono");
        String correo=req.getParameter("correo");
        String nombre_sesion=req.getParameter("NombreSesion");
        String clave=req.getParameter("Clave");
        String rol=req.getParameter("rol");
        String estado=req.getParameter("estado");
        Map<String,String> errores = new HashMap();
        if( nombre==null || nombre.isBlank()){
            errores.put("nombre", "El nombre es requerido");
        }
        if( apellido==null || apellido.isBlank()){
            errores.put("apellido", "El apellido");
        }
        if( telefono==null || telefono.isBlank()){
            errores.put("telefono", "El telefono es requerido");
        }
        if( correo==null || correo.isBlank()){
            errores.put("correo", "El correo es requerido");
        }
        if( nombre_sesion==null || nombre_sesion.isBlank()){
            errores.put("nombre_sesion", "El nombre se sesion es necesario");
        }
         if( clave==null || clave.isBlank()){
            errores.put("clave", "La clave es necesario");
        }
        if(rol==null || rol.isBlank()){
            errores.put("rol", "es necesario un rol");
        }
        if(estado==null || estado.isBlank()){
            errores.put("estado", "El estado es necesario");
        }
        if(!(esEntero(clave))){
            errores.put("rolTipo", "El rol debe ser tipo entero");
        }
        if(id_cliente==null || id_cliente.isBlank()){
            errores.put("id_producto", "El id_cliente es necesario");
        }
        if(!(esEntero(id_cliente))){
            errores.put("id_clienteTipo", "El idCliente es entero");
        }
        if(errores.isEmpty()){
            Cliente cliente=new Cliente();
             Empleado empleado=new Empleado();
            empleado.setId_empleado(Integer.parseInt(id_cliente));
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setCorreo(correo);
            empleado.setTelefono(telefono);
            empleado.setNombre_Sesion(nombre_sesion);
            empleado.setClave(clave);
            empleado.setRol(Integer.parseInt(rol));
            empleado.setEstado(estado);
            EmpleadoDAO empleadoDAO=new EmpleadoDAO();
            empleadoDAO.agregarEmpleado(empleado);
            
            
            listarClientes(req, res);
            
        }else{
            EmpleadoDAO empleadoDAO=new EmpleadoDAO();
            List<Empleado> listaEmpleados =empleadoDAO.listar();
            req.setAttribute("ListaEmpleados", listaEmpleados);
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/Cliente.jsp").forward(req, res);
        }
    }
    protected void agregarEmpleado(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException, NoSuchAlgorithmException{
        String nombre=req.getParameter("nombre");
        String apellido=req.getParameter("apellido");
        String telefono=req.getParameter("telefono");
        String correo=req.getParameter("correo");
        String nombre_sesion=req.getParameter("NombreSesion");
        String clave=req.getParameter("Clave");
        String rol=req.getParameter("rol");
        String estado=req.getParameter("estado");
        Map<String,String> errores = new HashMap();
        if( nombre==null || nombre.isBlank()){
            errores.put("nombre", "El nombre es requerido");
        }
        if( apellido==null || apellido.isBlank()){
            errores.put("apellido", "El apellido");
        }
        if( telefono==null || telefono.isBlank()){
            errores.put("telefono", "El telefono es requerido");
        }
        if( correo==null || correo.isBlank()){
            errores.put("correo", "El correo es requerido");
        }
        if( nombre_sesion==null || nombre_sesion.isBlank()){
            errores.put("nombre_sesion", "El nombre se sesion es necesario");
        }
         if( clave==null || clave.isBlank()){
            errores.put("clave", "La clave es necesario");
        }
        if(rol==null || rol.isBlank()){
            errores.put("rol", "es necesario un rol");
        }
        if(estado==null || estado.isBlank()){
            errores.put("estado", "El estado es necesario");
        }
        if(!(esEntero(clave))){
            errores.put("rolTipo", "El rol debe ser tipo entero");
        }
        if(errores.isEmpty()){
            
            Empleado empleado=new Empleado();
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setCorreo(correo);
            empleado.setTelefono(telefono);
            empleado.setNombre_Sesion(nombre_sesion);
            
            empleado.setClave(clave);
            empleado.setRol(Integer.parseInt(rol));
            empleado.setEstado(estado);
            EmpleadoDAO empleadoDAO=new EmpleadoDAO();
            empleadoDAO.agregarEmpleado(empleado);
                empleadoDAO=new EmpleadoDAO();
                List<Empleado> listaEmpleado = empleadoDAO.listar();
                req.setAttribute("ListaEmpleados", listaEmpleado);
                getServletContext().getRequestDispatcher("/Empleado.jsp").forward(req, res);
            }else{
                EmpleadoDAO empleadoDAO=new EmpleadoDAO();
                List<Empleado> listaEmpleado = empleadoDAO.listar();
                req.setAttribute("ListaEmpleados", listaEmpleado);
                 req.setAttribute("errores", errores);
                getServletContext().getRequestDispatcher("/Empleado.jsp").forward(req, res);
           }
    }
    protected void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          RequestDispatcher dispacher=null;
          List<Empleado> empleados=new ArrayList(); 
          EmpleadoDAO empleadoDAO=new EmpleadoDAO();
          empleados=empleadoDAO.listar();
          request.setAttribute("ListaEmpleados", empleados);
          dispacher=request.getRequestDispatcher("Empleado.jsp");   
          dispacher.forward(request, response);
    }
    
     protected void Loguearse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispacher=null;
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String recuerdame = request.getParameter("recuerdame");
         Map<String,String> errores = new HashMap();
        if(usuario==null || usuario.isEmpty()){
            errores.put("usuario", "El usuario es requerido");
        }
        if(usuario==null || usuario.isEmpty()){
            errores.put("clave", "La clave es requerido");
        }
        if(errores.isEmpty()){
            
            //    String hashedPassword=hashPassword(clave);
                
               Empleado empleado=new Empleado();
               EmpleadoDAO empleadoDAO=new EmpleadoDAO();
               empleado=empleadoDAO.loguinEmpleado(usuario, clave);
               if( empleado.getId_empleado()>0){
                   if(recuerdame != null && recuerdame.equals("on")){
                     Cookie cookie = new Cookie("usuario_recuerdame", usuario);
                    // Establecer la duración de la cookie (por ejemplo, una semana)
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    response.addCookie(cookie);
                    HttpSession session = request.getSession();
                    session.setAttribute("sesion", empleado);
                    dispacher=request.getRequestDispatcher("index.jsp");   
                     } 
                        else{
                        HttpSession session = request.getSession();
                        session.setAttribute("sesion", empleado);
                        dispacher=request.getRequestDispatcher("index.jsp");   
                  }
                }else{
                   request.setAttribute("error", "Usuario no encontrado");
                   dispacher=request.getRequestDispatcher("Login.jsp");   
               }
                
           
            
        }else{
             request.setAttribute("errores", errores);
             dispacher=request.getRequestDispatcher("Login.jsp");   
        }
          dispacher.forward(request, response);
    }

 /* public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Crear una instancia de MessageDigest con el algoritmo SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // Obtener el hash de la contraseña como un array de bytes
        byte[] encodedHash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        // Convertir el array de bytes a una representación hexadecimal
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
     }
    */
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
