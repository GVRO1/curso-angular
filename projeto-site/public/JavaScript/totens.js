onload = function () {
  document.body.style.visibility = "visible";
  local.innerHTML = sessionStorage.localizacao_usuario;
  maquinas();
}

var totenzinho, texto;
var nulos = [];

function maquinas() {
  var usuario = sessionStorage.fk_localizacao
  fetch(`/registros/maquinas/${usuario}`, { cache: 'no-store' }).then(function (response) {
    if (response.ok) {
      response.json().then(function (resposta) {

        console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

        for (i = 0; i < resposta.length; i++) {
          var registro = resposta[i];
          if (registro.status == 1) {
            DadosMAquina(registro.id_maquina, registro.capacidade_memoria, registro.status);
          }
          if (registro.status == 0) {
            Completar(registro.id_maquina);
           
          }
        }

        resposta.reverse();
      });
    } else {
      alert('erro ao pegar os Avisos ');
    }
  })

}


function Completar(id_maquina) {
  totenzinho = "totem_morto";
  texto = "Off";
  campoInfoTotens.innerHTML +=
    `<div class="infoToten"> 
  <div class="fotoToten">
    <img src="Imagens/${totenzinho}.png" alt="ERRO">
  </div>
<div class="setaToten">
<img class="seta" src="Imagens/seta-roxa.png" alt="ERRO">
</div>
<div class="dadosToten">
<p>Id-Totem: <span id="idMaquina">${id_maquina}</span> </p>
<p>Status-Totem: <span id="Status">${texto}</span></p>
<p>Transações-Totem: <span id="Transacoes">0</span></p>
<p>Clientes-Atendidos-Totem: <span id="Clientes">0</span> </p>
<p>Capacidade-Memoria: <span id="capacidadeMemoria">0</span> </p>
<p>Ocorrências: <span id="Ocorrencia">0</span></p>
</div>`;

}

function DadosMAquina(id_maquina, capacidade_memoria, status) {
  console.log(id_maquina)
  fetch(`/registros/getDados/${id_maquina}/${sessionStorage.fk_localizacao}`, { cache: 'no-store' }).then(function (response) {
    if (response.ok) {
      response.json().then(function (resposta) {

        console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

        for (i = 0; i < resposta.length; i++) {
          var registro = resposta[i];

          if (status == 1 && registro.Ocorrências == 0) {
            totenzinho = "totem_okay";
            texto = "On";
          } else if (status == 1 && registro.Ocorrências != 0) {
            totenzinho = "totem_parado";
            texto = "On";
          }
          var campo = campoInfoTotens.innerHTML;
          campoInfoTotens.innerHTML = "";

          campoInfoTotens.innerHTML =
            `<div class="infoToten"> 
                        <div class="fotoToten">
                          <img src="Imagens/${totenzinho}.png" alt="ERRO">
                        </div>
                    <div class="setaToten">
                      <img class="seta" src="Imagens/seta-roxa.png" alt="ERRO">
                    </div>
                    <div class="dadosToten">
                      <p>Id-Totem: <span id="idMaquina">${id_maquina}</span> </p>
                      <p>Status-Totem: <span id="Status">${texto}</span></p>
                      <p>Transações-Totem: <span id="Transacoes">${registro.Transacoes}</span></p>
                      <p>Clientes-Atendidos-Totem: <span id="Clientes">${registro.Clientes}</span> </p>
                      <p>Capacidade-Memoria: <span id="capacidadeMemoria">${capacidade_memoria}</span> </p>
                      <p>Ocorrências: <span id="Ocorrencia">${registro.Ocorrências}</span></p>
                    </div>`;
          campoInfoTotens.innerHTML += campo; 


        }
        resposta.reverse();
      });
    } else {
      alert('erro ao pegar as linhas ');
    }
  })
}