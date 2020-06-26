'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Registro = sequelize.define('registro',{
		//Nome do campo
		id_registro: {
			//Quando der erro colocar esse field
			field: "id_registro",
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		data_hora: {
			field: "data_hora",
			type: DataTypes.STRING,
			allowNull: false
		},
		porc_processador: {
			field: "porc_processador",
			type: DataTypes.INTEGER,
			allowNull: false
		},
		porc_disco: {
			field: "porc_disco",
			type: DataTypes.INTEGER,
			allowNull: false
		},
		porc_memoria: {
			field: "porc_memoria",
			type: DataTypes.INTEGER,
			allowNull: false
		},porc_ram: {
			field: "porc_ram",
			type: DataTypes.INTEGER,
			allowNull: false
		},temp_cpu: {
			field: "temp_cpu",
			type: DataTypes.INTEGER,
			allowNull: false
		},
		momento_grafico: {
			type: DataTypes.VIRTUAL, // campo 'falso' (não existe na tabela). Deverá ser preenchido 'manualmente' no select
			allowNull: true
		}
	}, 
	{
		//nome da tabela
		tableName: 'registro', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Registro;
};
