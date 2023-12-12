/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;


/**
 *
 * @author ASUS X
 */
@WebServlet(name = "GestionPedido", urlPatterns = {"/GestionPedido"})
public class GestionPedido extends HttpServlet {

  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispacher=null;
        String action="";
        action=request.getParameter("action");

        switch (action) {
            case "listaClientes":
                        List<Cliente> listaClientes = obtenerListaDatos();
                        request.setAttribute("listaClientes", listaClientes);
                        dispacher=request.getRequestDispatcher("Cliente.jsp");   
                break;
            case "delete":
                        String id=request.getParameter("idCliente");
                        if(id!=null){
                           int id_cliente=Integer.parseInt(id);
                            if(id_cliente!=0){
                                    ClienteDAO clienteDAO=new ClienteDAO();
                                    clienteDAO.cambiarEstado(id_cliente);
                                    listaClientes = obtenerListaDatos();
                                    request.setAttribute("listaClientes", listaClientes);
                                    dispacher=request.getRequestDispatcher("Cliente.jsp");   
                            }else{
                                    listaClientes = obtenerListaDatos();
                                    request.setAttribute("listaClientes", listaClientes);
                                    dispacher=request.getRequestDispatcher("Cliente.jsp");   
                            }
                        }else{
                                    listaClientes = obtenerListaDatos();
                                    request.setAttribute("listaClientes", listaClientes);
                                    dispacher=request.getRequestDispatcher("Cliente.jsp");   
                        } 
                break;
            case "update":
                        id=request.getParameter("idCliente");
                        if(id!=null){
                           int id_cliente=Integer.parseInt(id);
                            if(id_cliente!=0){
                                    ClienteDAO clienteDAO=new ClienteDAO();
                                    clienteDAO.cambiarEstado(id_cliente);
                                    listaClientes = obtenerListaDatos();
                                    Cliente cliente=new Cliente();
                                    cliente=clienteDAO.BuscarPorId(id_cliente);
                                    request.setAttribute("cliente", cliente);
                                    request.setAttribute("listaClientes", listaClientes);
                                    dispacher=request.getRequestDispatcher("Cliente.jsp");   
                            }else{
                                    listaClientes = obtenerListaDatos();
                                    request.setAttribute("listaClientes", listaClientes);
                                    dispacher=request.getRequestDispatcher("Cliente.jsp");   
                            }
                        }else{
                                    listaClientes = obtenerListaDatos();
                                    request.setAttribute("listaClientes", listaClientes);
                                    dispacher=request.getRequestDispatcher("Cliente.jsp");   
                        }
                        
                break;
            default:
                throw new AssertionError();
        }
        
         dispacher.forward(request, response);
    }
   
    private List<Cliente> obtenerListaDatos() {
        // Lógica para obtener la lista de datos (puedes adaptar esto según tus necesidades)
        List<Cliente> listaDatos = new ArrayList();
        
        ClienteDAO clienteDAO=new ClienteDAO();
        listaDatos=clienteDAO.listar();
        return listaDatos;
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispacher=null;
        String action="";
        action=request.getParameter("action");
        switch (action) {
             case "Agregar":
                    agregarCliente(request, response);
                break;
             case "Modificar":
                    ModificarCliente(request, response);
                 break;
            default:
                throw new AssertionError();
        }
       
    }

    protected void agregarCliente(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
        String nombre=req.getParameter("nombre");
        String apellido=req.getParameter("apellido");
        String telefono=req.getParameter("telefono");
        String correo=req.getParameter("correo");
        String DNI=req.getParameter("DNI");
        String volver=req.getParameter("regresar");
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
        if( DNI==null || DNI.isBlank()){
            errores.put("DNI", "El DNI es requerido");
        }
        if( DNI!=null || !(DNI.isBlank())){
            if(DNI.length()!=8){
              errores.put("DNINoTamaño", "El DNI es debe ser de 8 caracteres"); 
            }
            if(!(esEntero(DNI))){
              errores.put("DNINoEntero", "El DNI debe ser solo numeros");
            }
        }
        
        if(errores.isEmpty()){
            Cliente cliente=new Cliente();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setCorreo(correo);
            cliente.setDNI(DNI);
            cliente.setTelefono(telefono);
            ClienteDAO clienteDAO=new ClienteDAO();
            clienteDAO.agregarCliente(cliente);
            if(volver.equals("")){
                List<Cliente> listaClientes = obtenerListaDatos();
                req.setAttribute("listaClientes", listaClientes);
                getServletContext().getRequestDispatcher("/Cliente.jsp").forward(req, res);
            }else{
                Cliente clienteNuevo=new Cliente();
                clienteNuevo=clienteDAO.ObtenerCliente();
                String precioTotal=req.getParameter("precioTotal");
                if(precioTotal!=null){
                        req.setAttribute("totalPagar", Double.valueOf(precioTotal));
                        req.setAttribute("clienteEncontrado", clienteNuevo);
                        getServletContext().getRequestDispatcher("/generarPago.jsp").forward(req, res);
                
                }else{
                        getServletContext().getRequestDispatcher("/Cliente.jsp").forward(req, res);
                     }
        
            }
            
            
            
        }else{
            List<Cliente> listaClientes = obtenerListaDatos();
            req.setAttribute("listaClientes", listaClientes);
             req.setAttribute("errores", errores);
             getServletContext().getRequestDispatcher("/Cliente.jsp").forward(req, res);
        }
    }
    protected void ModificarCliente(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
        String String_id=req.getParameter("id_cliente");
        String nombre=req.getParameter("nombre");
        String apellido=req.getParameter("apellido");
        String telefono=req.getParameter("telefono");
        String correo=req.getParameter("correo");
        String DNI=req.getParameter("DNI");
        Map<String,String> errores = new HashMap();
        if( String_id==null || String_id.isBlank()){
            errores.put("id_cliente", "El id_cliente es requerido");
        }
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
        if( correo==null || correo.isBlank()){
            errores.put("DNI", "El DNI es requerido");
        }
        if( correo!=null || !(correo.isBlank())){
            if(DNI.length()!=8){
              errores.put("DNINoTamaño", "El DNI es debe ser de 8 caracteres"); 
            }
            if(!(esEntero(DNI))){
              errores.put("DNINoEntero", "El DNI debe ser solo numeros");
            }
        }
        if(errores.isEmpty()){
            Cliente cliente=new Cliente();
            int id_cliente=Integer.parseInt(String_id);
           
            cliente.setId_cliente(id_cliente);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setCorreo(correo);
            cliente.setDNI(DNI);
            cliente.setTelefono(telefono);
            ClienteDAO clienteDAO=new ClienteDAO();
            clienteDAO.agregarCliente(cliente);
            List<Cliente> listaClientes = obtenerListaDatos();
            req.setAttribute("listaClientes", listaClientes);
            getServletContext().getRequestDispatcher("/Cliente.jsp").forward(req, res);
            
        }else{
            List<Cliente> listaClientes = obtenerListaDatos();
            req.setAttribute("listaClientes", listaClientes);
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/Cliente.jsp").forward(req, res);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
      public static boolean esEntero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
