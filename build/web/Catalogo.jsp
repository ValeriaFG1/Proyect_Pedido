<%-- 
    Document   : TortasMujer
    Created on : 9 dic. 2023, 16:56:48
    Author     : ASUS X
--%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Catalogo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo Mujer</title>
         <%@include file="Vista/layout/head.jsp" %>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/css/pages/styleN.css">
        <link rel="stylesheet" href="assets/css/pages/style.css">
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
                    <div class="row container">
                        <div class="col-md-12">
                            <div class="card d-flex w-100 mb-4 mt-4">
                                <h6 class="card-header text-center"> <span class="h1 font-weight-bold"> CATALOGO DE TORTAS </span> </h6>
                                <div class="no-gutters row-bordered row-border-light h-100">
                                    
                                    <ul class="products-grid row" id="products-grid">
                                        <%    if(request.getAttribute("lista_catalogo")!=null){
                                            List<Catalogo> lista=(List<Catalogo>)request.getAttribute("lista_catalogo");
                                            
                                            for(Catalogo c : lista){
                                            %> 
                                        
                                             <li class="item col-4 mt-5">
                                                <div class="item-inner">
                                                  <div class="item-img">
                                                      <div class="item-img-info"><a href="#" title="<%out.print(c.getNombre()); %>"
                                                                                    class="product-image"><img src="imgCatalogo/<%out.print(c.getImagen()); %>"
                                                          alt="<% out.print(c.getNombre());%>"></a>
                                                        
                                                    </div>
                                                    <div class="d-flex justify-content-center">
                                                        <form action="ControllerCatalogo?action=detalleProducto" method="POST">
                                                            <input type="hidden" name="id_pro" id="id_pro" value="<%out.print(c.getId_producto_catalogo());%>">
                                                            <button class="button btn-cart" type="submit"><span>Ver detalle</span></button> 
                                                        </form>
                                                    </div>
                                                  </div>
                                                  <div class="item-info">
                                                    <div class="info-inner">
                                                      <div class="item-title"><a href="#"
                                                          title="Fresh Organic Mustard Leaves "> <% out.print(c.getNombre()); %></a> </div>
                                                      <div class="item-content">
                                                        <div class="rating">
                                                          <div class="ratings">
                                                            <div class="rating-box">
                                                              <div class="rating" style="width: auto"></div>
                                                            </div>
                                                            <p class="rating-links"><a href="#">  Vistas</a> <span class="separator">|</span>
                                                              <a href="#">agregar una opinión</a> </p>
                                                          </div>
                                                        </div>
                                                        <div class="item-price">
                                                          <div class="price-box"><span class="regular-price"><span class="price">S/<% out.print(c.getPrecio_unitario()); %></span>
                                                            </span> </div>
                                                        </div>
                                                      </div>
                                                    </div>
                                                  </div>
                                                </div>
                                              </li>
                                              <% }
                                                } %>
                                              
                                    </ul>  
                                    </div>               
                            </div>
                        </div>
                    </div>
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
     
    </body>
</html>
