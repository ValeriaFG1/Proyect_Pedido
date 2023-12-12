
function iniciarApp(){
    const fileImg=document.querySelector("#fileImg");
    if(fileImg){
        fileImg.addEventListener('change',visualizarImagen);
    }else{
        console.log("No existe");
    }
    function visualizarImagen(e){
        console.log("Estoy aqui");
        if(e.target.files[0]){
             const imagenResultado=document.querySelector("#imagenResultado");
                           const imagen=document.createElement('img');
                           imagen.width=500;
                           imagen.height=400;
                           imagen.style.paddingLeft = '40px'; 
                           imagen.style.paddingTop = '15px'; 
                           const reader= new FileReader();
                           reader.onload = function (e){
                               imagen.src=e.target.result;
                           }
                           reader.readAsDataURL(e.target.files[0]);
                           imagenResultado.appendChild(imagen);
         }
    }
    
}
document.addEventListener('DOMContentLoaded',iniciarApp);