
function iniciarAP(){
    const total=document.querySelector("#totalPagar");
    const acuenta=document.querySelector("#acuenta");
    const saldo=document.querySelector("#saldo");
    const divSaldo=document.querySelector("#errorsaldo");
    const botonAgregar=document.querySelector("#btnGenerarPago");
    if(acuenta){
        acuenta.addEventListener('input',calcularSaldo);
    }
    function calcularSaldo(e){
       const error=document.createElement("p");
       const valor=e.target.value;
       
        if(total){
            if(Number(valor)){
                const valorc=Number.parseFloat(valor);
                const totalc=Number.parseFloat(total.value);
                console.log(`${valorc} - ${totalc}`);
               if( valorc <= totalc && valorc >0){
                 lipiarHTML();
                  const saldoc=totalc-valorc;
                  saldo.value=saldoc;
                   botonAgregar.disabled=false;
               }else{
                lipiarHTML();
                botonAgregar.disabled=true;
                error.textContent="Acuenta debe ser menor al total y mayor a 0";
                divSaldo.appendChild(error);  
                divSaldo.classList.add("alert","alert-danger","px-5");
               }
               
            }else{
                lipiarHTML();
                botonAgregar.disabled=true;
                error.textContent="El acuenta debe ser un numero";
                divSaldo.classList.add("alert","alert-danger","px-5");
                divSaldo.appendChild(error);
            }
        }
    }
    function lipiarHTML(){
        while (divSaldo.firstChild) {
        divSaldo.removeChild(divSaldo.firstChild);
    }
    }
}

document.addEventListener('DOMContentLoaded',iniciarAP);
