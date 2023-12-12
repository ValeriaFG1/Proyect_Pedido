/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Catalogo;
import Modelo.CatalogoDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Pedido;
import Modelo.PedidoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.RetardoCarga;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
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
import java.sql.SQLException;
import java.util.Date;
//import java.util.Date;
import java.time.Instant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@MultipartConfig
@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})



public class CustomerServlet extends HttpServlet {

	//private CustomerController custCtr = new CustomerController();
        List<Producto> listaCarrito=new ArrayList();
        private Pedido pedido=new Pedido();
	private String pathFiles = "D:\\Cursos\\Netbeans\\AdministradorPedidos\\web\\files\\";
	private File uploads = new File(pathFiles);
	private String[] extens = {".ico", ".png", ".jpg", ".jpeg"};
        private int idProducto=1;
        private int cantidadProducto=0;
        private double subTotal=0;
   
        
        
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch (action) {
		case "add":
			saveCustomer(request, response);
			break;
                case "buscarCliente":
                               
                        buscarCliente(request,response);
                        break;
                case "recepcionarCliente":
                    {
                        try {
                            guardarClienteObjeto(request,response);
                        } catch (ParseException ex) {
                            Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;
                case "generarPedido":
                    {
                        try {
                            generarPedido(request,response);
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        } catch (SQLException e){
                             JOptionPane.showMessageDialog(null, e);
                        }
                    }
                    break;
              

                default:
                        
			break;
		}
		
	}
      @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispacher=null;
		String action = request.getParameter("action");
		
		switch (action) {
                case "listar":
                        listarProductos(request,response);
                     break;
                case "delete":
                        
                        int idPro=Integer.parseInt(request.getParameter("idPro"));
                        deleCustomer(idPro);
                        if(!(listaCarrito.isEmpty())){ 
                        request.setAttribute("listaProducto", listaCarrito);
                                cantidadProducto=0;
                                subTotal=0;
                                for(Producto c:listaCarrito){
                                    subTotal+=c.getPrecioUnitario()*c.getCantidad();
                                    cantidadProducto+=1;
                                }
                                double IGV=subTotal*0.21;
                                double totalPagar=IGV+subTotal;
                                request.setAttribute("subTotal", subTotal);
                                request.setAttribute("IGV", IGV);
                                request.setAttribute("totalPagar", totalPagar);
                                request.setAttribute("cantidad", cantidadProducto);
                        }
                        dispacher=request.getRequestDispatcher("pedido.jsp");
                        
                    break;
                case "generarPago":
                        
                         dispacher=request.getRequestDispatcher("generarPago.jsp");
                break;
                case "nuevo":
                        limpiarListaCarrito();
                       dispacher=request.getRequestDispatcher("pedido.jsp");
                break;
                case "irACrearCliente":
                        subTotal=0;
                            for(Producto c:listaCarrito){
                                subTotal+=c.getPrecioUnitario()*c.getCantidad();
                            }
                            double IGV=subTotal*0.21;
                            double totalPagar=IGV+subTotal;
                         request.setAttribute("totalPagar", totalPagar);
                         request.setAttribute("Volver", "volver");
                         dispacher=request.getRequestDispatcher("Cliente.jsp");
                break;
                case "pasarCarrito":
                           agregarACarrito(request, response);
                break;
                default:
                        
			break;
                
		}
             dispacher.forward(request, response);
		
	}
    
 private void listarProductos(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
       if(!(listaCarrito.isEmpty())){ 
                        req.setAttribute("listaProducto", listaCarrito);
                                cantidadProducto=0;
                                subTotal=0;
                                for(Producto c:listaCarrito){
                                    subTotal+=c.getPrecioUnitario()*c.getCantidad();
                                    cantidadProducto+=1;
                                }
                                double IGV=subTotal*0.21;
                                double totalPagar=IGV+subTotal;
                                req.setAttribute("subTotal", subTotal);
                                req.setAttribute("IGV", IGV);
                                req.setAttribute("totalPagar", totalPagar);
                                req.setAttribute("cantidad", cantidadProducto);
                        }
          RequestDispatcher dispatcher = req.getRequestDispatcher("pedido.jsp");
          dispatcher.forward(req, res);
        }
 private void deleCustomer(int idPro){
            
            int post = 0;
            for(int i=0;i<listaCarrito.size();i++){
                    if(idPro==listaCarrito.get(i).getIdProducto()){
                        post=i;
                        break;
                    }
            }
            String imagenPath=listaCarrito.get(post).getImagen();
            String pathEliminar=pathFiles.concat(imagenPath);
             JOptionPane.showMessageDialog(null, pathEliminar);
             File f=new File(pathEliminar);
             f.delete();
             listaCarrito.remove(post);
    }
    
