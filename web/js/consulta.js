
function filtro(selectTag){
    var divFecha = document.getElementById('formFecha');
    var divCasa = document.getElementById('formNumCasa');
    switch (selectTag.value){
        case "porFecha":
            divCasa.style.display = "none";
            divFecha.style.display = "block";
            break;
        case "porCasa":
            divFecha.style.display = "none";
            divCasa.style.display = "block";
            break;
        default :
            divFecha.style.display = "none";
            divCasa.style.display = "none";
    } 
}
