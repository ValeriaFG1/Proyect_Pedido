/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import static Controller.CustomerServlet.esDouble;
import static Controller.CustomerServlet.esEntero;
import Modelo.Catalogo;
import Modelo.CatalogoDAO;
import Modelo.Producto;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS X
 */
@MultipartConfig
@WebServlet(name = "ControllerCatalogo", urlPatterns = {"/ControllerCatalogo"})
public class ControllerCatalogo extends HttpServlet {

   private String[] extens = {".ico", ".png", ".jpg", ".jpeg"};
   private String pathFiles = "D:\\Cursos\\Netbeans\\AdministradorPedidos\\web\\imgCatalogo\\";
   private File uploads = new File(pathFiles);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerCatalogo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerCatalogo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    List<Catalogo> carrito_catalogo=new ArrayList();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion=request.getParameter("action");
        switch (accion) {
            case "listar_Tortas":
                            Lista_tortas_catalogo(request, response);
                break;
             case "guardarCarrito":
                            String id_producto_carrito=request.getParameter("id_pro");
                            if(id_producto_carrito!=null){
                                getServletContext().getRequestDispatcher("/Catalogo.jsp").forward(request, response);
                            }
                            Lista_tortas(request, response);
                break;
             case "pasarCarrito":
                        
                            request.setAttribute("action", "pasarCarrito");
                            HttpSession pasar_carrito = request.getSession();
                            pasar_carrito.setAttribute("carrito_catalogo", carrito_catalogo);
                            
                            RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerServlet");
                            // Limpiar la lista carrito_catalogo después de haberla enviado
                           
                            dispatcher.forward(request, response);
                 break;
             case "listar":
                        List <Catalogo> listaCatalogo=listarProducto();
                        request.setAttribute("listaCatalogo", listaCatalogo);
                        dispatcher = request.getRequestDispatcher("GestorCatalogo.jsp");
                        dispatcher.forward(request, response);
                 break;
             case "delete":
                          int idCatalogo=Integer.parseInt(request.getParameter("idCatalogo"));
                          CatalogoDAO cataloDAO=new CatalogoDAO();
                          Catalogo catalogo=new Catalogo();
                          catalogo=cataloDAO.ProductoCatalogoPorId(idCatalogo);
                          deleCatalogo(catalogo);
                            cataloDAO.EliminarCatalogo(idCatalogo);
                            listaCatalogo=listarProducto();
                            request.setAttribute("listaCatalogo", listaCatalogo);
                            dispatcher=request.getRequestDispatcher("GestorCatalogo.jsp");
                            dispatcher.forward(request, response);
                 break;
            default:
                throw new AssertionError();
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {
        String accion=request.getParameter("action");
        switch (accion) {
            case "add":
                       try{
                        Map<String,String> errores = new HashMap();
                        String nombre=request.getParameter("nombre");
                        JOptionPane.showMessageDialog(null, nombre);
                        String StringId_categoria=request.getParameter("categorias");
                                JOptionPane.showMessageDialog(null, StringId_categoria);
                        String pisos = request.getParameter("pisos");
                          JOptionPane.showMessageDialog(null, pisos);
                        String diametros = request.getParameter("diametros");
                          JOptionPane.showMessageDialog(null, diametros);
                        String keke = request.getParameter("keke");
                          JOptionPane.showMessageDialog(null, keke);
                        String[] rellenos = request.getParameterValues("relleno");
                          JOptionPane.showMessageDialog(null, rellenos);
                        String cadenaRellenos="";
                        String StringPrecioUnitario=request.getParameter("precioUnitario");
                      JOptionPane.showMessageDialog(null, StringPrecioUnitario);
                        String descripcion = request.getParameter("descripcion");
                            JOptionPane.showMessageDialog(null, descripcion);
                        Part part = request.getPart("fileImg");
                    
                    if(StringId_categoria==null || StringId_categoria.isBlank()){
                         errores.put("id_categoriaTipo", "La categoria es requerido");
                    }
                    
                    if(!(esEntero(StringId_categoria))){
                        errores.put("id_categoria", "La categoria debe ser entero");
                    }
                    if(nombre==null || nombre.isBlank()){
                         errores.put("id_categoriaTipo", "La categoria es requerido");
                    }
                    if(pisos==null || pisos.isBlank()){
                          errores.put("pisos", "Los pisos son requeridos");
                    }
                    
                    if(diametros==null || diametros.isBlank()){
                          errores.put("diametro", "Los diametros son requeridos");
                    }
                    if(keke==null || keke.isBlank()){
                          errores.put("keke", "El tipo keke son requeridos");
                    }
                    
                    if (rellenos != null && rellenos.length > 0) {
                        cadenaRellenos = String.join(", ", rellenos);
                    }else{
                        errores.put("rellenos", "Debe escoger al menos un relleno");
                    }
                     
                    if(StringPrecioUnitario==null || StringPrecioUnitario.isBlank()){
                          errores.put("precioUnitario", "El precio unitario es requerido");
                    }
                    if(!(esDouble(StringPrecioUnitario))){
                        errores.put("precioUnitarioTipo", "El precio debe ser un numero decimal o entero");
                    }
                    
                    if(descripcion==null || descripcion.isBlank()){
                          errores.put("descripcion", "La descripcion es requerido");
                        }
                     
                    if(part == null) {
			errores.put("imagen", "Seleccion de imagen es requerido");
			}
                        
		    if(errores.isEmpty()){
                            JOptionPane.showMessageDialog(null, "estoy aqui");
                            int id_categoria=Integer.parseInt(StringId_categoria);
                            double precioUnitario =Double.parseDouble( StringPrecioUnitario);
                             
                            if(isExtension(part.getSubmittedFileName(), extens)) {
                                String imagen = saveFile(part, uploads);
                                
                                Catalogo catalogo=new Catalogo();
                                catalogo.setId_categoria(id_categoria);
                                catalogo.setNombre(nombre);
                                catalogo.setDiametro(diametros);
                                catalogo.setKeke(keke);
                                catalogo.setRelleno(cadenaRellenos);
                                catalogo.setPrecio_unitario(precioUnitario);
                                catalogo.setDescripcion(descripcion);
                                catalogo.setImagen(imagen);
                                catalogo.setPiso(pisos);
                               CatalogoDAO catalogoDAO=new CatalogoDAO();
                               catalogoDAO.agregarCatalogo(catalogo);
                               List <Catalogo> listaCatalogo=listarProducto();
                               request.setAttribute("listaCatalogo", listaCatalogo);
                            }
                           getServletContext().getRequestDispatcher("/GestorCatalogo.jsp").forward(request, response);
                        }else{
                             request.setAttribute("errores", errores);
                             getServletContext().getRequestDispatcher("/GestorCatalogo.jsp").forward(request, response);
                        }
                    }catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, e);
                    }

                   
                 break;
            case "detalleProducto":
                             
                            String id_producto_carrito=request.getParameter("id_pro");
                            if(id_producto_carrito!=null){
                                  if(esEntero(id_producto_carrito)){
                                      Catalogo catalogo=new Catalogo();
                                      CatalogoDAO catalogoDAO=new CatalogoDAO();
                                      catalogo=catalogoDAO.ProductoCatalogoPorId(Integer.parseInt(id_producto_carrito));
                                      request.setAttribute("catalogo", catalogo);
                                  }
                              getServletContext().getRequestDispatcher("/DetalleProductoCatalogo.jsp").forward(request, response);
                            }else{
                                Lista_tortas(request, response);
                            }
                break;
             case "agregarProductoCarrito":
                            agrerProductocarrito(request,response);
                 break;
            default:
                throw new AssertionError();
        }
    }
    private  List<Catalogo> listarProducto(){
        CatalogoDAO catalogoDAO=new CatalogoDAO();
        List<Catalogo> lista=new ArrayList();
        lista=catalogoDAO.BuscarCatalogoPorIdCategoria();
        return  lista;
    }
    private String saveFile(Part part, File pathUploads) {
            String pathAbsolute = "";

            try {

                    Path path = Paths.get(part.getSubmittedFileName());
                  //nombre del archivo 
                    String fileName = path.getFileName().toString();
                  //el archivo en si - tiene que ser almacenado
                    InputStream input = part.getInputStream();

                    if(input != null) {
                           
                        
                            File file = new File(pathUploads, fileName);
                                   // Verificar si el archivo ya existe
                            if (file.exists()) {
                                // Obtener la extensión del archivo
                                String extension = "";
                                int dotIndex = fileName.lastIndexOf('.');
                                if (dotIndex != -1) {
                                    extension = fileName.substring(dotIndex);
                                }

                                // Construir un nuevo nombre de archivo con "(1)"
                                String baseFileName = fileName.substring(0, dotIndex);
                                String newFileName = baseFileName + "(1)" + extension;

                                // Crear un nuevo archivo con el nuevo nombre
                                file = new File(pathUploads, newFileName);
                            }
                          // pathAbsolute = file.getAbsolutePath();
                            pathAbsolute = file.getName();
                            //Aqui se guarda el archivo en la carpeta
                            Files.copy(input, file.toPath());
                    }

            } catch (Exception e) {
                    e.printStackTrace();
            }

            return pathAbsolute;
    }
    private boolean isExtension(String fileName, String[] extensions) {
              for(String et : extensions) {
                      if(fileName.toLowerCase().endsWith(et)) {
                              return true;
                      }
              }

              return false;
    }
     private void deleCatalogo(Catalogo catalogo){
            String imagenPath=catalogo.getImagen();
            String pathEliminar=pathFiles.concat(imagenPath);
            
             File f=new File(pathEliminar);
             f.delete();
    }
    protected void Lista_tortas(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            
            List<Catalogo> lista_catalogo=new ArrayList();
            CatalogoDAO cataloDAO=new CatalogoDAO();
            lista_catalogo=cataloDAO.BuscarCatalogoPorIdCategoria();
             // Convertir la lista de productos a formato JSON
             req.setAttribute("lista_catalogo", lista_catalogo);
             getServletContext().getRequestDispatcher("/Catalogo.jsp").forward(req, res);
    }
     
    protected void Lista_tortas_catalogo(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            carrito_catalogo.clear();
            List<Catalogo> lista_catalogo=new ArrayList();
            CatalogoDAO cataloDAO=new CatalogoDAO();
            lista_catalogo=cataloDAO.BuscarCatalogoPorIdCategoria();
             // Convertir la lista de productos a formato JSON
             req.setAttribute("lista_catalogo", lista_catalogo);
             getServletContext().getRequestDispatcher("/Catalogo.jsp").forward(req, res);
    }
     protected void agrerProductocarrito(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        
         String id_producto=req.getParameter("id_producto_catalogo");
         String cantidad=req.getParameter("cantidad");
         String imagen=req.getParameter("imagen");
         String nombre=req.getParameter("nombre");
         Map<String,String> errores = new HashMap();
         
         if( id_producto==null || id_producto.isBlank()){
            errores.put("id_producto", "El id_producto es requerido");
        }
        if( cantidad==null || cantidad.isBlank()){
            errores.put("cantidad", "La cantidad es requerido");
        }
        if( imagen==null || imagen.isBlank()){
            errores.put("imagen", "La imagen es requerido");
        }
         if( nombre==null || nombre.isBlank()){
            errores.put("nombre", "La imagen es requerido");
        }
        if(errores.isEmpty()){
            
            Catalogo catalogo =new Catalogo();
            if(esEntero(cantidad) || esEntero(id_producto)){
                catalogo.setCantidad(Integer.parseInt(cantidad));
                catalogo.setId_producto_catalogo(Integer.parseInt(id_producto));
                catalogo.setNombre(nombre);
                catalogo.setImagen(imagen);
                if(carrito_catalogo.isEmpty()){
                    carrito_catalogo.add(catalogo);
                }else{
                    int rpta=-1;
                    for(int i=0;i<carrito_catalogo.size();i++){
                         if (carrito_catalogo.get(i).getId_producto_catalogo() == catalogo.getId_producto_catalogo()) {
                                rpta=i;
                            }
                    }
                    
                    if(rpta!=-1){
                        Catalogo productoModificar=carrito_catalogo.get(rpta);
                        int actualCantidad=productoModificar.getCantidad();
                        productoModificar.setCantidad((actualCantidad+Integer.parseInt(cantidad)));
                        
                    }else{
                       carrito_catalogo.add(catalogo); 
                    }
                    
                }
               JOptionPane.showMessageDialog(null, carrito_catalogo);
                req.setAttribute("carrito_catalogo", carrito_catalogo);
                Lista_tortas(req, res);
                
            }else{
               Lista_tortas(req, res);
            }
            
        }else{
          Lista_tortas(req, res);
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
