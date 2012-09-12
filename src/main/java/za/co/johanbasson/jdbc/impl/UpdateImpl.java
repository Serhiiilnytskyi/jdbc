package za.co.johanbasson.jdbc.impl;

import za.co.johanbasson.jdbc.Update;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateImpl extends Statement<Update> implements Update {

    private boolean autoClose = true;

    public UpdateImpl(Connection connection, String statement, boolean autoClose) throws SQLException {
        super(connection, statement);
        this.autoClose = autoClose;
    }

    @Override
    public void execute() throws SQLException {
        namedParameterStatement.execute();
        if (autoClose) {
            close();
        }
    }

}
