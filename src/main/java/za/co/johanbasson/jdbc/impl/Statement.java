package za.co.johanbasson.jdbc.impl;

import za.co.johanbasson.jdbc.DatabaseException;
import za.co.johanbasson.jdbc.Query;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class Statement<T> {

    protected NamedParameterStatement namedParameterStatement;
    private Connection con = null;

    public Statement(Connection connection, String statement) throws SQLException {
        con = connection;
        namedParameterStatement = new NamedParameterStatement(connection, statement);
    }

    public T setString(String name, String value) {
        try {
            namedParameterStatement.setString(name, value);
            return (T)this;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public T setInteger(String name, Integer value) {
        try {
            namedParameterStatement.setInt(name, value);
            return (T)this;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public T setLong(String name, Long value) {
        try {
            namedParameterStatement.setLong(name, value);
            return (T)this;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public T setDate(String name, Date value) {
        try {
            namedParameterStatement.setDate(name, value);
            return (T)this;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public T setBoolean(String name, Boolean value) {
        try {
            namedParameterStatement.setObject(name, value);
            return (T)this;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public T setTimestamp(String name, Date value) {
        try {
            namedParameterStatement.setTimestamp(name, new Timestamp(value.getTime()));
            return (T)this;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void close() {
        if (namedParameterStatement != null) {
            try {
                namedParameterStatement.close();
            } catch (SQLException e) {
                //Ignore
            }

        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                //Ignore
            }
        }
    }

}
