'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Localizacao = sequelize.define('localizacao',{
		//Nome do campo
		id_localizacao: {
			//Quando der erro colocar esse field
			field: "id_localizacao",
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nome_localizacao: {
			field: "nome_localizacao",
			type: DataTypes.STRING,
			allowNull: false
		},
		tipo_linha: {
			field: "tipo_linha",
			type: DataTypes.STRING,
			allowNull: false
		},
		endereco: {
			field: "endereco",
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		//nome da tabela
		tableName: 'localizacao', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Localizacao;
};
