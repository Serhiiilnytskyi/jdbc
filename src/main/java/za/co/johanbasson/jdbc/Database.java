package za.co.johanbasson.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import za.co.johanbasson.jdbc.impl.QueryImpl;
import za.co.johanbasson.jdbc.impl.UpdateImpl;
import za.co.johanbasson.jdbc.impl.TransactionImpl;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 *
 */
public class Database {

    private DataSource dataSource;

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Database(String driver, String url, String username, String password) {
        dataSource = new BasicDataSource();

        ((BasicDataSource) dataSource).setDriverClassName(driver);
        ((BasicDataSource) dataSource).setUrl(url);
        ((BasicDataSource) dataSource).setUsername(username);
        ((BasicDataSource) dataSource).setPassword(password);
    }

    public void close() throws SQLException {
        if (dataSource instanceof BasicDataSource) {
            ((BasicDataSource) dataSource).close();
        }
    }

    public Query query(String statement) throws SQLException {
        return new QueryImpl(dataSource.getConnection(), statement, true);
    }

    public Update update(String statement) throws SQLException {
        return new UpdateImpl(dataSource.getConnection(), statement, true);
    }

    public Transaction startTransaction() throws SQLException {
        return new TransactionImpl(dataSource.getConnection());
    }
}
