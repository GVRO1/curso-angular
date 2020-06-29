onload = function () {
	document.body.style.visibility = "visible";
	local.innerHTML = sessionStorage.localizacao_usuario;
	maquinas();
}

function maquinas(){
	var usuario = sessionStorage.fk_localizacao
	fetch(`/registros/maquinas/${usuario}`, { cache: 'no-store' }).then(function (response) {
		if (response.ok) {
			tbody.innerHTML = "";
			response.json().then(function (resposta) {

				console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

				for (i = 0; i < resposta.length; i++) {
					var registro = resposta[i];
					boxMaquina.innerHTML += `<option value="${registro.id_maquina}">${registro.id_maquina}</option>` 
				}

				resposta.reverse();
			});
		} else {
			alert('erro ao pegar os Avisos ');
		}
	})

}

function Avisos() {
	var maquina = boxMaquina.value
	fetch(`/registros/avisos/${maquina}`, { cache: 'no-store' }).then(function (response) {
		if (response.ok) {
			tbody.innerHTML = "";
			response.json().then(function (resposta) {

				console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

				for (i = 0; i < resposta.length; i++) {
					var registro = resposta[i];

					tbody.innerHTML +=
						`<tr>
							<td>${registro.id_aviso}</td>
							<td>${registro.categoria}</td>
							<td>${registro.mensagem}</td>
							<td>${registro.data_hora}</td>
							<td><button>Enviar técnico</button></td>
						</tr>`
				}

				resposta.reverse();

				if (tbody.innerHTML == "" && boxMaquina.value != 0){
					tbody.innerHTML +=
						`<tr>
							<td>//</td>
							<td>Morto</td>
							<td>Toten Ainda é inexistente</td>
							<td>//</td>
							<td><button>Enviar técnico</button></td>
						</tr>`
				}
			});
		} else {
			alert('erro ao pegar os Avisos ');
		}
	})
}

function criarTabela() {
	var contador = 0;
	div.innerHTML = '';


	for (contador; contador < numero.value; contador++) {
		div.innerHTML +=
			`<table border="1" class="tabela">
			<tr>
				<td>idTotem</td>
				<td>nomeAviso</td>
				<td>mensagemAviso</td>
				<td>Data/Hora</td>
				<td><button>Enviar técnico</button></td>
			</tr>
		</table>`
	}
}