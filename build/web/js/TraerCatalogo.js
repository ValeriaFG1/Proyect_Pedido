
         // Realizar una solicitud AJAX al servlet
  var xhr = new XMLHttpRequest();
  var dataFromServlet;
  xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                // Manejar la respuesta del servidor (que debería ser JSON)
                 dataFromServlet = JSON.parse(xhr.responseText);
              // document.getElementById("result").innerHTML = "Datos recibidos: " + JSON.stringify(dataFromServlet);
             // document.getElementById("result").innerHTML = "Datos recibidos: " + dataFromServlet;
               
          const resultList = document.querySelector("#products-grid");
                // Mueve el elemento ul fuera del bucle
                
                dataFromServlet.forEach(producto => {
                const {nombre,imagen,precio_unitario,id_producto_catalogo}=producto;
                resultList.innerHTML+=` 

                                             <li class="item col-4 mt-5">
                                                <div class="item-inner">
                                                  <div class="item-img">
                                                      <div class="item-img-info"><a href="#" title="${nombre}"
                                                                                    class="product-image"><img src="imgCatalogo/${imagen}"
                                                          alt="${nombre}"></a>
                                                        
                                                    </div>
                                                    <div class="d-flex justify-content-center">
                                                            <a class="button btn-cart" src="ControllerCatalogo?action=guardarCarrito&id_pro=${id_producto_catalogo}"  target="target" ><span>Add Carrito</span></a> 
                                                        <form action="#" method="POST">
                                                            <button class="button btn-cart" type="submit" ><span>Ver detalle</span></button> 
                                                        </form>
                                                    </div>
                                                  </div>
                                                  <div class="item-info">
                                                    <div class="info-inner">
                                                      <div class="item-title"><a href="#"
                                                          title="Fresh Organic Mustard Leaves "> ${nombre}</a> </div>
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
                                                          <div class="price-box"><span class="regular-price"><span class="price">S/${precio_unitario}</span>
                                                            </span> </div>
                                                        </div>
                                                      </div>
                                                    </div>
                                                  </div>
                                                </div>
                                              </li>
                                                `;
                    });
                }
        };
        var parametro1 = "listar_Tortas";
       // var parametro2 = "4";
        // Hacer la solicitud GET al servlet
       // var url = "ControllerCatalogo?action=" + encodeURIComponent(parametro1) + "&id_categoria=" + encodeURIComponent(parametro2);
       var url = "ControllerCatalogo?action=" + encodeURIComponent(parametro1);
        xhr.open("GET",url, true);
        xhr.send();
  
