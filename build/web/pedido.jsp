<%-- 
    Document   : pedido
    Created on : 30 oct. 2023, 00:13:58
    Author     : ASUS X
--%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
              <%@include file="Vista/layout/head.jsp" %>
              <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
              <link rel="stylesheet" href="assets/css/pages/styleN.css">
              <link rel="stylesheet" href="assets/css/jquery.dataTables.min.css">
               <link rel="stylesheet" href="assets/css/style.css">
    </head>
     <body>
        <!-- [ Preloader ] Start -->
    <div class="page-loader">
        <div class="bg-primary"></div>
    </div>
    <!-- [ Preloader ] End -->

    <!-- [ Layout wrapper ] Start -->
    <div class="layout-wrapper layout-2">
        <div class="layout-inner">
            <!-- [ Layout sidenav ] Start -->
            <%@include file="Vista/layout/navegador.jsp" %>
            <!-- [ Layout sidenav ] End -->
            <!-- [ Layout container ] Start -->
            <div class="layout-container">
                <!-- [ Layout navbar ( Header ) ] Start -->
                <%@include file="Vista/layout/header.jsp" %>
                <!-- [ Layout navbar ( Header ) ] End -->
                    <h2 class="text-center my-5 titulo">Administrador de pedidos</h2>
                    <div class="container  ">
                         <div id="contenido" class="row">
                         <div class="col-md-6 agregar-cita px-5">
                             <form class="card  bg-light px-5 py-3" action="CustomerServlet"  method="post" enctype="multipart/form-data">
                                  
                                 <legend class="mb-4 text-bold ">Datos del producto</legend>
                                 <label for="categorias" class="form-label" >Categoría</label>
                                 <select id="categorias" class="form-select" name="categorias">
                                      
                                        <option value="1">Masa elastica</option>
                                        <option value="2">Torta en chantilly</option>
                                        <option value="3">Chesecakes</option>
                                    </select>
                                   
                                     <div class="">
                                        <label for="pisos" class="form-label">Pisos:</label>
                                        <select id="pisos" class="form-select" name="pisos">
                                       
                                        <option value="Un piso">Un piso</option>
                                        <option value="dos pisos">Dos pisos</option>
                                        <option value="tres pisos">Tres pisos</option>
                                        <option value="cuatro pisos">Cuatro pisos</option>
                                         <option value="Cinco pisos">Cinco pisos</option>
                                        </select>
                                     </div>
                                     <div class="mt-3">
                                     <label for="diametros" class="form-label">Diametros:</label>
                                     <input type="text"  id="diametros" class="form-control px-3" placeholder="Ejemplo: 18-20-24" name="diametros">
                                     </div>
                                    <div class="mt-3">
                                        <label for="keke" class="form-label">Tipo de keke:</label>
                                        <input type="text"  id="keke" class="form-control px-3" placeholder="chocolate/vainilla" name="keke">
                                     </div>
                                    <div class="row mb-3">
                                       <label  class="col-form-label">Relleno: </label >
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="fresa" class="form-check-input" name="relleno">
                                         <label class="form-check-label">Fresa</label>
                                       </div>
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="manjar" class="form-check-input" name="relleno" >
                                         <label class="form-check-label">Manjar</label>
                                       </div>
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="guanabana" class="form-check-input" name="relleno">
                                         <label class="form-check-label">Guanabana</label>
                                       </div>
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="fudge" class="form-check-input" name="relleno">
                                         <label class="form-check-label">Fudge</label>
                                       </div>
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="ganash" class="form-check-input" name="relleno">
                                         <label class="form-check-label">Ganash</label>
                                       </div>
                                     </div>
                                    <div class="row mt-3">
                                         <label for="precioUnitario" class="form-label">Precio Unitario:</label>
                                           <input type="text"  id="precioUnitario" class="form-control px-3" placeholder="000.00" name="precioUnitario">
                                         </div>
                                         <div class="row mt-3">
                                         <label for="cantidad" class="form-label">Cantidad:</label>
                                           <input type="text"  id="cantidad" class="form-control px-3" placeholder="0" name="cantidad">
                                         </div>
                                          <div class="row mt-3">
                                                <label for="descripcion" class="form-label">Descripción</label>
                                                <textarea class="form-control px-3" id="descripcion" rows="3" name="descripcion"></textarea>
                                         </div>
                                         <div class="row mt-3">
                                                <label for="imagen" class="form-label">Imagen:</label>
                                                <input type="file"   class="form-control px-3" accept="image/*" id="fileImg" name="fileImg">
                                        </div>
                                        <div class="row mt-3 imagenResultado" id="imagenResultado">
                                            
                                        </div>
                                        <div class="form-group mt-3">
                                            <button type="submit" class="btn btn-success w-100 d-block" id='agregarPedido' name="action" value="add">Agregar productos</button>
                                         </div> 
                             </form>    
                     </div>
                    <div class="col-md-6" id="resultado">
                        <div class="row">
                        <h2 id="administra" class="mb-4 text-center">Resumen de pedidos</h2>
                        </div>
                        <div class="row">
                        <form action="" method="post" class="row g-3">
                             
                               <div class="mb-3 row px-5">
                               <label for="subTotal" class="col-sm-6 col-form-label">SUB TOTAL:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="subTotal" readonly value=" <% if(request.getAttribute("subTotal")!=null){ 
                            double subTotal=(double)request.getAttribute("subTotal");
                                            if(subTotal!=0){
                                            out.print(subTotal);
                                }
                                }
                            %> "  >
                                </div>
                              
                                </div>
                                 
                                <div class="mb-3 row px-5">
                             
                                <label for="IGV" class="col-sm-6 col-form-label">IGV:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="IGV" readonly  value="<% if(request.getAttribute("IGV")!=null){ 
                                        double IGV=(double)request.getAttribute("IGV");
                                            if(IGV!=0){
                                            out.print(IGV);
                                }
                                }
                            %>">
                                </div>
                               
                                </div>
                                <div class="mb-3 row px-5">
                                
                                <label for="precioTotal" class="col-sm-6 col-form-label">PRECIO TOTAL:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="precioTotal" readonly value=" <% if(request.getAttribute("totalPagar")!=null){ 
                            double totalPagar=(double)request.getAttribute("totalPagar");
                                            if(totalPagar!=0){
                                            out.print(totalPagar);
                                }
                                }
                            %> ">
                                </div>
                                </div>
                                <div class="mb-3 row px-5">
                                   
                                <label for="productoTotal" class="col-sm-6 col-form-label">CANTIDAD PRODUCTOS:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="productoTotal" readonly value=" <% if(request.getAttribute("cantidad")!=null){ 
                                    int cantidad=(int)request.getAttribute("cantidad");
                                            if(cantidad!=0){
                                            out.print(cantidad);
                                }
                                }
                            %> ">
                                </div>
                                </div>
                                <div class="form-group mb-3 px-5">
                                   <button type="submit" class="btn btn-primary w-100 d-block" id='generarPedido' name="action" value="generarPedido">Generar Pedido</button>
                                </div>
                                </form>  
                            <div class="row g-3">   
                                <div class="mb-3 px-5">
                                    <a href="CustomerServlet?action=generarPago" class="btn btn-success w-100 d-block" >Seleccionar Cliente</a>
                                   
                                 </div>
                            </div>
                            </div> 
                             <div class="card pt-3">
                                     <%   if(request.getAttribute("errores")!=null){ 
                                        Map<String,String> erroresMap=(Map<String,String>)request.getAttribute("errores"); 
                                        if(erroresMap.size()>0){
                                    %>
                                        <ul class="alert alert-danger mx-3 px-3">
                                        <% for(String error: erroresMap.values()){ %>
                                       
                                          <div class="alert alert-warning alert-dismissible fade show">
                                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                                    <%out.print(error);%>
                                         </div>
                                        <% } %>
                                        </ul>
                                    <% }
                                    }%>
                            </div>
                            
                        
                    </div>
                    <div class="mt-3 container p-3" id="divProductos">
                                 
                     </div>
            </div> <!--.row-->
            <div class="row">
                <div class="container">
                <table class="customers" id="example" style="width:100%">
                  <%    if(request.getAttribute("listaProducto")!=null){
                        List<Producto> lista=(List<Producto>)request.getAttribute("listaProducto");
                        if(lista.size()>0){
                      %>
                      <thead>
                            <tr>
                               <th scope="row">${id}</th>
                               <th scope="col">Categoria</th>
                                <th scope="col">Pisos</th> 
                                <th scope="col">Diametro</th> 
                                <th scope="col">Keke</th>
                                <th scope="col">Relleno</th> 
                                <th scope="col">Precio</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Imagen</th>
                                <th scope="col">Acctión</th>
                            </tr>
                      </thead>  
                      <tbody>
                          <% for(int i=0; i<lista.size();i++){
                            %>
                            <tr>
                                <td><%out.print(lista.get(i).getIdProducto()); %> </td>
                                <td><%switch(lista.get(i).getId_categoria()){
                                    case 1:
                                        out.print("Torta en masa elastica");
                                        break;
                                    case 2:
                                        out.print("Torta en chantilly");
                                        break;
                                    case 3:
                                        out.print("CheseCakes");
                                        break;
                                    case 4:
                                        out.print("Torta para mujer");
                                        break;
                                    case 5:
                                         out.print("Torta para niño");
                                        break;
                                    case 6:
                                        out.print("Torta hombre");
                                        break;
                                     default:
                                        out.print("Torta anita"); 
                                    }
                                %> </td>
                                <td><%out.print(lista.get(i).getPisos()); %> </td>
                                 <td><%out.print(lista.get(i).getDiametros()); %> </td>
                                 <td><%out.print(lista.get(i).getKeke()); %> </td>
                                   <td><%out.print(lista.get(i).getRellenos()); %> </td>
                                   <td>S/ <%out.print(lista.get(i).getPrecioUnitario()); %> </td>
                                   <td>&nbsp; &nbsp; <%out.print(lista.get(i).getCantidad()); %> </td>
                                   <td><img src="files/<%out.print(lista.get(i).getImagen());%>" width="100px" height="100px" /> </td>
                                 <!--  <td><button class="btn btn-danger" type="submit">Eliminar</button> </td> -->
                                 <td> <a href="CustomerServlet?action=delete&idPro=<% out.print(lista.get(i).getIdProducto());%>" target="target" class="btn btn-danger">Eliminar</a> </td>
                            </tr>
                            <% } %>
                       </tbody>
                       <% } } %>
                </table>
                     
                     </div>
            </div>
                     
        </div><!--.container-->
           
               

                    <!-- [ Layout footer ] Start -->
                    <%@include file="Vista/layout/footer.jsp" %>
                    <!-- [ Layout footer ] End -->
                </div>
                <!-- [ Layout content ] Start -->
            </div>
            <!-- [ Layout container ] End -->
        </div>
        <!-- Overlay -->
        <div class="layout-overlay layout-sidenav-toggle"></div>
    </div>
    <!-- [ Layout wrapper] End -->
       <!-- Core scripts -->
       <%@include file="Vista/layout/script.jsp" %>
       <script src="js/GenerarVenta.js"></script>
       <script src="js/jquery-3.7.0.js"></script>
       <script src="js/jquery.dataTables.min.js"></script>
       <script src="js/FormatoTabla01.js" ></script>
       <script src="js/bootstrap.bundle.min.js"></script>
    </body>
</html>
