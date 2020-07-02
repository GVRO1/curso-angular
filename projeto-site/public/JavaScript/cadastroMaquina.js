onload = function () {
  document.body.style.visibility = "visible"
  estacaoMaquina.innerHTML = sessionStorage.localizacao_usuario;
}

function mostrarSenha() {
  var x = document.getElementById("senha");
  if (x.type === "password") {
    x.type = "text";
  }
  else {
    x.type = "password";
  }
}

function enviarMaquina() {
  var formulario = new URLSearchParams(new FormData(form_maquina));
  var fk_localizacao = sessionStorage.fk_localizacao;
  fetch(`/maquinas/cadastrar/${fk_localizacao}`,
    {
      method: "POST",
      body: formulario,
    }).then((resposta) => {
      if (resposta.ok) {
        resposta.json().then((json) => {
          console.log(json)
          console.log("Cadastro realizado!")
          alert("Cadastrado com sucesso");
        });
      } else {
        console.log("Erro de cadastro!");
        console.log(resposta);
        resposta.text().then((texto) => {
          console.error(texto);
          // finalizar_aguardar(texto);
        });
      }
    });
}