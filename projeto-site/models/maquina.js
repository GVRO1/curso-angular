'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Maquina = sequelize.define('maquina',{
		//Nome do campo
		id_maquina: 
		{
			//Quando der erro colocar esse field
			field: "id_maquina",
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},
		cod_maquina: 
		{
			field: "cod_maquina",
			type: DataTypes.STRING,
			allowNull: false
		},
		linha_maquina: 
		{
			field: "linha_maquina",
			type: DataTypes.STRING,
			allowNull: false
		},
		estacao_maquina: 
		{
			field: "estacao_maquina",
			type: DataTypes.STRING,
			allowNull: false
		},
	}, 
	{
		//nome da tabela
		tableName: 'maquina', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Maquina;
};