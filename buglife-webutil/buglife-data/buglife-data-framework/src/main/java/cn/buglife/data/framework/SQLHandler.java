package cn.buglife.data.framework;

import org.apache.commons.dbutils.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.*;

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

        // nothing to do here
        if (params == null) {
            return;
        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                stmt.setObject(i + 1, params[i]);
            } else {
                // VARCHAR works with many drivers regardless
                // of the actual column type. Oddly, NULL and
                // OTHER don't work with Oracle's drivers.
                int sqlType = Types.VARCHAR;
                if (!pmdKnownBroken) {
                    try {
                        /*
                         * It's not possible for pmdKnownBroken to change from
                         * true to false, (once true, always true) so pmd cannot
                         * be null here.
                         */
                        sqlType = pmd.getParameterType(i + 1);
                    } catch (SQLException e) {
                        pmdKnownBroken = true;
                    }
                }
                stmt.setNull(i + 1, sqlType);
            }
        }
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
            //TODO this.rethrow(e, sql, params);
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

}
