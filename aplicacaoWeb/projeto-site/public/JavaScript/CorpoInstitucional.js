onload = function () {
    document.body.style.visibility = "visible";
    verificar_autenticacao();
}
 
function verificar_autenticacao(){
    login_usuario = sessionStorage.login_usuario;
    
    if (login_usuario != undefined)  {
        log.innerHTML = "Administrar";
        log.setAttribute("onclick","window.location.href='UserADM.html';");
    }
}