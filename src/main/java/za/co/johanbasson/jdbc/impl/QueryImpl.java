package za.co.johanbasson.jdbc.impl;

import za.co.johanbasson.jdbc.DatabaseException;
import za.co.johanbasson.jdbc.Query;
import za.co.johanbasson.jdbc.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryImpl<T> extends Statement<Query> implements Query<T> {

    private boolean autoClose = true;

    public QueryImpl(Connection connection, String statement, boolean autoClose) throws SQLException {
        super(connection, statement);
        this.autoClose = autoClose;
    }

    @Override
    public List<T> queryForList(RowMapper<T> rowMapper) throws SQLException {
        List<T> out = new ArrayList<T>();

        ResultSet rs = null;
        try {
            rs = namedParameterStatement.executeQuery();
            while (rs.next()) {
                out.add(rowMapper.mapRow(rs));
            }
            return out;
        } finally {
            JdbcUtil.close(rs);
            if (autoClose) {
                close();
            }
        }
    }

    @Override
    public Object queryForObject(RowMapper rowMapper) throws SQLException {
        ResultSet rs = null;
        try {
            rs = namedParameterStatement.executeQuery();
            if (rs.next()) {
                return rowMapper.mapRow(rs);
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            if (autoClose) {
                close();
            }
        }
    }

    @Override
    public String queryForString() throws SQLException {
        ResultSet rs = null;
        try {
            rs = namedParameterStatement.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            if (autoClose) {
                close();
            }
        }
    }

    @Override
    public Integer queryForInteger() throws SQLException {
        ResultSet rs = null;
        try {
            rs = namedParameterStatement.executeQuery();
            if (rs.next()) {
                return Integer.valueOf(rs.getInt(1));
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            if (autoClose) {
                close();
            }
        }
    }

    @Override
    public Long queryForLong() throws SQLException {
        ResultSet rs = null;
        try {
            rs = namedParameterStatement.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            if (autoClose) {
                close();
            }
        }
    }

    @Override
    public Date queryForDate() throws SQLException {
        ResultSet rs = null;
        try {
            rs = namedParameterStatement.executeQuery();
            if (rs.next()) {
                return rs.getDate(1);
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            if (autoClose) {
                close();
            }
        }
    }

    @Override
    public Date queryForTimestamp() throws SQLException {
        ResultSet rs = null;
        try {
            rs = namedParameterStatement.executeQuery();
            if (rs.next()) {
                return new Date(rs.getTimestamp(1).getTime());
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            if (autoClose) {
                close();
            }
        }
    }


    @Override
    public Boolean queryForBoolean() throws SQLException {
        ResultSet rs = null;
        try {
            rs = namedParameterStatement.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            if (autoClose) {
                close();
            }
        }
    }

}
