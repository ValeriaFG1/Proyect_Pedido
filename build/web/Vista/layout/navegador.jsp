<div id="layout-sidenav" class="layout-sidenav sidenav sidenav-vertical bg-dark">
                <!-- Brand demo (see assets/css/demo/demo.css) -->
                <div class="app-brand demo">
                    <span class="app-brand-logo demo">
                        <img src="assets/img/logo.png" alt="Brand Logo" class="img-fluid">
                    </span>
                    <a href="index.html" class="app-brand-text demo sidenav-text font-weight-normal ml-2">Dominik</a>
                    <a href="javascript:" class="layout-sidenav-toggle sidenav-link text-large ml-auto">
                        <i class="ion ion-md-menu align-middle"></i>
                    </a>
                </div>
                <div class="sidenav-divider mt-0"></div>

                <!-- Links -->
                <ul class="sidenav-inner py-1">

                    <!-- Dashboards -->
                    <li class="sidenav-item active">
                        <a href="index.jsp" class="sidenav-link">
                            <i class="sidenav-icon feather icon-home"></i>
                            <div>Dashboards</div>
                            <div class="pl-1 ml-auto">
                                <div class="badge badge-danger">Hot</div>
                            </div>
                        </a>
                    </li>

                    <!-- Layouts -->
                    <li class="sidenav-divider mb-1"></li>
                    <li class="sidenav-header small font-weight-semibold">UI Components</li>
                    <li class="sidenav-item">
                        <a href="CustomerServlet?action=nuevo" class="sidenav-link">
                            <i class="sidenav-icon feather icon-type"></i>
                            <div>Nuevo Pedido</div>
                        </a>
                    </li>

                    <!-- UI elements -->
                    <li class="sidenav-item">
                        <a href="javascript:" class="sidenav-link sidenav-toggle">
                            <i class="sidenav-icon feather icon-box"></i>
                            <div>Catalogo</div>
                        </a>
                        <ul class="sidenav-menu">
                            <li class="sidenav-item">
                                <a href="ControllerCatalogo?action=listar_Tortas" class="sidenav-link">
                                    <div>Catalogo de torta</div>
                                </a>
                            </li>
                            <li class="sidenav-item">
                                <a href="ControllerCatalogo?action=listar" class="sidenav-link">
                                    <div>Gestor de Catalogo</div>
                                </a>
                            </li>
                
                        </ul>
                    </li>

                 <!--    Forms & Tables -->
                    <li class="sidenav-divider mb-1"></li>
                    <li class="sidenav-header small font-weight-semibold">Formularios y reportes</li>
                    <li class="sidenav-item">
                        <a href="javascript:" class="sidenav-link sidenav-toggle">
                            <i class="sidenav-icon feather icon-clipboard"></i>
                            <div>Formularios</div>
                        </a>
                        <ul class="sidenav-menu">
                            <li class="sidenav-item">
                                <a href="GestionPedido?action=listaClientes" class="sidenav-link">
                                    <div>Gestión de cliente</div>
                                </a>
                            </li>
                            <li class="sidenav-item">
                                <a href="ControllerEmpleado?action=listaClientes" class="sidenav-link">
                                    <div>Gestión de empleado</div>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <!--  Icons -->
                    <li class="sidenav-divider mb-1"></li>
                    <li class="sidenav-header small font-weight-semibold">Reportes</li>
                    <li class="sidenav-item">
                        <a href="javascript:" class="sidenav-link sidenav-toggle">
                            <i class="sidenav-icon feather icon-feather"></i>
                            <div>Reportes</div>
                        </a>
                        <ul class="sidenav-menu">
                            <li class="sidenav-item">
                                <a href="reporteVenta.jsp" class="sidenav-link">
                                    <div>Reporte ventas</div>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <!-- Pages -->
                    <li class="sidenav-divider mb-1"></li>
                    <li class="sidenav-header small font-weight-semibold">Pages</li>
                    <li class="sidenav-item">
                        <a href="ControllerEmpleado?action=salir" class="sidenav-link">
                            <i class="sidenav-icon feather icon-user"></i>
                            <div>Cerrar Sesión</div>
                        </a>
                    </li>
                   
                </ul>
 </div>