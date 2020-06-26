create database alprime;
use alprime;

create table usuarios (idUsuario int primary key, nomeUsuario varchar(45),cpfUsuario varchar(45),emailUsuario varchar(45),senhaUsuario varchar(45),tipoUsuario varchar(45));
create table maquina (id_maquina int primary key auto_increment, tipo_processador varchar(45), capacidade_memoria int, sistema_operacional varchar(45));
Alter table maquina add fk_localizacao int ;
create table localizacao (id_localizacao int primary key auto_increment, nome_localizacao varchar(45), tipo_linha varchar(45),endereco varchar(45));
alter table maquina add foreign key (fk_localizacao)references localizacao(id_localizacao) ;
create table registro (id_registro int primary key auto_increment, data_hora varchar(45), porc_processador int, porc_disco int, porc_memoria int);
Alter table registro add fk_maquina int ;
alter table registro add foreign key (fk_maquina)references maquina(id_maquina) ;

insert into usuarios values(2,'diovana','123','diovana@bandtec.com.br','123','comum');
insert into localizacao values (null,'Tucuruvi','azul','Dr. Antônio Maria Laet');
insert into localizacao values (null,'Jabaquara','azul','Rua dos Jequitibás');
insert into localizacao values (null,'Vila Madalena','verde','Dr. Paulo Vieira');
insert into localizacao values (null,'Sacomã','verde','Praça Altemar Dutra');
insert into localizacao values (null,'palmeiras barra funda','vermelho','Bento Teobaldo Ferraz');
insert into localizacao values (null,'corinthians itaquera','vermelho','Cidade Antônio Estêvão de Carvalho');
insert into maquina values (null, "aa", 2,"Windows","1");
insert into registro values (null,"02:10",2,3,4,1);
insert into registro values (null,"03:10",20,43,41,1);

select * from maquina;
select * from localizacao;
select * from registro;


select * from maquina where id_maquina= 1;
select * from registro where fk_maquina =1;
select * from registro, maquina, localizacao where fk_maquina = 1 and fk_maquina = id_maquina and fk_localizacao = id_localizacao;

update maquina set fk_localizacao = 1 where id_maquina = 1;

delete from registro where id_registro >0;
delete from maquina where id_maquina > 5;
select idUsuario, nomeUsuario, cpfUsuario, emailUsuario, senhaUsuario, tipoUsuario from usuarios order by idUsuario desc;


select count(idUsuario) from usuarios;

drop table maquina;
drop table localizacao;
drop table registro;