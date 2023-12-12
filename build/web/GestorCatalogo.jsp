<%-- 
    Document   : GestorCatalogo
    Created on : 10 dic. 2023, 18:03:08
    Author     : ASUS X
--%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Catalogo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
<head>
    <%@include file="Vista/layout/head.jsp" %>
    <!-- Page -->
    <link rel="stylesheet" href="assets/css/pages/authentication.css">
    <!-- Libs -->
    <link rel="stylesheet" href="assets/libs/perfect-scrollbar/perfect-scrollbar.css">
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
                    
                    <div class="container-fluid flex-grow-1 container-p-y"> 
                        <h4 class="font-weight-bold py-3 mb-0">Gestión de catalogo</h4>
                        <div class="text-muted small mt-0 mb-4 d-block breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item">Gestion de Catalogo</li>
                                <li class="breadcrumb-item active">Gestión de catalogo</li>
                            </ol>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-6 ">
                            <div class="card mb-4">
                                <h6 class="card-header">CATALOGO</h6>
                                <div class="card-body">
                  <form class="card  bg-light px-5 py-3" action="ControllerCatalogo?action=add"  method="post" enctype="multipart/form-data">
                      <input type="hidden" name="Id_producto_catalogo" value="0">
                                 <legend class="mb-4 text-bold ">Datos del producto</legend>
                                  <label for="categorias" class="form-label" >Categoría</label>
                                  
                                 <select id="categorias" class="form-select" name="categorias">
                                      
                                        <option value="1">Masa elastica</option>
                                        <option value="2">Torta en chantilly</option>
                                        <option value="3">Chesecakes</option>
                                        <option value="4">Torta mujer</option>
                                        <option value="5">Torta niño</option>
                                         <option value="6">Torta hombre</option>
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
                                     <label for="diametros" class="form-label">Nombre:</label>
                                     <input type="text"  id="nombre" class="form-control px-3" placeholder="Torta corazon" name="nombre">
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
                                            <button type="submit" class="btn btn-success w-100 d-block" id="agregarCatalogo"  >Agregar producto a catalogo</button>
                                         </div> 
                                     </form> 
                                    
                                </div>
                        </div>
                        <div class="card mb-4 pt-4">
                                       
                                    <%   if(request.getAttribute("errores")!=null){ 
                                        Map<String,String> erroresMap=(Map<String,String>)request.getAttribute("errores"); 
                                        if(erroresMap.size()>0){
                                    %>
                                        <ul class="alert alert-danger mx-5 px-5">
                                        <% for(String error: erroresMap.values()){ %>
                                        <li><%out.print(error);%> </li>
                                        <% } %>
                                        </ul>
                                    <% }
                                    }%>
                        </div>
                        </div> 
                        </div>
                        <div class="row justify-content-center my-5 row">
                            <div class="col-10">
                            <table id="example" class="display" style="width:100%">
                                     <%    if(request.getAttribute("listaCatalogo")!=null){
                                            List<Catalogo> lista=(List<Catalogo>)request.getAttribute("listaCatalogo");
                                    %>
                                    <thead>
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Diametro</th>
                                            <th>Keke</th>
                                            <th>Relleno</th>
                                            <th>Precio Unitario</th>
                                             <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Catalogo c : lista){
                                            %>
                                            <tr>
                                                <td><%out.print(c.getNombre()); %></td>
                                                <td><%out.print(c.getDiametro()); %></td>
                                                <td><%out.print(c.getKeke()); %></td>
                                                <td><%out.print(c.getRelleno()); %></td>
                                                <td><%out.print(c.getPrecio_unitario()); %></td>
                                                <td> 
                                                     <a href="ControllerCatalogo?action=delete&idCatalogo=<% out.print(c.getId_producto_catalogo());%>" target="target" class="btn btn-danger">Eliminar</a>
                                                </td>
                                            </tr>
                                         <% } %>
                                    </tbody>
                                    <% }
                                   %>
                                </table>  
                            
                            <div class="col-md-11">
                                
                                  </div>
                                </table>  
                             </div>
                           
                        </div>
                    </div>
                    
                     <!-- [ Layout footer ] Start -->
                <%@include file="Vista/layout/footer.jsp" %>
                    <!-- [ Layout footer ] End -->
                </div>
                <!-- [ Layout content ] Start -->
            </div>
            <!-- [ Layout container ] End -->
        </div>
      <!-- Core scripts -->
       <%@include file="Vista/layout/script.jsp" %>
        <script src="js/GenerarVenta.js"></script>
       <script src="js/bootstrap.bundle.min.js"></script>
       <script src="js/jquery-3.7.0.js"></script>
       <script src="js/jquery.dataTables.min.js"></script>
       <script src="js/FormatoTabla.js"></script>
    
       
    </body>

</html>
