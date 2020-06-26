process.env.NODE_ENV = 'production';

var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usuariosRouter = require('./routes/usuarios');
var registrosRouter = require('./routes/registros');
var vendasRouter = require('./routes/vendas');
var localizacoesRouter = require('./routes/localizacoes');
var maquinasRouter = require('./routes/maquinas');



var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/usuarios', usuariosRouter);
app.use('/registros', registrosRouter);
app.use('/vendas', vendasRouter);
app.use('/localizacoes', localizacoesRouter);
app.use('/maquinas', maquinasRouter);


module.exports = app;
