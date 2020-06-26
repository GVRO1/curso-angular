var express = require("express");
var router = express.Router();
var sequelize = require("../models").sequelize;
var Maquina = require("../models").Maquina;

router.post("/cadastrar/:fk_localizacao", function (req, res, next) {
  console.log("Criando uma máquina");
  var senha = req.body.senha;
  var fk_localizacao = req.params.fk_localizacao;
  const instrucaoSql1 = `
  insert into maquina values(null,'${senha}', null, null,null,false,null,null,null,null,${fk_localizacao});`
  sequelize.query(instrucaoSql1, {
      model: Maquina,
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
