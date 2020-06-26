var exibiu_grafico = false;
var id_maquina;
// só mexer se quiser alterar o tempo de atualização
// ou se souber o que está fazendo!
function atualizarGraficos() {
  obterDadosGraficoProcessador();
  obterDadosGraficoMemoria();
  obterDadosGraficoDisco();
  obterDadosGraficoRAM();
  obterDadosGraficoTemperatura();
  
  setTimeout(atualizarGraficos, 1000);
}

// altere aqui as configurações do gráfico
// (tamanhos, cores, textos, etc)
function configurarGraficoProcessador() {
  var configuracoes = {
    responsive: true,
    animation: exibiu_grafico ? false : { duration: 1500 },
    hoverMode: "index",
    stacked: false,
    title: {
      display: true,
      text: "Histórico recente do uso do processador",
    },
    scales: {
      yAxes: [
        {
          type: "linear", // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
          display: true,
          position: "left",
          id: "y-processador",
        },
      ],
    },
  };

  exibiu_grafico = true;

  return configuracoes;
}

// altere aqui como os dados serão exibidos
// e como são recuperados do BackEnd
function obterDadosGraficoProcessador() {
  // neste JSON tem que ser 'labels', 'datasets' etc,
  // porque é o padrão do Chart.js
  var dados = {
    labels: [],
    datasets: [
      {
        yAxisID: "y-processador",
        label: "Processador",
        borderColor: "#6F2C91",
        backgroundColor: "#6F2C91",
        fill: true,
        data: [],
      },
    ],
  };

  fetch(`/registro/ultimos/${id_maquina}`, { cache: "no-store" })
    .then(function (response) {
      if (response.ok) {
        response.json().then(function (resposta) {
          console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

          resposta.reverse();

          for (i = 0; i < resposta.length; i++) {
            var registro = resposta[i];

            // aqui, após 'registro.' use os nomes
            // dos atributos que vem no JSON
            // que gerou na consulta ao banco de dados
            var dataHoraBonita;
            var data = registro.data_hora.split("T")[0];
            var hora = registro.data_hora.split("T")[1];
            var horaBonita = hora.split(".")[0];
            var datas = data.split("-");
            dataHoraBonita = `${horaBonita}`;
            dados.labels.push(dataHoraBonita);

            dados.datasets[0].data.push(registro.porc_processador);
          }
          console.log(JSON.stringify(dados));

          plotarGraficoProcessador(dados);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}

// só altere aqui se souber o que está fazendo!
function plotarGraficoProcessador(dados) {
  console.log("iniciando plotagem do gráfico...");

  var ctx = canvas_grafico_processador.getContext("2d");
  window.grafico_linha = Chart.Line(ctx, {
    data: dados,
    options: configurarGraficoProcessador(),
  });
}

// Grafico de RAM
function configurarGraficoRAM() {
  var configuracoes = {
    responsive: true,
    animation: exibiu_grafico ? false : { duration: 1500 },
    hoverMode: "index",
    stacked: false,
    title: {
      display: true,
      text: "Histórico recente do uso da RAM",
    },
    scales: {
      yAxes: [
        {
          type: "linear", // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
          display: true,
          position: "left",
          id: "y-ram",
        },
      ],
    },
  };

  exibiu_grafico = true;

  return configuracoes;
}

// altere aqui como os dados serão exibidos
// e como são recuperados do BackEnd
function obterDadosGraficoRAM() {
  // neste JSON tem que ser 'labels', 'datasets' etc,
  // porque é o padrão do Chart.js
  var dados = {
    labels: [],
    datasets: [
      {
        yAxisID: "y-ram",
        label: "Ram",
        borderColor: "#6F2C91",
        backgroundColor: "#6F2C91",
        fill: true,
        data: [],
      },
    ],
  };

  fetch(`/registro/ultimos/${id_maquina}`, { cache: "no-store" })
    .then(function (response) {
      if (response.ok) {
        response.json().then(function (resposta) {
          console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

          resposta.reverse();

          for (i = 0; i < resposta.length; i++) {
            var registro = resposta[i];

            // aqui, após 'registro.' use os nomes
            // dos atributos que vem no JSON
            // que gerou na consulta ao banco de dados
            var dataHoraBonita;
            var data = registro.data_hora.split("T")[0];
            var hora = registro.data_hora.split("T")[1];
            var horaBonita = hora.split(".")[0];
            var datas = data.split("-");
            dataHoraBonita = `${horaBonita}`;
            dados.labels.push(dataHoraBonita);

            dados.datasets[0].data.push(registro.porc_ram);
          }
          console.log(JSON.stringify(dados));

          plotarGraficoRAM(dados);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}

// só altere aqui se souber o que está fazendo!
function plotarGraficoRAM(dados) {
  console.log("iniciando plotagem do gráfico...");

  var ctx = canvas_grafico_ram.getContext("2d");
  window.grafico_linha = Chart.Line(ctx, {
    data: dados,
    options: configurarGraficoRAM(),
  });
}

// Grafico de Memória

// altere aqui as configurações do gráfico
// (tamanhos, cores, textos, etc)
function configurarGraficoMemoria() {
  var configuracoes = {
    responsive: true,
    animation: exibiu_grafico ? false : { duration: 1500 },
    hoverMode: "index",
    stacked: false,
    title: {
      display: true,
      text: "Histórico recente do uso da memoria",
    },
    scales: {
      yAxes: [
        {
          type: "linear", // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
          display: true,
          position: "left",
          id: "y-memoria",
        },
      ],
    },
  };

  exibiu_grafico = true;

  return configuracoes;
}

// altere aqui como os dados serão exibidos
// e como são recuperados do BackEnd
function obterDadosGraficoMemoria() {
  // neste JSON tem que ser 'labels', 'datasets' etc,
  // porque é o padrão do Chart.js
  var dados = {
    labels: [],
    datasets: [
      {
        yAxisID: "y-memoria",
        label: "Processador",
        borderColor: "#6F2C91",
        backgroundColor: "#6F2C91",
        fill: true,
        data: [],
      },
    ],
  };

  fetch(`/registro/ultimos/${id_maquina}`, { cache: "no-store" })
    .then(function (response) {
      if (response.ok) {
        response.json().then(function (resposta) {
          console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

          resposta.reverse();

          for (i = 0; i < resposta.length; i++) {
            var registro = resposta[i];

            // aqui, após 'registro.' use os nomes
            // dos atributos que vem no JSON
            // que gerou na consulta ao banco de dados
            var dataHoraBonita;
            var data = registro.data_hora.split("T")[0];
            var hora = registro.data_hora.split("T")[1];
            var horaBonita = hora.split(".")[0];
            var datas = data.split("-");
            dataHoraBonita = `${horaBonita}`;
            dados.labels.push(dataHoraBonita);

            dados.datasets[0].data.push(registro.porc_memoria.toFixed(1));
          }
          console.log(JSON.stringify(dados));

          plotarGraficoMemoria(dados);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}

// só altere aqui se souber o que está fazendo!
function plotarGraficoMemoria(dados) {
  console.log("iniciando plotagem do gráfico...");

  var ctx = canvas_grafico_memoria.getContext("2d");
  window.grafico_linha = Chart.Line(ctx, {
    data: dados,
    options: configurarGraficoMemoria(),
  });
}
// Grafico de Disco

// altere aqui as configurações do gráfico
// (tamanhos, cores, textos, etc)
function configurarGraficoDisco() {
  var configuracoes = {
    responsive: true,
    animation: exibiu_grafico ? false : { duration: 1500 },
    hoverMode: "index",
    stacked: false,
    title: {
      display: true,
      text: "Histórico recente do Disco",
    },
    scales: {
      yAxes: [
        {
          type: "linear", // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
          display: true,
          position: "left",
          id: "y-disco",
        },
      ],
    },
  };

  exibiu_grafico = true;

  return configuracoes;
}

// altere aqui como os dados serão exibidos
// e como são recuperados do BackEnd
function obterDadosGraficoDisco() {
  // neste JSON tem que ser 'labels', 'datasets' etc,
  // porque é o padrão do Chart.js
  var dados = {
    labels: [],
    datasets: [
      {
        yAxisID: "y-disco",
        label: "Disco",
        borderColor: "#6F2C91",
        backgroundColor: "#6F2C91",
        fill: true,
        data: [],
      },
    ],
  };

  fetch(`/registro/ultimos/${id_maquina}`, { cache: "no-store" })
    .then(function (response) {
      if (response.ok) {
        response.json().then(function (resposta) {
          console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

          resposta.reverse();

          for (i = 0; i < resposta.length; i++) {
            var registro = resposta[i];

            // aqui, após 'registro.' use os nomes
            // dos atributos que vem no JSON
            // que gerou na consulta ao banco de dados

            var dataHoraBonita;
            var data = registro.data_hora.split("T")[0];
            var hora = registro.data_hora.split("T")[1];
            var horaBonita = hora.split(".")[0];
            var datas = data.split("-");
            dataHoraBonita = `${horaBonita}`;
            dados.labels.push(dataHoraBonita);

            dados.datasets[0].data.push(registro.porc_disco);
          }
          console.log(JSON.stringify(dados));

          plotarGraficoDisco(dados);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}

// só altere aqui se souber o que está fazendo!
function plotarGraficoDisco(dados) {
  console.log("iniciando plotagem do gráfico...");

  var ctx = canvas_grafico_disco.getContext("2d");
  window.grafico_linha = Chart.Line(ctx, {
    data: dados,
    options: configurarGraficoDisco(),
  });
}
// Grafico de temperatura

// altere aqui as configurações do gráfico
// (tamanhos, cores, textos, etc)
function configurarGraficoTemperatura() {
  var configuracoes = {
    responsive: true,
    animation: exibiu_grafico ? false : { duration: 1500 },
    hoverMode: "index",
    stacked: false,
    title: {
      display: true,
      text: "Histórico recente da temperatura da CPU",
    },
    scales: {
      yAxes: [
        {
          type: "linear", // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
          display: true,
          position: "left",
          id: "y-temperatura",
        },
      ],
    },
  };

  exibiu_grafico = true;

  return configuracoes;
}

// altere aqui como os dados serão exibidos
// e como são recuperados do BackEnd
function obterDadosGraficoTemperatura() {
  // neste JSON tem que ser 'labels', 'datasets' etc,
  // porque é o padrão do Chart.js
  var dados = {
    labels: [],
    datasets: [
      {
        yAxisID: "y-temperatura",
        label: "Temperatura",
        borderColor: "#6F2C91",
        backgroundColor: "#6F2C91",
        fill: true,
        data: [],
      },
    ],
  };

  fetch(`/registro/ultimos/${id_maquina}`, { cache: "no-store" })
    .then(function (response) {
      if (response.ok) {
        response.json().then(function (resposta) {
          console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

          resposta.reverse();

          for (i = 0; i < resposta.length; i++) {
            var registro = resposta[i];

            // aqui, após 'registro.' use os nomes
            // dos atributos que vem no JSON
            // que gerou na consulta ao banco de dados

            var dataHoraBonita;
            var data = registro.data_hora.split("T")[0];
            var hora = registro.data_hora.split("T")[1];
            var horaBonita = hora.split(".")[0];
            var datas = data.split("-");
            dataHoraBonita = `${horaBonita}`;
            dados.labels.push(dataHoraBonita);

            dados.datasets[0].data.push(registro.temp_cpu);
          }
          console.log(JSON.stringify(dados));

          plotarGraficoTemperatura(dados);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}

// só altere aqui se souber o que está fazendo!
function plotarGraficoTemperatura(dados) {
  console.log("iniciando plotagem do gráfico...");

  var ctx = canvas_grafico_temperatura.getContext("2d");
  window.grafico_linha = Chart.Line(ctx, {
    data: dados,
    options: configurarGraficoTemperatura(),
  });
}