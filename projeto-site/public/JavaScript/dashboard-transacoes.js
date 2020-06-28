onload = function () {
    atualizarGrafico();
    quantidadeMaquinas();
    document.body.style.visibility = "visible";
    local.innerHTML = sessionStorage.localizacao_usuario;
}

var dataInput = dataMonitoracao.value = dataAtualFormatada(); 

function dataAtualFormatada(){
    var data = new Date(),
        dia  = data.getDate().toString(),
        diaF = (dia.length == 1) ? '0'+dia : dia,
        mes  = (data.getMonth()+1).toString(), //+1 pois no getMonth Janeiro começa com zero.
        mesF = (mes.length == 1) ? '0'+mes : mes,
        anoF = data.getFullYear();
    return anoF+"-"+mesF+"-"+diaF;
}

var exibiu_grafico = false;
function botao() {
    console.log(input.value);
}
// só mexer se quiser alterar o tempo de atualização
// ou se souber o que está fazendo!
function atualizarGrafico() {
    obterDadosGrafico();
    setTimeout(atualizarGrafico, 10000);
}

// altere aqui as configurações do gráfico
// (tamanhos, cores, textos, etc)
function configurarGrafico() {
    var configuracoes = {
        responsive: true,
        animation: exibiu_grafico ? false : { duration: 1500 },
        hoverMode: 'index',
        stacked: false,
        title: {
            display: true,
            text: 'Transações/hora'
        },
        hover: {
            mode: 'nearest',
            intersect: true
        },
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Horas'
                }
            }],
            yAxes: [{
                type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                display: true,
                position: 'left',
                id: 'y-temperatura',
                scaleLabel: {
                    display: true,
                    labelString: 'Transações'
                }

            }],
        }
    };
    exibiu_grafico = true;

    return configuracoes;
}

// altere aqui como os dados serão exibidos
// e como são recuperados do BackEnd
function obterDadosGrafico() {

    // neste JSON tem que ser 'labels', 'datasets' etc, 
    // porque é o padrão do Chart.js
    var dados = {
        labels: [],
        datasets: [
            {
                yAxisID: 'y-temperatura',
                label: 'Transações',
                borderColor: window.chartColors.purpleAlprime,
                backgroundColor: window.chartColors.purpleAlprime,
                fill: false,
                data: []
            }
        ]
    };
    var horasFormatado = [];
    var valoresFormatado = [];
    var horas = [];
    var valores = [];
    fetch(`/vendas/usuarios-hora/${dataInput}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (resposta) {

                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

                resposta.reverse();

                for (i = 0; i < resposta.length; i++) {
                    var registro = resposta[i];

                    // aqui, após 'registro.' use os nomes 
                    // dos atributos que vem no JSON 
                    // que gerou na consulta ao banco de dados
                    horas.push(registro.hora);
                    valores.push(registro.totalUsuario);


                }
                console.log(JSON.stringify(dados));

                var indice = 0;
                while (indice < 24) {
                    var horaBanco = null;
                    var valorBanco = null;
                    var jaSelecionado = false;
                    for (var i = 0; i < horas.length; i++) {
                        if (indice == horas[i]) {
                            console.log("horas[i]: " + horas[i]);
                            horaBanco = horas[i];
                            valorBanco = valores[i];
                            jaSelecionado = true;
                        }
                    }
                    if (horaBanco != null) {
                        // valoresFormatado.push(valorBanco);
                        // horasFormatado.push(horaBanco);
                        dados.labels.push(horaBanco);
                        dados.datasets[0].data.push(valorBanco);
                    } else {
                        // horasFormatado.push(indice);
                        //     valoresFormatado.push(0);
                        dados.labels.push(indice);
                        dados.datasets[0].data.push(0);
                    }
                    indice++;

                }
                console.log(dados.labels);
                console.log(dados.datasets[0].data);
                plotarGrafico(dados);
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
        .catch(function (error) {
            console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
        });

}

// só altere aqui se souber o que está fazendo!
function plotarGrafico(dados) {
    console.log('iniciando plotagem do gráfico...');

    var ctx = canvas_grafico.getContext('2d');
    window.grafico_linha = Chart.Line(ctx, {
        data: dados,
        options: configurarGrafico()
    });
}

function quantidadeMaquinas(){
    var  usuario = sessionStorage.fk_localizacao
    fetch(`/registros/maquinas/${usuario}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            totalToten.innerHTML = 0;
            response.json().then(function (resposta) {

                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

                for (i = 0; i < resposta.length; i++) {
                    var registro = resposta[i];

                    totalToten.innerHTML++;
                    gerarTabela(registro.id_maquina);
                }
                
                resposta.reverse();
            });
        } else {
            alert('erro ao pegar os dadosda tabela ');
        }
    })
}

function gerarTabela(maquina) {
    var totalTrasa=0, Trasacoes=0,horaLuc = 0;
    console.log(`esse é o valor de res: ${maquina}`);
    fetch(`/vendas/tabela/${maquina}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (resposta) {

                console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

                for (i = 0; i < resposta.length; i++) {
                    var registro = resposta[i];

                    if (registro.valor > horaLuc){
                        HoraLucrativa.innerHTML = registro.data_hora
                    }
                    Trasacoes += registro.valor;

                }
                totalTrasa = Number(totalTransaçoe.innerHTML) + Trasacoes;
                totalTransaçoe.innerHTML = totalTrasa.toFixed(2);
                TotalPorHora.innerHTML = (totalTrasa/20).toFixed(2);
                resposta.reverse();
            });
        } else {
            alert('Erro ao pegar os dados da tabela ');
        }
    })
}

function mudarDta(){
    atualizarGrafico();
}

function mostra(){

}