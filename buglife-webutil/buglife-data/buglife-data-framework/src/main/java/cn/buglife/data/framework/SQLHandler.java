package cn.buglife.data.framework;

import org.apache.commons.dbutils.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;

/**
 * Created by CrazyHarry on 2014/11/27.
 */
public class SQLHandler {

    private volatile boolean pmdKnownBroken = false;

    private DataSource dataSource;

    public SQLHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 通过占位符动态填充SQL中的变量值.
     *
     * @param stmt
     * @param params
     * @throws SQLException
     */
    private void fillStatement(PreparedStatement stmt, Object... params)
            throws SQLException {

        // check the parameter count, if we can
        ParameterMetaData pmd = null;
        if (!pmdKnownBroken) {
            pmd = stmt.getParameterMetaData();
            int stmtCount = pmd.getParameterCount();
            int paramsCount = params == null ? 0 : params.length;

            if (stmtCount != paramsCount) {
                throw new SQLException("Wrong number of parameters: expected "
                        + stmtCount + ", was given " + paramsCount);
            }
        }

        if (params == null) {
            return;
        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                stmt.setObject(i + 1, params[i]);
            } else {
                int sqlType = Types.VARCHAR;
                if (!pmdKnownBroken) {
                    try {
                        sqlType = pmd.getParameterType(i + 1);
                    } catch (SQLException e) {
                        pmdKnownBroken = true;
                    }
                }
                stmt.setNull(i + 1, sqlType);
            }
        }
    }

    private void rethrow(SQLException cause, String sql, Object... params)
            throws SQLException {

        String causeMessage = cause.getMessage();
        if (causeMessage == null) {
            causeMessage = "";
        }
        StringBuffer msg = new StringBuffer(causeMessage);

        msg.append(" Query: ");
        msg.append(sql);
        msg.append(" Parameters: ");

        if (params == null) {
            msg.append("[]");
        } else {
            msg.append(Arrays.deepToString(params));
        }

        SQLException e = new SQLException(msg.toString(), cause.getSQLState(),
                cause.getErrorCode());
        e.setNextException(cause);

        throw e;
    }

    /**
     * 执行SQL查询
     *
     * @param sql
     * @param rsh
     * @param params
     * @param <T>
     * @return SQL执行的结果集
     * @throws SQLException
     */
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
            throws SQLException {
        Connection conn = this.dataSource.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        T result = null;

        try {
            stmt = conn.prepareStatement(sql);
            this.fillStatement(stmt, params);
            rs = stmt.executeQuery();
            result = rsh.handle(rs);

        } catch (SQLException e) {
            this.rethrow(e, sql, params);
        } finally {
            try {
                FWUtil.close(rs);
            } finally {
                FWUtil.close(stmt);
                FWUtil.close(conn);
            }
        }

        return result;
    }

    /**
     * 执行SQL插入语句
     *
     * @param sql
     * @param rsh
     * @param params
     * @param <T>
     * @throws SQLException
     */
    public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params)
            throws SQLException {
        Connection conn = this.dataSource.getConnection();
        PreparedStatement stmt = null;
        T generatedKeys = null;

        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            this.fillStatement(stmt, params);
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            generatedKeys = rsh.handle(resultSet);
        } catch (SQLException e) {
            this.rethrow(e, sql, params);
        } finally {
            FWUtil.close(stmt);
            FWUtil.close(conn);
        }

        return generatedKeys;
    }

    /**
     * 批量插入
     *
     * @param sql
     * @param rsh
     * @param params
     * @param <T>
     * @throws SQLException
     */
    public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params)
            throws SQLException {
        Connection conn = this.dataSource.getConnection();
        PreparedStatement stmt = null;
        T generatedKeys = null;
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < params.length; i++) {
                this.fillStatement(stmt, params[i]);
                stmt.addBatch();
            }
            stmt.executeBatch();
            ResultSet rs = stmt.getGeneratedKeys();
            generatedKeys = rsh.handle(rs);

        } catch (SQLException e) {
            this.rethrow(e, sql, (Object[]) params);
        } finally {
            FWUtil.close(stmt);
            FWUtil.close(conn);
        }

        return generatedKeys;
    }

    /**
     * 执行更新SQL语句
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int update(String sql, Object... params) throws SQLException {
        Connection conn = this.dataSource.getConnection();

        PreparedStatement stmt = null;
        int rows = 0;

        try {
            stmt = conn.prepareStatement(sql);
            this.fillStatement(stmt, params);
            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            this.rethrow(e, sql, params);

        } finally {
            FWUtil.close(stmt);
            FWUtil.close(conn);
        }

        return rows;
    }

    /**
     * 执行批量操作SQL语句
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int[] batch(String sql, Object[][] params) throws SQLException {
        Connection conn = this.dataSource.getConnection();
        PreparedStatement stmt = null;
        int[] rows = null;
        try {
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                this.fillStatement(stmt, params[i]);
                stmt.addBatch();
            }
            rows = stmt.executeBatch();

        } catch (SQLException e) {
            this.rethrow(e, sql, (Object[]) params);
        } finally {
            FWUtil.close(stmt);
            FWUtil.close(conn);
        }

        return rows;
    }
}
