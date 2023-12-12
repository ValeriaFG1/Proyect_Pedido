<%-- 
    Document   : Login
    Created on : 4 dic. 2023, 08:58:24
    Author     : ASUS X
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
  <title>Iniciar Sesion</title>

     <%@include file="Vista/layout/head.jsp" %>
    <!-- Page -->
    <link rel="stylesheet" href="assets/css/pages/authentication.css">
    </head>
    <body>
                    <div class="page-loader">
                     <div class="bg-primary"></div>
                 </div>
                 <!-- [ Preloader ] End -->

                 <!-- [ content ] Start -->
                 <div class="authentication-wrapper authentication-1 ">
                     <div class="col-md-6 px-5 ml-5 pl-4">
                     <div class="authentication-inner py-5">

                         <!-- [ Logo ] Start -->
                         <div class="d-flex justify-content-center align-items-center">
                             <div class="ui-w-60">
                                 <div class="w-100 position-relative">
                                     <img src="reportesJasper/img/images.png" alt="Brand Logo" class="img-fluid">
                                 </div>
                             </div>
                         </div>
                         <!-- [ Logo ] End -->

                         <!-- [ Form ] Start -->
                         <form class="my-5" action="ControllerEmpleado" method="post">
                             <div class="form-group">
                                 <label class="form-label">Usuario</label>
                                 <input type="text" class="form-control" name="usuario">
                                 <div class="clearfix"></div>
                             </div>
                             <div class="form-group">
                                 <label class="form-label d-flex justify-content-between align-items-end">
                                     <span>Password</span>
                                   
                                 </label>
                                 <input type="password" class="form-control" name="clave">
                                 <div class="clearfix"></div>
                             </div>
                             <div class="d-flex justify-content-between align-items-center m-0">
                                 <label class="custom-control custom-checkbox m-0">
                                     <input type="checkbox" class="custom-control-input" name="recuerdame">
                                     <span class="custom-control-label">Recordarme</span>
                                 </label>
                                 <button type="submit" class="btn btn-primary"  name="action" value="loguearse">Ingresar</button>
                             </div>
                         </form>
                         <!-- [ Form ] End -->
                     
                     </div>
                     </div>
                     <div class="col-md-6">
                         <img src="assets/img/pngtree-free-wallpaper-of-cakes-picture-image_2482567.jpg" alt="alt" width="600px" height="600px"/>
                     </div>
                 </div>
                     <!-- Core scripts -->
        <script src="assets/js/pace.js"></script>
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/libs/popper/popper.js"></script>
        <script src="assets/js/bootstrap.js"></script>
        <script src="assets/js/sidenav.js"></script>
        <script src="assets/js/layout-helpers.js"></script>
        <script src="assets/js/material-ripple.js"></script>

        <!-- Libs -->
        <script src="assets/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

        <!-- Demo -->
        
    </body>
</html>
