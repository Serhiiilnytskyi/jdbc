package za.co.johanbasson.jdbc;

import java.sql.SQLException;

public interface Transaction {

    void commit() throws SQLException;

    void rollback() throws SQLException;

    Query query(String statement) throws SQLException;

    Update update(String statement) throws SQLException;

}
