<%-- 
    Document   : Cliente
    Created on : 2 dic. 2023, 23:11:46
    Author     : ASUS X
--%>
<%@page import="Modelo.Cliente"%>
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
                        <h4 class="font-weight-bold py-3 mb-0">Gestión de cliente</h4>
                        <div class="text-muted small mt-0 mb-4 d-block breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item">Formulario</li>
                                <li class="breadcrumb-item active">Gestión de cliente</li>
                            </ol>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-6 ">
                            <div class="card mb-4">
                                <h6 class="card-header">Cliente</h6>
                                <div class="card-body">
                                <% Cliente cliente = (Cliente) request.getAttribute("cliente"); %>
                                <form method="post" action="GestionPedido">
                                    <input type="hidden" id="precioTotal" name="precioTotal" value="${totalPagar!=null ? totalPagar:""}"> 
                                    <input type="hidden" id="regresar" name="regresar" value="${Volver!=null ? Volver:""}">  
                                    <div class="form-group">
                                          <input type="hidden"  id="id_cliente" name="id_cliente" value="${cliente != null ? cliente.getId_cliente() : 0}">
                                     </div>
                                    <div class="form-group">
                                        <label class="form-label">Nombre:</label>
                                        <input type="text" class="form-control" placeholder="nombre" name="nombre" value="${cliente != null ? cliente.getNombre() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Apellido:</label>
                                        <input type="text" class="form-control" placeholder="Apellido" name="apellido" value="${cliente != null ? cliente.getApellido() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                     <div class="form-group">
                                        <label class="form-label">Telefono:</label>
                                        <input type="text" class="form-control" placeholder="telefono" name="telefono" value="${cliente != null ? cliente.getTelefono() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                     <div class="form-group">
                                        <label class="form-label">Correo:</label>
                                        <input type="text" class="form-control" placeholder="correo" name="correo" value="${cliente != null ? cliente.getCorreo() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">DNI:</label>
                                        <input type="text" class="form-control" placeholder="DNI" name="DNI" value="${cliente != null ? cliente.getDNI() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                    <button type="submit" class="btn btn-primary" id="btnAgregar" name="action"></button>
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
                        <div class="row justify-content-center my-5">
                            
                            
                            <div class="col-md-11">
                                
                           
                                <table id="example" class="display" style="width:100%">
                                     <%    if(request.getAttribute("listaClientes")!=null){
                                            List<Cliente> lista=(List<Cliente>)request.getAttribute("listaClientes");
                                    %>
                                    <thead>
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Telefono</th>
                                            <th>Correo</th>
                                            <th>DNI</th>
                                             <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Cliente c : lista){
                                            %>
                                            <tr>
                                                <td><%out.print(c.getNombre()); %></td>
                                                <td><%out.print(c.getApellido()); %></td>
                                                <td><%out.print(c.getTelefono()); %></td>
                                                <td><%out.print(c.getCorreo()); %></td>
                                                <td><%out.print(c.getDNI()); %></td>
                                                <td> 
                                                     <a href="GestionPedido?action=delete&idCliente=<% out.print(c.getId_cliente());%>" target="target" class="btn btn-danger">Eliminar</a>
                                                     <a href="GestionPedido?action=update&idCliente=<% out.print(c.getId_cliente());%>" target="target" class="btn btn-primary">Modificar</a>
                                                </td>
                                            </tr>
                                         <% } %>
                                    </tbody>
                                    <% }
                                   %>
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
       <script src="js/bootstrap.bundle.min.js"></script>
       <script src="js/jquery-3.7.0.js"></script>
       <script src="js/jquery.dataTables.min.js"></script>
       <script src="js/FormatoTabla.js"></script>
    
       
    </body>
    <script src="js/ClienteUpdate.js"></script>
</html>
