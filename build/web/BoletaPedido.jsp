<%-- 
    Document   : BoletaPedido
    Created on : 8 dic. 2023, 12:06:27
    Author     : ASUS X
--%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Pedido"%>
<%@page import="Modelo.Empleado"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Vista/layout/head.jsp" %>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/css/pages/styleN.css">
        <link rel="stylesheet" href="assets/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title>Boleta de pedido</title>
    </head>
    <% Cliente cliente=(Cliente)request.getAttribute("cliente");
       Pedido pedido=(Pedido)request.getAttribute("pedido");
       Empleado empleado=(Empleado)request.getAttribute("empleado");
       List<Producto> lista=(List<Producto>)request.getAttribute("listaCarrito");
       double subTotal=0;
       double IGV=0;
       double total=0;
    %>
    <body class="row d-flex justify-content-center">
        <div class="col-10">
            <div class="container pt-3">
                <div class="row">
                    <div class="col-2 pt-4 px-4">
                        <img src="reportesJasper/img/images.png" width="150rem" height="80rem">
                    </div>
                    <div class="col-7">
                        <div class="row">
                            <h1>Pasteleria Dominick</h1>
                        </div>
                        <div class="row">
                            <span>Dirección: Av. las palmeras - Los Olivos - Lima / Email: n71480431@gmail.com</span> 
                          
                             
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <span>Fecha de entrega: <%if(pedido!=null){
                                    out.print(pedido.getFecha_hora_entrega());
                                    } %>  </span>
                            </div>
                             <div class="col-md-6">
                                 <span>Fecha de pedido: <% 
                                     out.print(pedido.getFecha_hora_pedido());
                                     %></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="cuadroBoleta text-center">
                            <span class=""> <strong> RUC: 779929788 </strong> </span> <br>
                            <span> <strong>Bolta de Venta</strong> </span> <br>
                             <span> <strong>N° 00<% if(request.getAttribute("id_pedido")!=null){
                                        out.print(request.getAttribute("id_pedido"));
                                } %> </strong> </span>
                        </div>
                 
                    </div>
                </div>
                
                <div class="row my-4">
                    <div class="row">
                    <div class="col-4">
                        Nombre Cliente: <%if(cliente!=null){
                                out.print(cliente.getNombre()+" "+cliente.getApellido());
                            } %>
                    </div>
                     <div class="col-4">
                        Telefono Cliente: <% if(cliente!=null){
                                out.print(cliente.getTelefono());
                            } %>
                    </div>
                     <div class="col-4">
                        Correo Cliente: <% if(cliente!=null){
                                out.print(cliente.getCorreo());
                            } %>
                     </div>
                    </div>
                    <div class="row mt-4 ">
                      <div class="col-4">
                        Nombre empleado: <% if(empleado!=null){
                                out.print(empleado.getNombre()+" "+empleado.getApellido());
                            } %>
                     </div>
                        <div class="col-4">
                        Telefono empleado: <% if(empleado!=null){
                                out.print(empleado.getTelefono());
                            } %>
                     </div>
                    </div>
                </div>
                <hr>
                <div class="mt-4" >
                <table class="customers">
                    <thead class="text-center">
                    <tr>
                        <th class="tableHead">Cantidad</th>
                        <th class="tableHead">Descripción</th>
                        <th class="tableHead">Precio Unitario</th>
                 </tr>
                 </thead>
                 <% if(lista!=null && lista.size()>0){
                    for(Producto p: lista){
                        subTotal+=p.getCantidad()*p.getPrecioUnitario();
                 %>
                 <tr>
                   <td><%out.print(p.getCantidad());%></td>
                   <td><%out.print("Pisos: "+p.getPisos()+" Diametros: "+p.getDiametros()+" / "+" Keke: "+p.getKeke()+" / "+" Relleno: "+p.getRellenos()+" / "+" Descripción: "+p.getDescripcion());%></td>
                   <td>S/<% out.print(p.getPrecioUnitario()); %></td>
                 </tr>
                 
                 <% }
                      IGV=subTotal*0.21;
                      total=subTotal+IGV;
                    } %>
               </table>

                </div>
                  <hr>
                <div class="mt-4 row d-flex justify-content-end">
                    <div class="col-3">
                        <span class="font-weight-bold" >Sub total: </span> S/<% out.print(subTotal); %> <br>
                        <span class="font-weight-bold">IGV: </span> S/<% out.print(IGV); %> <br>
                        <span class="font-weight-bold">Precio Total:</span> S/<% out.print(total); %> <br>
                        <span class="font-weight-bold">Saldo: </span>S/<% if(pedido!=null){ out.print(pedido.getAcuenta());} %> <br>
                        <span class="font-weight-bold">Acuenta:</span>S/<% if(pedido!=null){ out.print(pedido.getSaldo());} %> <br>
                    </div>
                </div>
                
               
                 <hr>
                 <div class="row">
                     <div class="col-11">
                         Para mayor información visite todas nuestras redes o consulte nuestra pagina oficial <br><!-- comment -->
                         www.dominik.com.pe
                     </div>
                     <div class="col-1">
                         <img src="reportesJasper/img/images.png" alt="logo" width="100rem" height="50rem" class="pr-4" />
                     </div>
                 </div>
               
                  <div class="mt-4 d-flex justify-content-center">
                    <div class="mx-3">
                        <button class="oculto-impresion btn btn-success miBoton" onclick="imprimir()">Imprimir</button>
                    </div>
                    <div class="mx-3">
                        <a class="oculto-impresion btn btn-primary miBoton" href="CustomerServlet?action=nuevo" target="Nuevo">Nuevo</a>
                        
                    </div>
                   </div>
            </div>
        </div>
    </body>
    
    <script src="js/GenerarBoleta.js"></script>
</html>
