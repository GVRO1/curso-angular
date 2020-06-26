var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Localizacao = require('../models').Localizacao;
const selectQueryType = {
	type: sequelize.QueryTypes.SELECT
};
router.post("/cadastrarLocalizacao", function (req, res, next) {
	console.log("Criando uma Localização");
	var endereco = req.body.endereco;
	var linha = req.body.linha;
	var estacao = req.body.estacao;
	const instrucaoSql1 = ` insert into localizacao values(null,'${estacao}','${linha}','${endereco}');`;
	sequelize.query(instrucaoSql1, {
		model: Localizacao,
		mapToModel: true,
	  })
	  .then((resultado) => {
		console.log(`Encontrados: ${resultado.length}`);
	    res.json(resultado);
	  })
	  .catch((erro) => {
		console.error(erro);
		res.status(500).send(erro.message);
	  });
  });

  router.get("/getLocalizacao/:estacao", function (req, res, next) {
	console.log("Recuperando uma Localização");
	var estacao = req.params.estacao;
	const instrucaoSql1 = ` select * from localizacao where nome_localizacao = '${estacao}'`;
	sequelize.query(instrucaoSql1,selectQueryType, {
		model: Localizacao,
		mapToModel: true,
	  })
	  .then((resultado) => {
		console.log(`Encontrados: ${resultado.length}`);
	    res.json(resultado);
	  })
	  .catch((erro) => {
		console.error(erro);
		res.status(500).send(erro.message);
	  });
  });

module.exports = router;

