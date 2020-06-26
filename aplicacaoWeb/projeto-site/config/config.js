module.exports = {
  development: {
    dialect: "sqlite",
    storage: "./db.development.sqlite"
  },
  test: {
    dialect: "sqlite",
    storage: ":memory:"
  },
  //Colocar os nossos dados
  
  production: {
  // Banco de Dados na Amazon
    username: 'root',
    password: 'urubu100',
    database: 'alprime',
    host: '3.92.85.73',
    dialect: 'mysql',
    port: "3306",
  // Banco de Dados na Azure
    // username: 'alprimeadmin',
    // password: '#Gfgrupo9',
    // database: 'alprime',
    // host: 'alprime.database.windows.net',
    // dialect: 'mssql',
    xuse_env_variable: 'DATABASE_URL',
    dialectOptions: 
    {
      options: 
      {
        encrypt: true
      }
    },
    pool: 
    { 
      max: 5,
      min: 1,
      acquire: 5000,
      idle: 30000,
      connectTimeout: 5000
    }
  }
};
 
