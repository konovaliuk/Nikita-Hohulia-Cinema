package com.hohulia.cinema.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:mysql://localhost/cinema?useUnicode=true&serverTimezone=UTC&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(100);

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDataSource(){ }
}
