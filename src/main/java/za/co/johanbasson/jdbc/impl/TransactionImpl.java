package za.co.johanbasson.jdbc.impl;

import za.co.johanbasson.jdbc.Query;
import za.co.johanbasson.jdbc.Transaction;
import za.co.johanbasson.jdbc.Update;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction {

    private Connection connection;

    public TransactionImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
        JdbcUtil.close(this.connection);
    }

    @Override
    public void rollback() throws SQLException {
        this.connection.rollback();
        JdbcUtil.close(this.connection);
    }

    @Override
    public Query query(String statement) throws SQLException {
        return new QueryImpl(connection, statement, false);
    }

    @Override
    public Update update(String statement) throws SQLException {
        return new UpdateImpl(connection, statement, false);
    }
}
