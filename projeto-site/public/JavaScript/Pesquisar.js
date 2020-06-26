onload = function () {
    document.body.style.visibility = "visible";

    mostrarLinhas()
}

function mostrarLinhas() {
    fetch(`/registros/Linha`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (resposta) {

                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

                for (i = 0; i < resposta.length; i++) {
                    var registro = resposta[i];

                    Linha.innerHTML += `<option value="${registro.tipo_linha}">${registro.tipo_linha}</option><br>`

                }

                resposta.reverse();
            });
        } else {
            alert('erro ao pegar as linhas ');
        }
    })
}

function estacoes() {
    if (Linha.value == 0) {
        Estacao.innerHTML = `<option value='0'>-- Selecione uma Linha --</option></select>`;
    } else {
        var linha = Linha.value;
        fetch(`/registros/estacoes/${linha}`, { cache: 'no-store' }).then(function (response) {
            if (response.ok) {
                response.json().then(function (resposta) {

                    console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
                    Estacao.innerHTML = "";

                    for (i = 0; i < resposta.length; i++) {
                        var registro = resposta[i];

                        Estacao.innerHTML += `<option value="${registro.nome_localizacao}">${registro.nome_localizacao}</option><br>`
                    }

                    resposta.reverse();
                });
            } else {
                alert('erro ao pegar as estações');
            }
        })
    }

}

function perquisar() {
    if (Linha.value != 0) {
        var Estacoes = Estacao.value;
        var on = 0, off = 0;
        fetch(`/registros/buscar/${Estacoes}`, { cache: 'no-store' }).then(function (response) {
            if (response.ok) {
                response.json().then(function (resposta) {

                    console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

                    for (i = 0; i < resposta.length; i++) {
                        var registro = resposta[i];
                        lblEstacao.innerHTML = `<b class="titulozinho">Estação:</b> ${registro.nome_localizacao}`;
                        fotoLinha.src = `./Imagens/linha-${registro.tipo_linha}.png`;

                        if (registro.status == 1) {
                            on++;
                        } else {
                            off++;
                        }

                    }
                    totalOn.innerHTML = on;
                    totalOff.innerHTML = off;

                    resposta.reverse();
                });
            } else {
                alert('erro ao pegar as estações');
            }
        })
        espaco.style.display = 'none';
        campoInfo.style.display = 'block';
    } else {
        campoInfo.style.display = 'none';
        espaco.style.display = 'block';
    }

}