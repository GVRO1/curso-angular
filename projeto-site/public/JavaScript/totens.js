onload = function () {
	document.body.style.visibility = "visible";
    local.innerHTML = sessionStorage.localizacao_usuario;
    confirmarIdMaquina()
}

function confirmarIdMaquina(){
    var usuario = sessionStorage.fk_localizacao;
    var totenzinho, texto;
    fetch(`/registros/getMaquina/${usuario}`, { cache: 'no-store' }).then(function (response) {
      if (response.ok) {
        campoInfoTotens.innerHTML = "";
          response.json().then(function (resposta) {

              console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

              for (i = 0; i < resposta.length; i++) {
                  var registro = resposta[i];

                  if(registro.status == 1 && registro.Ocorrências == 0){
                    totenzinho = "totem_okay";
                    texto = "On";
                  } else if (registro.status == 1 && registro.Ocorrências != 0){
                    totenzinho = "totem_parado";
                    texto = "On";
                  }else if(registro.status == 0){
                    totenzinho = "totem_morto";
                    texto = "Off";
                  }

                  campoInfoTotens.innerHTML += 
                    `<div class="infoToten">
                        <div class="fotoToten">
                          <img src="Imagens/${totenzinho}.png" alt="ERRO">
                        </div>
                    <div class="setaToten">
                      <img class="seta" src="Imagens/seta-roxa.png" alt="ERRO">
                    </div>
                    <div class="dadosToten">
                      <p>Id-Totem: <span id="idMaquina">${registro.id_maquina}</span> </p>
                      <p>Status-Totem: <span id="Status">${texto}</span></p>
                      <p>Transações-Totem: <span id="Transacoes">${registro.Transações}</span></p>
                      <p>Clientes-Atendidos-Totem: <span id="Clientes">${registro.Clientes}</span> </p>
                      <p>Capacidade-Memoria: <span id="capacidadeMemoria">${registro.capacidade_memoria}</span> </p>
                      <p>Ocorrências: <span id="Ocorrencia">${registro.Ocorrências}</span></p>
                    </div>`

              }

              resposta.reverse();
          });
      } else {
          alert('erro ao pegar as linhas ');
      }
  })
    
}