<%-- 
--%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Empleado"%>
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
                        <h4 class="font-weight-bold py-3 mb-0">Gestión de empleados</h4>
                        <div class="text-muted small mt-0 mb-4 d-block breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item">Forms</li>
                                <li class="breadcrumb-item active">Gestión de empleados</li>
                            </ol>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-6 ">
                            <div class="card mb-4">
                                <h6 class="card-header">EMPLEADOS</h6>
                                <div class="card-body">
                                <% Empleado empleado = (Empleado)request.getAttribute("empleado"); %>
                                <form method="post" action="ControllerEmpleado">
                                      <div class="form-group">
                                          <input type="hidden"  id="id_cliente" name="id_cliente" value="${empleado != null ? empleado.getId_empleado() : 0}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Nombre:</label>
                                        <input type="text" class="form-control" placeholder="nombre" name="nombre" value="${empleado != null ? empleado.getNombre() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Apellido:</label>
                                        <input type="text" class="form-control" placeholder="Apellido" name="apellido" value="${empleado != null ? empleado.getApellido() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                     <div class="form-group">
                                        <label class="form-label">Telefono:</label>
                                        <input type="text" class="form-control" placeholder="telefono" name="telefono" value="${empleado != null ? empleado.getTelefono() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                     <div class="form-group">
                                        <label class="form-label">Correo:</label>
                                        <input type="text" class="form-control" placeholder="correo" name="correo" value="${empleado != null ? empleado.getCorreo() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Nombre Sesion:</label>
                                        <input type="text" class="form-control" placeholder="Nombre sesion" name="NombreSesion" value="${empleado != null ? empleado.getNombre_Sesion() : "" }">
                                        <div class="clearfix"></div>
                                    </div>
                                      <div class="form-group">
                                        <label class="form-label">Clave:</label>
                                        <input type="text" class="form-control" placeholder="Clave" name="Clave" value="${empleado != null ? empleado.getClave() : "" }">
                                        <div class="clearfix"></div>
                                    </div>  
                                         <div class="form-group">
                                             <select id="rol" class="form-select" name="rol">
                                                <option value="1">Administrador</option>
                                                <option value="2">Cajero</option>
                                            </select>
                                        </div>  
                                        <div class="form-group">
                                             <select id="rol" class="form-select" name="estado">
                                                <option value="activo">Activo</option>
                                                <option value="inactivo">Inactivo</option>
                                            </select>
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
                                     <%    if(request.getAttribute("ListaEmpleados")!=null){
                                            List<Empleado> lista=(List<Empleado>)request.getAttribute("ListaEmpleados");
                                            
                                    %>
                                    <thead>
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Telefono</th>
                                             <th>Correo</th>
                                            <th>Estado</th>
                                            <th>Rol</th>
                                            <th>Nombre Usuario</th>
                                             <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Empleado c : lista){
                                            %>
                                            <tr>
                                                <td><%out.print(c.getNombre()); %></td>
                                                <td><%out.print(c.getApellido()); %></td>
                                                <td><%out.print(c.getTelefono()); %></td>
                                                <td><%out.print(c.getCorreo()); %></td>
                                                <td><%out.print(c.getEstado()); %></td>
                                                <td>${c.getRol() == 1 ? "Administrador" : "Cajero" }</td>
                                                <td><%out.print(c.getCorreo()); %></td>
                                                <td> 
                                                     <a href="ControllerEmpleado?action=delete&idCliente=<% out.print(c.getId_empleado());%>" target="target" class="btn btn-danger">Eliminar</a>
                                                     <a href="ControllerEmpleado?action=update&idCliente=<% out.print(c.getId_empleado());%>" target="target" class="btn btn-primary">Modificar</a>
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
         <script src="js/EmpleadoUpdate.js"></script>
      <%@include file="Vista/layout/script.jsp" %>
       <script src="js/bootstrap.bundle.min.js"></script>
       <script src="js/jquery-3.7.0.js"></script>
       <script src="js/jquery.dataTables.min.js"></script>
       <script src="js/FormatoTabla.js"></script>
    
       
    </body>
</html>
