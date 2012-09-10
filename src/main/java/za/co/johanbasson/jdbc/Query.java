package za.co.johanbasson.jdbc;

import za.co.johanbasson.jdbc.impl.QueryImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface Query<T> {

    List<T> queryForList(RowMapper<T> rowMapper) throws SQLException;

    T queryForObject(RowMapper<T> rowMapper) throws SQLException;

    String queryForString() throws SQLException;

    Integer queryForInteger() throws SQLException;

    Long queryForLong() throws SQLException;

    Date queryForDate() throws SQLException;

    Date queryForTimestamp() throws SQLException;

    Boolean queryForBoolean() throws SQLException;

    Query<T> setString(String name, String value) throws SQLException;

    Query<T> setInteger(String name, Integer value) throws SQLException;

    Query<T> setLong(String name, Long value);

    Query<T> setDate(String name, Date value);

    Query<T> setBoolean(String name, Boolean value);

    Query<T> setTimestamp(String name, Date value);

}
