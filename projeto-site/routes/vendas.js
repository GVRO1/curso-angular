var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Venda = require('../models').Venda;
const selectQueryType = {
	type: sequelize.QueryTypes.SELECT
};

router.get('/usuarios-hora/:dataInput', function(req, res, next) {
	
	// alterar  os nomes da tabela de acordo com o nome da tabela e do nome dos campo
	var dataMonitor = req.params.dataInput;
	const instrucaoSql = `select count(id_venda) as totalUsuario, hour(data_hora) as hora from venda 
	where  DATE_FORMAT (data_hora,'%Y-%m-%d') = '${dataMonitor}' group by hour(data_hora) order by hora desc `;


	sequelize.query(instrucaoSql,selectQueryType, {
		model: Venda,
		mapToModel: true 	
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/relatorio/:startDate/:endDate', function(req, res, next) {
	
	// alterar  os nomes da tabela de acordo com o nome da tabela e do nome dos campo
	var startDate = req.params.startDate ;
	var endDate = req.params.endDate;
	const instrucaoSql = `select sum(valor) as totalDinheiro, count(id_venda) as totalUsuarios from venda where convert(data_hora, date) between ${startDate} and ${endDate};`;
	

	sequelize.query(instrucaoSql,selectQueryType, {
		model: Venda,Model: true 	
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/qntmaquina/:localizacao', function(req, res, next) {
	
	// alterar  os nomes da tabela de acordo com o nome da tabela e do nome dos campo
	var startDate = req.params.startDate ;
	var endDate = req.params.endDate;
	const instrucaoSql = `select * from usuario;`;
	

	sequelize.query(instrucaoSql,selectQueryType, {
		model: Venda,Model: true 	
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/tabela/:data', function(req, res, next) {
	
	// alterar  os nomes da tabela de acordo com o nome da tabela e do nome dos campo
	var startDate = req.params.startDate ;
	var endDate = req.params.endDate;
	const instrucaoSql = `select valor, hour(data_hora) as hora from venda where DATE_FORMAT (data_hora,'%Y-%m-%d') = '${req.params.data}';`;
	

	sequelize.query(instrucaoSql,selectQueryType, {
		model: Venda,Model: true 	
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/tabelaData/:data', function(req, res, next) {

	const instrucaoSql = `select count(DISTINCT(id_maquina)) as totaMaquina, sum(valor) as totalValor, hour(data_hora) as hora from venda, maquina where fk_maquina = id_maquina group by hour(data_hora);`;
	

	sequelize.query(instrucaoSql,selectQueryType, {
		model: Venda,Model: true 	
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/maquinas2/:usuario/:data', function(req, res, next) {
	
	const instrucaoSql = `select count(DISTINCT(id_maquina)), capacidade_memoria, status from maquina, venda where fk_localizacao = ${req.params.usuario} and DATE_FORMAT (data_hora,'%Y-%m-%d') = '${req.params.data}';`;
	sequelize.query(instrucaoSql, selectQueryType, {
		model: Registro,
		mapToModel: true 
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});


module.exports = router;