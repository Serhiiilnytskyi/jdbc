package za.co.johanbasson.jdbc.impl;

import za.co.johanbasson.jdbc.Update;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateImpl extends Statement<Update> implements Update {

    public UpdateImpl(Connection connection, String statement) throws SQLException {
        super(connection, statement);
    }

    @Override
    public void execute() throws SQLException {
        namedParameterStatement.execute();
        close();
    }

}