  private void agregarACarrito(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        
        
            List<Catalogo> lista_catalogo=new ArrayList();
            HttpSession pasar_carrito = req.getSession();
            List<Catalogo> carritoCatalogo = (List<Catalogo>) pasar_carrito.getAttribute("carrito_catalogo");
            
            limpiarListaCarrito();
            CatalogoDAO catalogoDAO=new CatalogoDAO();
            
            if(carritoCatalogo.size()>0){
                for(Catalogo c:carritoCatalogo){
                    Producto producto=new Producto();
                    producto.setIdProducto(idProducto);
                    Catalogo cDetalles=new Catalogo();
                    
                    cDetalles=catalogoDAO.ProductoCatalogoPorId(c.getId_producto_catalogo());
                    producto.setId_categoria(cDetalles.getId_categoria());
                    producto.setPisos(cDetalles.getPiso());
                    producto.setDiametros(cDetalles.getDiametro());
                    producto.setKeke(cDetalles.getKeke());
                    producto.setRellenos(cDetalles.getRelleno());
                    producto.setPrecioUnitario(cDetalles.getPrecio_unitario());
                    producto.setCantidad(c.getCantidad());
                    producto.setDescripcion(cDetalles.getDescripcion());
                    producto.setImagen(cDetalles.getImagen());
                    pedido.copiarArchivoServidor(cDetalles.getImagen());
                    listaCarrito.add(producto);
                    idProducto++;
                }
                  try {
                        // Simula un retraso de 3 segundos (3000 milisegundos)
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                listarProductos(req, res);
            }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, res);
            }
            
            
    }
  
    private void saveCustomer(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		RequestDispatcher dispacher=null;
                try {
                    Map<String,String> errores = new HashMap();   
                    
                    String StringId_categoria=req.getParameter("categorias");
                    String pisos = req.getParameter("pisos");
                    String diametros = req.getParameter("diametros");
                    String keke = req.getParameter("keke");
                    String[] rellenos = req.getParameterValues("relleno");
                    String cadenaRellenos="";
                    String StringPrecioUnitario=req.getParameter("precioUnitario");
                    String StringCantidad=req.getParameter("cantidad");
                    String descripcion = req.getParameter("descripcion");
                    Part part = req.getPart("fileImg");
                   
                    if(StringId_categoria==null || StringId_categoria.isBlank()){
                         errores.put("id_categoriaTipo", "La categoria es requerido");
                    }
                    
                    if(!(esEntero(StringId_categoria))){
                        errores.put("id_categoria", "La categoria debe ser entero");
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
                    
                    if(StringCantidad==null || StringCantidad.isBlank()){
                          errores.put("cantidad", "La cantidad es requerido");
                    }
                    
                    if(!(esEntero(StringCantidad))){
                        errores.put("cantidadTipo", "La cantidad debe ser entero");
                    }
                        
                    if(descripcion==null || descripcion.isBlank()){
                          errores.put("descripcion", "La descripcion es requerido");
                        }
                    if(part == null) {
			errores.put("imagen", "Seleccion de imagen es requerido");
			}
			if(errores.isEmpty()){
                            int id_categoria=Integer.parseInt(StringId_categoria);
                            double precioUnitario =Double.parseDouble( StringPrecioUnitario);
                             int cantidad =Integer.parseInt(  StringCantidad);
                             
                            if(isExtension(part.getSubmittedFileName(), extens)) {
                                String imagen = saveFile(part, uploads);
                                
				//Customer cust = new Customer(name, photo);
                                Producto producto=new Producto();
                                producto.setIdProducto(idProducto);
                                producto.setId_categoria(id_categoria);
                                producto.setPisos(pisos);
                                producto.setDiametros(diametros);
                                producto.setKeke(keke);
                                producto.setRellenos(cadenaRellenos);
                                producto.setPrecioUnitario(precioUnitario);
                                producto.setCantidad(cantidad);
                                producto.setDescripcion(descripcion);
                                producto.setImagen(imagen);
                                //	custCtr.addCustomer(cust);
                                listaCarrito.add(producto);
                                
                                idProducto++;
                                req.setAttribute("listaProducto", listaCarrito);
                                cantidadProducto=0;
                                subTotal=0;
                                for(Producto c:listaCarrito){
                                    subTotal+=c.getPrecioUnitario()*c.getCantidad();
                                    cantidadProducto+=1;
                                }
                                
                                double IGV=subTotal*0.21;
                                double totalPagar=IGV+subTotal;
                                 try {
                                        // Simula un retraso de 3 segundos (3000 milisegundos)
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                req.setAttribute("subTotal", subTotal);
                                req.setAttribute("IGV", IGV);
                                req.setAttribute("totalPagar", totalPagar);
                                req.setAttribute("cantidad", cantidadProducto);
                              }
                              dispacher=req.getRequestDispatcher("pedido.jsp");
                        }else{
                                if(!(listaCarrito.isEmpty())){ 
                                    req.setAttribute("listaProducto", listaCarrito);
                                       cantidadProducto=0;
                                       subTotal=0;
                                       for(Producto c:listaCarrito){
                                           subTotal+=c.getPrecioUnitario()*c.getCantidad();
                                           cantidadProducto+=1;
                                       }
                                       double IGV=subTotal*0.21;
                                       double totalPagar=IGV+subTotal;
                                       req.setAttribute("subTotal", subTotal);
                                       req.setAttribute("IGV", IGV);
                                       req.setAttribute("totalPagar", totalPagar);
                                       req.setAttribute("cantidad", cantidadProducto);
                               }
                              req.setAttribute("errores", errores);
                              dispacher=req.getRequestDispatcher("pedido.jsp");
                        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 // res.sendRedirect("/AdministradorPedidos/pedido.jsp");
               dispacher.forward(req, res);
	}
    private void buscarCliente(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
            RequestDispatcher dispacher=null;
            String DNICliente=req.getParameter("DNICliente");
            Map<String,String> errores=new HashMap();
            if(DNICliente.isBlank() || DNICliente==null){
                 errores.put("DNINoExiste", "Es necesario ingresar el valor del DNI");
                 req.setAttribute("errores", errores);
                dispacher=req.getRequestDispatcher("generarPago.jsp");
                
            }else{
                if(DNICliente.length()!=8){
                 errores.put("DNINoTamaño", "El tamaño del DNI no es valido");
                  req.setAttribute("errores", errores);
                dispacher=req.getRequestDispatcher("generarPago.jsp");
                }else{
                    if(!(esEntero(DNICliente))){
                     errores.put("DNINoEntero", "El DNI debe ser numero");
                      req.setAttribute("errores", errores);
                      dispacher=req.getRequestDispatcher("generarPago.jsp");
                    }else{
                         
                         ClienteDAO clienteDAO=new ClienteDAO();
                         Cliente cliente=new Cliente();
                         cliente=clienteDAO.BuscarClientePorDNI(DNICliente);
                         if(cliente!=null ){
                             if(cliente.getId_cliente()!=0){ 
                                 
                                req.setAttribute("clienteEncontrado", cliente);
                                req.setAttribute("errores", errores);
                                    subTotal=0;
                                    for(Producto c:listaCarrito){
                                        subTotal+=c.getPrecioUnitario()*c.getCantidad();
                                    }
                                    double IGV=subTotal*0.21;
                                    double totalPagar=IGV+subTotal;
                                    req.setAttribute("totalPagar", totalPagar);
                                dispacher=req.getRequestDispatcher("generarPago.jsp");
                                
                             }else{
                                errores.put("DNINoEntero", "El usuario el usuario es 0");
                                req.setAttribute("errores", errores);
                                dispacher=req.getRequestDispatcher("generarPago.jsp");
                             }
                         }else{
                             errores.put("DNINoEntero", "El usuario no existe");
                             req.setAttribute("errores", errores);
                             dispacher=req.getRequestDispatcher("generarPago.jsp");
                             
                         }
                         
                    }
                }
            }
             dispacher.forward(req, res);
    }
    private void guardarClienteObjeto(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ParseException{
            RequestDispatcher dispacher=null;
            HttpSession session = req.getSession(false);
            int id_empleado=0;
                if (session != null) {
                    // Obtener el objeto de usuario de la sesión
                    Empleado empleado = (Empleado) session.getAttribute("sesion");

                    if (empleado != null) {
                        id_empleado=empleado.getId_empleado();
                    } else {
                       id_empleado=1;
                    }
                } else {
                     dispacher=req.getRequestDispatcher("Login.jsp");
                     dispacher.forward(req, res);
                    return;
                }
            Empleado empleado=(Empleado)session.getAttribute("id_empleado");
            String StringId_cliente=req.getParameter("id_cliente");
            String fechaStringPedido=req.getParameter("fecha_pedido");
            String fechaStringEntrega = req.getParameter("fecha_entrega");
            String horaString = req.getParameter("hora");
       
            String StringaCuenta=req.getParameter("acuenta");
            String StringSaldo=req.getParameter("saldo");
            String StringTotal=req.getParameter("totalPagar");
            
             Map<String,String> erroresCarga=new HashMap();
             if(StringId_cliente==null || StringId_cliente.isBlank()){
                  erroresCarga.put("id_cliente","el id_cliente es requerido");
             }
             if( fechaStringPedido == null || fechaStringPedido.isBlank() ){
                 erroresCarga.put("fecha_Pedido","La fecha pedido es requerido");
              }
             if( horaString == null || horaString.isBlank() ){
                 erroresCarga.put("hora","La hora pedido es requerido");
              }
             if( fechaStringEntrega == null || fechaStringEntrega.isBlank()){
                 erroresCarga.put("fecha_entrega","La fecha entrega es requerido");
              }
              if( StringaCuenta==null || StringaCuenta.isBlank()){
                 erroresCarga.put("acuenta","El acuenta es requerido");
              }
              
              if(StringSaldo==null || StringSaldo.isBlank()){
                erroresCarga.put("saldo","El saldo es requerido");
              }
              if(StringTotal==null || StringTotal.isBlank()){
                erroresCarga.put("total","El saldo es requerido");
              }
              if(!(esDouble(StringaCuenta))){
                erroresCarga.put("acuentaTipo","El saldo es requerido");
              }
              if(erroresCarga.isEmpty()){
                       int id_cliente=Integer.parseInt(StringId_cliente);
                       double acuenta=Double.parseDouble(StringaCuenta);
                       double saldo=Double.parseDouble(StringSaldo);
                       double total=Double.parseDouble(StringTotal);
                       
                       SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                       Date fechaPedido =  (Date) formatoFecha.parse(fechaStringPedido);
                       SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                       Date fechaEntrega = (Date) formatoFechaHora.parse(fechaStringEntrega + " " + horaString);                    
                       pedido.setId_cliente(id_cliente);
                       pedido.setId_empleado(id_empleado);
                       pedido.setFecha_hora_pedido(new java.sql.Date(fechaPedido.getTime()));
                       pedido.setFecha_hora_entrega(new java.sql.Timestamp(fechaEntrega.getTime()));
                       pedido.setSaldo(saldo);
                       pedido.setTotal(total);
                       pedido.setAcuenta(acuenta);
                       pedido.setEstado("En proceso");
                       req.setAttribute("listaProducto", listaCarrito);
                                cantidadProducto=0;
                                subTotal=0;
                                for(Producto c:listaCarrito){
                                    subTotal+=c.getPrecioUnitario()*c.getCantidad();
                                    cantidadProducto+=1;
                                }
                                double IGV=subTotal*0.21;
                                double totalPagar=IGV+subTotal;
                        req.setAttribute("subTotal", subTotal);
                        req.setAttribute("IGV", IGV);
                        req.setAttribute("totalPagar", totalPagar);
                        req.setAttribute("cantidad", cantidadProducto);
                       dispacher=req.getRequestDispatcher("pedido.jsp");
              }else{
                    subTotal=0;
                    for(Producto c:listaCarrito){
                        subTotal+=c.getPrecioUnitario()*c.getCantidad();
                    }
                    double IGV=subTotal*0.21;
                    double totalPagar=IGV+subTotal;
                    int id_cliente=Integer.parseInt(StringId_cliente);
                    req.setAttribute("totalPagar", totalPagar);
                    req.setAttribute("clienteEncontrado", id_cliente);
                    req.setAttribute("errores", erroresCarga);
                   dispacher=req.getRequestDispatcher("generarPago.jsp");
               }
           dispacher.forward(req, res);
    }
    
    
     private void generarPedido (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ParseException, SQLException{
         RequestDispatcher dispacher=null;
            PedidoDAO pedidoDAO=new PedidoDAO();
            ProductoDAO productoDAO=new ProductoDAO();
            
            Map<String,String> erroresCarga=new HashMap();
            
            if(listaCarrito.isEmpty()){
                JOptionPane.showMessageDialog(null, "estoy vacio");
                erroresCarga.put("listaVacia", "Para realizar una boleta de pedido es necesario al menos un producto");
                req.setAttribute("errores", erroresCarga);
                dispacher=req.getRequestDispatcher("pedido.jsp"); 
                
            }else{
                  
                
               
                if(pedido==null || pedido.getId_cliente()==0){
                 
                 erroresCarga.put("clienteNogenerado", "Es necesario registrar cliente para pedido");
                  req.setAttribute("errores", erroresCarga);
                   req.setAttribute("listaProducto", listaCarrito);
                                cantidadProducto=0;
                                subTotal=0;
                                for(Producto c:listaCarrito){
                                    subTotal+=c.getPrecioUnitario()*c.getCantidad();
                                    cantidadProducto+=1;
                                }
                                double IGV=subTotal*0.21;
                                double totalPagar=IGV+subTotal;
                    req.setAttribute("subTotal", subTotal);
                    req.setAttribute("IGV", IGV);
                    req.setAttribute("totalPagar", totalPagar);
                    req.setAttribute("cantidad", cantidadProducto);
                  dispacher=req.getRequestDispatcher("pedido.jsp"); 
                 }
                else{
                     int id_pedido=pedidoDAO.guardarPedido(pedido);
                     if(id_pedido!=-1){
                        if(productoDAO.guardar(listaCarrito, id_pedido)){
                           // limpiarListaCarrito();
                            req.setAttribute("id_pedido", id_pedido);
                            Cliente cliente=new Cliente();
                            ClienteDAO clienteDAO=new ClienteDAO();
                            req.setAttribute("cliente", clienteDAO.BuscarPorId(pedido.getId_cliente()));
                            Empleado empleado=new Empleado();
                            EmpleadoDAO empleadoDAO=new EmpleadoDAO();
                            req.setAttribute("empleado", empleadoDAO.BuscarPorId(pedido.getId_empleado()));
                            req.setAttribute("pedido", pedido);
                            req.setAttribute("listaCarrito", listaCarrito);
                            dispacher=req.getRequestDispatcher("BoletaPedido.jsp"); 
                        }else{
                            erroresCarga.put("error en la carga", "Se a producido un error en la carga");
                             req.setAttribute("errores", erroresCarga);
                            dispacher=req.getRequestDispatcher("pedido.jsp");   
                        }
                    }else{
                         erroresCarga.put("error en la carga", "Se a producido un error en la carga");
                         req.setAttribute("errores", erroresCarga);
                         dispacher=req.getRequestDispatcher("pedido.jsp"); 
                    }
                }
                
            }
            
            
           
            
           dispacher.forward(req, res);
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
    
    //validar si es imagen
   private boolean isExtension(String fileName, String[] extensions) {
		for(String et : extensions) {
			if(fileName.toLowerCase().endsWith(et)) {
				return true;
			}
		}
		
		return false;
    }
    public static boolean esEntero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
     public static boolean esDouble(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void limpiarListaCarrito(){
       listaCarrito.clear();
       pedido=new Pedido();
       idProducto=1;
       cantidadProducto=0;
       subTotal=0;
    }
    
   
}
