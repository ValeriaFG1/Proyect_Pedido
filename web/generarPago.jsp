<%-- 
    Document   : generarPago
    Created on : 29 nov. 2023, 05:56:56
    Author     : ASUS X
--%>
<%@page import="Modelo.Cliente"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Vista/layout/head.jsp" %>
              <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
              <link rel="stylesheet" href="assets/css/pages/styleN.css">
              <link rel="stylesheet" href="assets/css/tailwind.min.css">
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
               
                <div class="container ml-4"> 
                    
                      <div class="col-md-6 agregar-cita">
                    <form action="CustomerServlet"  method="post">
                        <legend class="mb-4 text-center mt-4">Datos de pago</legend>
                        <div class="form-group row">
                            <label class="col-sm-4 col-lg-4 col-form-label">DNI Cliente:</label>
                            <div class="col-sm-8 col-lg-8">
                                <input type="text" id="DNICliente" name="DNICliente" class="form-control" placeholder="DNI cliente">
                            </div>
                        </div>
                         <div class="form-group row">
                             <div class="col-md-6">
                                  <button type="submit" class="btn btn-primary w-100 d-block" name="action" value="buscarCliente">Buscar cliente</button>
                             </div>    
                             <div class="col-md-6">
                                 <a href="CustomerServlet?action=irACrearCliente" class="btn btn-danger w-100 d-block" target="target">Registrar cliente</a>
                             </div>
                        </div>
                        <div class="row form-group">
                             <%   if(request.getAttribute("errores")!=null){ 
                                        Map<String,String> erroresMap=(Map<String,String>)request.getAttribute("errores"); 
                                        if(erroresMap.size()>0){
                                    %>
                                        <ul class="alert alert-danger mx-3 px-3">
                                        <% for(String error: erroresMap.values()){ %>
                                        <li><%out.print(error);%> </li>
                                        <% } %>
                                        </ul>
                                    <% }
                                    }%>
                        </div>
                      
                      <% if(request.getAttribute("clienteEncontrado")!=null){
                                Cliente cliente= new Cliente();
                                cliente=(Cliente)request.getAttribute("clienteEncontrado");
                       %>
                        <div >
                            <input type="hidden" name="id_cliente" value="<%out.print(cliente.getId_cliente()); %>">
                        </div>
                         <div class="form-group row">
                            <label class="col-sm-4 col-lg-4 col-form-label">Nombre Cliente:</label>
                            <div class="col-sm-8 col-lg-8">
                                <input type="text" id="nombreCliente" name="nombreCliente" class="form-control" readonly="" value="<%out.print(cliente.getNombre()+" "+cliente.getApellido()); %>">
                            </div>
                        </div> 
                        <div class="form-group row">
                            <label class="col-sm-4 col-lg-4 col-form-label">Fecha de pedido:</label>
                            <div class="col-sm-8 col-lg-8">
                                <input type="date" id="fecha_pedido" name="fecha_pedido" class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-lg-4 col-form-label">Fecha entrega:</label>
                            <div class="col-sm-8 col-lg-8">
                                <input type="date" id="fecha_entrega" name="fecha_entrega" class="form-control">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-4 col-lg-4 col-form-label">Hora:</label>
                            <div class="col-sm-8 col-lg-8">
                                <input type="time" id="hora" name="hora" class="form-control">
                            </div>
                        </div>
                         <div class="form-group row">
                            <label class="col-sm-4 col-lg-4 col-form-label">Total a pagar:</label>
                            <div class="col-sm-8 col-lg-8">
                                <input type="tel" id="totalPagar" name="totalPagar" class="form-control"  value="<% if(request.getAttribute("totalPagar")!=null){ 
                                double totalPagar=(double)request.getAttribute("totalPagar");
                                            if(totalPagar!=0){
                                            out.print(totalPagar);
                                }
                                } %>" readonly> 
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-lg-4 col-form-label">Acuenta:</label>
                            <div class="col-sm-8 col-lg-8">
                                <input type="tel" id="acuenta" name="acuenta" class="form-control"  placeholder="00">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-lg-4 col-form-label">Saldo:</label>
                            <div class="col-sm-8 col-lg-8">
                                <input type="tel" id="saldo" name="saldo" class="form-control"  placeholder="00" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success w-100 d-block" disabled="true" id="btnGenerarPago" name="action" value="recepcionarCliente">Generar Pago</button>
                        </div>
                        <div class="" id="errorsaldo">

                        </div>
                      <% } %>
                    </form>
                    <div class="mt-4">
                          
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
                    <script src="js/CalcularSaldo.js" ></script>  
    </body>
    
</html>
