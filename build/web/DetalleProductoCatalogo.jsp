<%-- 
    Document   : DetalleProductoCatalogo
    Created on : 10 dic. 2023, 10:16:32
    Author     : ASUS X
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Catalogo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detaller Catalogo Producto</title>
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
                                <h6 class="card-header text-center"> <span class="h1 font-weight-bold"> Detalle de tortas </span> </h6>
                                <div class="no-gutters row-bordered row-border-light h-100">
                                   <div class="row product-view justify-content-lg-between my-4">
                                            <% if(request.getAttribute("catalogo")!=null){
                                                      Catalogo c=(Catalogo)request.getAttribute("catalogo");
                                                 %>
                                                 <form  method="post" id="product_addtocart_form" action="ControllerCatalogo?action=agregarProductoCarrito"> 
                                                 <div class="row"> 
                                                 <div class="product-img-box col-7">
                                             
                                                  <div class="card-img-top"> <img id="product-zoom" src="imgCatalogo/<%out.print(c.getImagen());%>"
                                                       data-zoom-image="imgCatalogo/<%out.print(c.getImagen());%>" alt="product-image"   class="img-fluid w-100"/> 
                                                  </div>
                                                
                                                 <!-- end: more-images -->
                                               </div>
                                               <!--End For version 1,2,6-->
                                               <!-- For version 3 -->
                                               <div class="product-shop col-5">
                                                 <div class="product-next-prev"> <a class="product-next" href="#"><span></span></a> <a class="product-prev"
                                                     href="#"><span></span></a> </div>
                                                 <div class="brand">PRODUCTO</div>
                                                 <div class="product-name">
                                                   <h1> <%out.print(c.getNombre());%> </h1>
                                                 </div>
                                                 <div class="ratings">
                                                   <div class="rating-box">
                                                     <div style="width:70%" class="rating"></div>
                                                   </div>
                                                 </div>
                                                 <div class="price-block">
                                                   <div class="price-box">   
                                                       
                                                       <p class="special-price"> <span class="price-label">Precio Especial</span> <span id="product-price-48"
                                                       class="price"> S/ <% out.print(c.getPrecio_unitario()); %>0 </span> </p>
                                                        
                                                   </div>
                                                 </div>
                                               
                                                 <div class="short-description">
                                                     <div class="row">
                                                         <div class="col-3">
                                                             <p><strong>Descripción: </strong> </p>
                                                         </div>
                                                         <div class="col-7">
                                                             <p>  <% out.print(c.getDescripcion());%>  </p>
                                                         </div>
                                                     </div>
                                                     <div class="row">
                                                         <div class="col-3">
                                                             <p><strong>Keke: </strong> </p>
                                                         </div>
                                                         <div class="col-7">
                                                             <p>  <%out.print(c.getKeke());%>  </p>
                                                         </div>
                                                     </div>
                                                      <div class="row">
                                                         <div class="col-3">
                                                             <p><strong>Relleno: </strong> </p>
                                                         </div>
                                                         <div class="col-7">
                                                             <p>  <%out.print(c.getRelleno());%>  </p>
                                                         </div>
                                                     </div>
                                                          <div class="row">
                                                         <div class="col-3">
                                                             <p><strong>Relleno: </strong> </p>
                                                         </div>
                                                         <div class="col-7">
                                                             <p>  <%out.print(c.getDiametro());%>  </p>
                                                         </div>
                                                     </div>
                                                 </div>
                                                           <div class="add-to-box">
                                                 
                                                 <div class="add-to-cart">
                                                     <div class="pull-left col-5 py-2">
                                                      <div class="custom pull-left font-weight-bold">    
                                                        <button
                                                          onclick="var result = document.getElementById('qty'); var qty = result.value; if( !isNaN( qty ) &amp;&amp; qty > 0 ) result.value--;return false;"
                                                          class="reduced items-count" type="button"><i class="fa fa-minus">&nbsp;</i></button>
                                                         <input type="hidden" name="nombre" value="<%out.print(c.getNombre());%>">
                                                         <input type="hidden" name="imagen" value="<%out.print(c.getImagen());%>">
                                                        <input type="hidden" name="id_producto_catalogo" value="<%out.print(c.getId_producto_catalogo());%>">
                                                        <input type="text" class="input-text qty" title="Qty" value="1" maxlength="12" id="qty"  name="cantidad">
                                                        <button
                                                          onclick="var result = document.getElementById('qty'); var qty = result.value; if( !isNaN( qty )) result.value++;return false;"
                                                          class="increase items-count" type="button"><i class="fa fa-plus">&nbsp;</i></button>
                                                       </div>
                                                     </div>
                                                         
                                                     <button class="button btn-cart" title="Add to Cart"
                                                             type="submit" >Agregar al carrito</button>
                                                   </div>
                                                 </div>
                                                 
                                                 </div>
                                               </div>
                                               
                                             </form>
                                                         
                                            <% } %>




                                           </div>
                                    
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
