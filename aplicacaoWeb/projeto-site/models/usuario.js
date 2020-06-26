'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Usuario = sequelize.define('usuario',{
		//Nome do campo
		id_usuario: {
			//Quando der erro colocar esse field
			field: "id_usuario",
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nome_usuario: {
			field: "nome_usuario",
			type: DataTypes.STRING,
			allowNull: false
		},
		cpf_usuario: {
			field: "cpf_usuario",
			type: DataTypes.STRING,
			allowNull: false
		},
		email_usuario: {
			field: "email_usuario",
			type: DataTypes.STRING,
			allowNull: false
		},
		senha_usuario: {
			field: "senha_usuario",
			type: DataTypes.STRING,
			allowNull: false
		},
		telefone: {
			field: "telefone",
			type: DataTypes.STRING,
			allowNull: false
		},chat_id: {
			field: "chat_id",
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		//nome da tabela
		tableName: 'usuario', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Usuario;
};
