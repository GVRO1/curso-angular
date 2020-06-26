/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alprime.bancoDados;

//import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConexaoBD {

    private BasicDataSource dataSource;

    public ConexaoBD() {
        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.dataSource.setUrl("jdbc:mysql://3.92.85.73:3306/alprime?useSSL=false");
        this.dataSource.setUsername("root");
        this.dataSource.setPassword("urubu100");

    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    
}
