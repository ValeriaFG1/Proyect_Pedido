<%-- 
    Document   : reporteVenta
    Created on : 11 dic. 2023, 05:24:41
    Author     : ASUS X
--%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Empleado"%>
<%@page import="Modelo.ReporteFecha"%>
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
                        <h4 class="font-weight-bold py-3 mb-0">Reporte de venta</h4>
                        <div class="text-muted small mt-0 mb-4 d-block breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item">Reporte</li>
                                <li class="breadcrumb-item active">Reporte de venta</li>
                            </ol>
                        </div>
                        <div class="cart mb-4">
                             <div class="form-group row col-8">
                                 <form action="ControllerReporte?action=BuscarReporte" method="post">
                                      <div class="form-group row"> 
                                     <label class="col-sm-4 col-lg-4 col-form-label">Fecha incio:</label>
                                          <div class="col-sm-8 col-lg-8">
                                              <input type="date" id="fecha_inicio" name="fecha_inicio" class="form-control">
                                          </div>
                                      </div> 
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-lg-4 col-form-label">Fecha ultimo:</label>
                                        <div class="col-sm-8 col-lg-8">
                                            <input type="date" id="fecha_ultimo" name="fecha_ultimo" class="form-control">
                                        </div>
                                    </div>
                                     <div class="form-group">
                                        <button type="submit" class="btn btn-success w-100 d-block" id="btnBuscar">Buscar</button>
                                    </div>
                                 </form>
                            
                        </div>
                        
                        <div class="card mb-4 pt-4 col-8">
                                       
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
                            
                            
                            <div class="mx-4 col-md-11">
                                
                           
                                <table id="example" class="display" style="width:100%">
                                     <%    if(request.getAttribute("listaReporte")!=null){
                                            List<ReporteFecha> lista=(List<ReporteFecha>)request.getAttribute("listaReporte");
                                            
                                    %>
                                    <thead>
                                        <tr>
                                            <th>Fecha</th>
                                            <th>Total</th>
                                            <th>Descargar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(ReporteFecha c : lista){
                                            %>
                                            <tr>
                                                <td><%out.print(c.getFechaPedido()); %></td>
                                                <td>S/ <%out.print(c.getTotal_ventas()); %></td>
                                                 <td><a href="#" target="target" class="btn btn-danger">PDF</a></td>
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
</html>
