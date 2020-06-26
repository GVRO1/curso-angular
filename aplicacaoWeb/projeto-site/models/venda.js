'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Venda = sequelize.define('venda',{
		//Nome do campo
		id_venda: {
			//Quando der erro colocar esse field
			field: "id_venda",
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		data_hora: {
			field: "data_hora",
			type: DataTypes.STRING,
			allowNull: false
		},
		valor: {
			field: "valor",
			type: DataTypes.DOUBLE,
			allowNull: false
		},
		fk_maquina: {
			field: "fk_maquina",
			type: DataTypes.INTEGER,
			allowNull: false
		}
	}, 
	{
		//nome da tabela
		tableName: 'venda', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Venda;
};
