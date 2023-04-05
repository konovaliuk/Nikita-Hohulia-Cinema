package com.hohulia.cinema.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlTransaction  {
    public Connection conn;
    public SqlTransaction(Connection conn){
        this.conn = conn;
    }
    public void beginTransaction() throws SQLException {
        conn.setAutoCommit(false);
    }
    public void endTransaction() throws SQLException {
        conn.commit();
        conn.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException {
        conn.rollback();
        conn.setAutoCommit(true);
    }
}
