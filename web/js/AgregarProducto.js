const ModalTitle=document.querySelector('.modal .modal-title');
const ModalBody=document.querySelector('.modal .modal-body');
const respuesModal=document.querySelector('.respuestaModal');
const modalFooter=document.querySelector('.modal-footer');
const divProductos=document.querySelector('#divProductos');

function iniciarApp(){
    const btnAgregar=document.querySelector('#agregarPedido');
    const resultado=document.querySelector('#resultado');
    const selectCategorias=document.querySelector('#categorias');;
    const modal =new bootstrap.Modal('#modal',{});
    if(btnAgregar){
        btnAgregar.addEventListener('click',mostrarRecetaModal);
    }
    if(selectCategorias){
        selectCategorias.addEventListener('change',selecionarCategoria);
    }
    if(divProductos){
        obtenerFavoritos();
    }
    function guardarImagen() {
         imagen.onchange=function(event){
        const archivoSeleccionado = event.target.files[0];
            }
    }
    function mostrarRecetaModal(e){
         e.preventDefault();
        console.log("ingrese al modal")
        modal.show();
        ModalTitle.textContent="Ingrese productos del pedido";
        ModalTitle.classList.add('text-center');
        limpiarHTML(respuesModal);
        limpiarHTML(modalFooter);
        

    }
    function selecionarCategoria(e){
        const idCategoria=Number(e.target.value)  
        limpiarHTML(respuesModal);
        limpiarHTML(modalFooter);
        if(idCategoria==1 || idCategoria==2){
         respuesModal.innerHTML=`                    <div class="d-md-flex justify-content-md-center mt-3 ">
                                 <form class="card col-md-8 bg-light px-5 py-3">
                                     <div class="">
                                        <label for="pisos" class="form-label">Pisos:</label>
                                        <select id="pisos" class="form-select">
                                        <option selected>-- Seleccione --</option>
                                        <option value="Un piso">Un piso</option>
                                        <option value="dos pisos">Dos pisos</option>
                                        <option value="tes pisos">Tres pisos</option>
                                        </select>
                                     </div>
                                     <div class="mt-3">
                                     <label for="diametros" class="form-label">Diametros:</label>
                                    <input type="text"  id="diametros" class="form-control px-3" placeholder="Ejemplo: 18-20-24">
                                     </div>
                                    <div class="mt-3">
                                        <label for="keke" class="form-label">Tipo de keke:</label>
                                        <input type="text"  id="keke" class="form-control px-3" placeholder="chocolate/vainilla">
                                     </div>
                                    <div class="row mb-3">
                                       <label  class="col-form-label">Relleno: </label >
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="fresa" class="form-check-input">
                                         <label class="form-check-label">Fresa</label>
                                       </div>
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="manjar" class="form-check-input">
                                         <label class="form-check-label">Manjar</label>
                                       </div>
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="guanabana" class="form-check-input">
                                         <label class="form-check-label">Guanabana</label>
                                       </div>
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="fudge" class="form-check-input">
                                         <label class="form-check-label">Fudge</label>
                                       </div>
                                       <div class="form-check col-sm-4">
                                         <input type="checkbox" name="relleno" value="ganash" class="form-check-input">
                                         <label class="form-check-label">Ganash</label>
                                       </div>
                                     </div>
                                    <div class="row mt-3">
                                         <label for="precioUnitario" class="form-label">Precio Unitario:</label>
                                           <input type="text"  id="precioUnitario" class="form-control px-3" placeholder="000.00">
                                         </div>
                                         <div class="row mt-3">
                                         <label for="cantidad" class="form-label">Cantidad:</label>
                                           <input type="text"  id="cantidad" class="form-control px-3" placeholder="0">
                                         </div>
                                          <div class="row mt-3">
                                                <label for="descripcion" class="form-label">Descripción</label>
                                                <textarea class="form-control px-3" id="descripcion" rows="3"></textarea>
                                         </div>
                                </form>
                    </div>`;
                //botones de cerrar y de favorito
             const btnFavorito=document.createElement('button');
             btnFavorito.classList.add('btn','btn-danger','col');
             btnFavorito.textContent='Agregar';

             const btnCerrarModal=document.createElement('button');
             btnCerrarModal.classList.add('btn','btn-secondary','col');
             btnCerrarModal.textContent='Cerrar';
             modalFooter.appendChild(btnFavorito);
             modalFooter.appendChild(btnCerrarModal);
             btnCerrarModal.onclick=function(){
                 modal.hide();
                 setTimeout(() => {
                     if(favoritosDIV){
                         location. reload();
                     }
                     return;  
                 }, 1000);
                 }
             
             btnFavorito.onclick=function(){
                    const pisos=document.querySelector('#pisos').value;
                    const diametro=document.querySelector('#diametros').value;
                    var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
                    const precioUnitario=document.querySelector('#precioUnitario').value;
                    const cantidad=document.querySelector('#cantidad').value;
                    const descripcion=document.querySelector('#descripcion').value;
                    const keke=document.querySelector('#keke').value;
                
              
                var rellenos = [];
                    checkboxes.forEach(function(checkbox) {
                            rellenos.push(checkbox.value);
                        });
                 let categoria; 
                 if(idCategoria==1){
                      categoria="Masa elastica";
                  }else{
                      categoria="Torta en chantilly";
                  }
                 const productos=JSON.parse(localStorage.getItem('productos')) ?? [];
                 let id; 
                 if(productos.length){
                      id=productos.length;
                  }else{
                      id=0;
                  }
                  
                  const objProducto={
                        id,
                        categoria,
                        pisos:pisos,
                        keke,
                        diametro:diametro,
                        rellenos: rellenos,
                        precioUnitario,
                        cantidad,
                        descripcion
                    }
                   
                    agregaFavorito(objProducto);  
                    obtenerFavoritos();
             }
         }
    }

    function obtenerFavoritos(){
        const productos=JSON.parse(localStorage.getItem('productos')) ?? [];
        if(productos.length){
            mostrarProductos(productos);
            return;
        }
        const noFavoritos=document.createElement('p');
        noFavoritos.textContent='No hay productos';
        noFavoritos.classList.add('fs-4','text-center','font-bold','mt-5');
        divProductos.appendChild(noFavoritos);
    }
    function mostrarProductos(productos){
        
        limpiarHTML(divProductos);
        const table=document.createElement('table');
        table.classList.add('table');
       // const thead=document.createElement('thead');
        table.innerHTML=`<thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Categoria</th>
          <th scope="col">Pisos</th>
          <th scope="col">Diametro</th>
          <th scope="col">Keke</th>
          <th scope="col">Rellenos</th>
          <th scope="col">Precion Unitario</th>
          <th scope="col">Cantidad</th>
          <th scope="col">Descripción</th>
          <th scope="col">Eliminar</th>
        </tr>
      </thead>`;
    //Iterar los resultados
        const tbody=document.createElement('tbody');
        productos.forEach(producto=>{
            const{id,cantidad,categoria,descripcion,diametro,img,keke,pisos,precioUnitario,rellenos}=producto;
            
            tbody.innerHTML+=`<tr>
                            <th scope="row">${id}</th>
                             <td>${categoria}</td>
                             <td>${pisos}</td> 
                             <td>${diametro}</td> 
                             <td>${keke}</td>
                             <td>${rellenos}</td> 
                             <td>${precioUnitario}</td>
                             <td>${cantidad}</td>
                             <td>${descripcion}</td>
                             <td><button name="eliminar" id="${id}" type="submit" class="btn btn-danger">Eliminar</button></td>
                            
                            </tr>`;
        }

        );
        table.appendChild(tbody);
         divProductos.appendChild(table);
         
         const generarVenta=document.createElement("button");
         generarVenta.innerHTML="Generar Venta";
         generarVenta.type = "submit";
         generarVenta.id= "generarVenta";
         generarVenta.classList.add("btn", "btn-success","align-right", "float-right","mx-4");
         divProductos.appendChild(generarVenta);
         generarVenta.onclick=generarVentaPedido;
    }
    function limpiarHTML(selector){
        while(selector.firstChild){
            selector.removeChild(selector.firstChild);
        }
    }
    function agregaFavorito(producto){
        const favoritos=JSON.parse(localStorage.getItem('productos')) ?? [];
        localStorage.setItem('productos',JSON.stringify([...favoritos,producto]));
    }
    function eliminarFavorito(id){
        const favoritos=JSON.parse(localStorage.getItem('favoritos')) ?? [];
        const nuevoFavorito=favoritos.filter(favorito=>favorito.id !==id);
        localStorage.setItem('favoritos',JSON.stringify(nuevoFavorito));
    }
    function existenStorage(id){
        const favoritos=JSON.parse(localStorage.getItem('favoritos')) ?? [];
        return favoritos.some(favorito=>favorito.id===id);
    }
}

document.addEventListener('DOMContentLoaded',iniciarApp);