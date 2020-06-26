var express = require("express");
var router = express.Router();
var sequelize = require("../models").sequelize;
var Usuario = require("../models").Usuario;
var Localizacao = require("../models").Localizacao;

let sessoes = [];
const selectQueryType = {
  type: sequelize.QueryTypes.SELECT,
};

/* Recuperar usuário por login e senha */
router.post("/login", function (req, res, next) {
  console.log("Recuperando usuário por login e senha");

  var login = req.body.email; // depois de .body, use o nome (name) do campo em seu formulário de login
  var senha = req.body.senha; // depois de .body, use o nome (name) do campo em seu formulário de login

  let instrucaoSql = `select * from usuario, localizacao where email_usuario='${login}' and senha_usuario='${senha}' and fk_localizacao = id_localizacao`;
  console.log(instrucaoSql);

  sequelize
    .query(instrucaoSql, selectQueryType, {
      model: Usuario,
    })
    .then((resultado) => {
      console.log(`Encontrados: ${resultado.length}`);
      console.log(resultado);

      //Joga os valores para o json
      if (resultado.length == 1) {
        sessoes.push(resultado[0].nomeUsuario);
        console.log("Resultado:", resultado[0].nomeUsuario);
        res.json(resultado[0]);
      } else if (resultado.length == 0) {
        res.status(403).send("Login e/ou senha inválido(s)");
      } else {
        res.status(403).send("Mais de um usuário com o mesmo login e senha!");
      }
    })
    .catch((erro) => {
      console.error(erro);
      res.status(500).send(erro.message);
    });
});

router.post("/cadastrarUsuario", function (req, res, next) {
  console.log("Criando um usuário");
  var nome = req.body.nome;
  var cpf = req.body.cpf;
  var email = req.body.email;
  var senha = req.body.senha;
  var telefone = req.body.telefone;
  const instrucaoSql1 = `
  insert into usuario values(null,'${nome}','${cpf}','${email}','${senha}','${telefone}',null,(select max(id_localizacao) from localizacao));`
  sequelize.query(instrucaoSql1, {
      model: Usuario,
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

router.get("/getUsuario/:cpfUsuario", function (req, res, next) {
  console.log("Recuperando um usuário");
  var cpfUsuario = req.params.cpfUsuario;
  const instrucaoSql1 = `
  select * from usuario where cpf_usuario = ${cpfUsuario};`
  sequelize.query(instrucaoSql1, selectQueryType, {
      model: Usuario,
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

/* Logoff de usuário */
router.get("/sair/:login", function (req, res, next) {
  let login = req.params.login;
  console.log(`Finalizando a sessão do usuário ${login}`);
  let nova_sessoes = [];
  for (let u = 0; u < sessoes.length; u++) {
    if (sessoes[u] != login) {
      nova_sessoes.push(sessoes[u]);
    }
  }
  sessoes = nova_sessoes;
  res.send(`Sessão do usuário ${login} finalizada com sucesso!`);
});

module.exports = router;
