<%-- 
    Document   : index
    Created on : 29 oct. 2023, 23:58:58
    Author     : ASUS X
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" class="material-style layout-fixed">
    <head>
        <%@include file="Vista/layout/head.jsp" %>
    </head>
    <body>
        
    <% if(session.getAttribute("sesion")==null){
            %>  
            <script>
                window.location.href = "Login.jsp";
            </script>    
    <%
        }
    %>
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

                <!-- [ Layout content ] Start -->
                <div class="layout-content">

                    <!-- [ content ] Start -->
                    <div class="container-fluid flex-grow-1 container-p-y">
                        <h4 class="font-weight-bold py-3 mb-0">Dashboard</h4>
                        <div class="text-muted small mt-0 mb-4 d-block breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="#">Libreria</a></li>
                                <li class="breadcrumb-item active">Datos</li>
                            </ol>
                        </div>
                        <div class="row">
                            <!-- 1st row Start -->
                            <div class="col-lg-7">
                                <div class="card mb-4">
                                    <div class="card-header with-elements">
                                        <h6 class="card-header-title mb-0">ESTADISTICA</h6>
                                        <div class="card-header-elements ml-auto">
                                            <label class="text m-0">
                                                <span class="text-light text-tiny font-weight-semibold align-middle">SHOW STATS</span>
                                                <span class="switcher switcher-sm d-inline-block align-middle mr-0 ml-2"><input type="checkbox" class="switcher-input" checked><span class="switcher-indicator"><span class="switcher-yes"></span><span
                                                            class="switcher-no"></span></span></span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div id="statistics-chart-1" style="height:270px"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-5">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card mb-4 bg-pattern-2-dark">
                                            <div class="card-body">
                                                <div class="d-flex align-items-center">
                                                    <div class="lnr lnr-gift display-4 text-primary"></div>
                                                    <div class="ml-3">
                                                        <div class="text-muted small">Cantidad Productos del mes</div>
                                                        <div class="text-large">120</div>
                                                    </div>
                                                </div>
                                                <div id="ecom-chart-3" class="mt-3 chart-shadow-primary" style="height:40px"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card mb-4 bg-pattern-2 bg-primary text-white">
                                            <div class="card-body">
                                                <div class="d-flex align-items-center">
                                                    <div class="lnr lnr-cart display-4"></div>
                                                    <div class="ml-3">
                                                        <div class="small">Ventas del mes</div>
                                                        <div class="text-large">S/ 2362.00</div>
                                                    </div>
                                                </div>
                                                <div id="order-chart-1" class="mt-3 chart-shadow" style="height:40px"></div>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                            <!-- 1st row Start -->
                        </div>
                        <div class="row">
                            <!-- 2nd row Start -->
                            <div class="col-md-12">
                                <div class="card d-flex w-100 mb-4">
                                    <div class="row no-gutters row-bordered row-border-light h-100">
                                        <div class="d-flex col-md-6 col-lg-3 align-items-center">
                                            <div class="card-body">
                                                <div class="row align-items-center mb-3">
                                                    <div class="col-auto">
                                                        <i class="lnr lnr-earth text-primary display-4"></i>
                                                    </div>
                                                    <div class="col">
                                                        <h6 class="mb-0 text-muted">Total <span class="text-primary">venta</span></h6>
                                                        <h4 class="mt-3 mb-0">S/ 7652.00<i class="ion ion-md-arrow-round-down ml-3 text-danger"></i></h4>
                                                    </div>
                                                </div>
                                                <p class="mb-0 text-muted">Total de ventas</p>
                                            </div>
                                        </div>
                                        <div class="d-flex col-md-6 col-lg-3 align-items-center">
                                            <div class="card-body">
                                                <div class="row align-items-center mb-3">
                                                    <div class="col-auto">
                                                        <i class="lnr lnr-cart text-primary display-4"></i>
                                                    </div>
                                                    <div class="col">
                                                        <h6 class="mb-0 text-muted"><span class="text-primary">Cantidad</span> Pedidos realizados</h6>
                                                        <h4 class="mt-3 mb-0">6325<i class="ion ion-md-arrow-round-up ml-3 text-success"></i></h4>
                                                    </div>
                                                </div>
                                                <p class="mb-0 text-muted">Cantidad Productos</p>
                                            </div>
                                        </div>
                                        <div class="d-flex col-md-6 col-lg-3 align-items-center">
                                            <div class="card-body">
                                                <div class="row align-items-center mb-3">
                                                    <div class="col-auto">
                                                        <i class="lnr lnr-users text-primary display-4"></i>
                                                    </div>
                                                    <div class="col">
                                                        <h6 class="mb-0 text-muted">Cantidad <span class="text-primary">Clinetes</span></h6>
                                                        <h4 class="mt-3 mb-0">25<i class="ion ion-md-arrow-round-down ml-3 text-danger"></i></h4>
                                                    </div>
                                                </div>
                                                <p class="mb-0 text-muted">Cantidad de clientes</p>
                                            </div>
                                        </div>
                                        <div class="d-flex col-md-6 col-lg-3 align-items-center">
                                            <div class="card-body">
                                                <div class="row align-items-center mb-3">
                                                    <div class="col-auto">
                                                        <i class="lnr lnr-magic-wand text-primary display-4"></i>
                                                    </div>
                                                    <div class="col">
                                                        <h6 class="mb-0 text-muted">Cantidad <span class="text-primary">Productos Catalogo</span></h6>
                                                        <h4 class="mt-3 mb-0">16<i class="ion ion-md-arrow-round-up ml-3 text-success"></i></h4>
                                                    </div>
                                                </div>
                                                <p class="mb-0 text-muted">Cantidad productos Catalogo</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Staustic card 3 Start -->
                            </div>
                            <!-- 2nd row Start -->
                        </div>
                        
                    </div>
                    <!-- [ content ] End -->

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
    </body>
    
</html>