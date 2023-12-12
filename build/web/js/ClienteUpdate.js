
function iniciarAP(){
    const id_cliente=document.querySelector('#id_cliente').value;
    const btnAgregar=document.querySelector('#btnAgregar');
    const id=parseInt(id_cliente);
    if(id!=0){
        //update
        btnAgregar.textContent="MODIFICAR CLIENTE";
        btnAgregar.value="Modificar";
    }else{
        //put
        btnAgregar.textContent="AGREGAR CLIENTE";
        btnAgregar.value="Agregar";
    }
}


document.addEventListener('DOMContentLoaded',iniciarAP);


