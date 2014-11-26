package cn.buglife.data.framework;

import java.lang.reflect.Constructor;
import java.sql.*;

import static java.sql.DriverManager.registerDriver;

/**
 * <p><code>FWUtil</code>主要提供了几个在JDBC使用过程中常用的几个操作工具方法</p>
 * <p/>
 * Created by CrazyHarry on 2014/11/23.
 */
public final class FWUtil {
    public FWUtil() {
    }

    /**
     * 验证是否加载了数据库驱动，例如mysql驱动
     *
     * @param loader     类加载器
     * @param driverName 数据库驱动名
     * @return true或者false
     */
    public static boolean loadDriver(ClassLoader loader, String driverName) {
        try {
            Class<?> loadedClass = loader.loadClass(driverName);

            if (!Driver.class.isAssignableFrom(loadedClass)) {
                return false;
            }

            Class<Driver> driverClass = (Class<Driver>) loadedClass;
            Constructor<Driver> driverConstructor = driverClass.getConstructor();

            //使私有化的构造器能够访问
            boolean isConstructorAccessible = driverConstructor.isAccessible();
            if (!isConstructorAccessible) {
                driverConstructor.setAccessible(true);
            }

            try {
                Driver driver = driverConstructor.newInstance();
                registerDriver(new DriverProxy(driver));
            } finally {
                driverConstructor.setAccessible(isConstructorAccessible);
            }

            return true;

            //发生异常时则算加载驱动失败
        } catch (RuntimeException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 加载并且注册数据库驱动程序
     *
     * @param driverName 数据库驱动名
     * @return true 或者false
     */
    public static boolean loadDriver(String driverName) {
        return loadDriver(FWUtil.class.getClassLoader(), driverName);
    }

    /**
     * 关闭jdbc连接
     *
     * @param connection JDBC连接
     * @throws SQLException
     */
    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 在分析完结果集后关闭查询的结果集
     *
     * @param rs
     * @throws SQLException
     */
    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }


    /**
     * 执行完SQL后关闭Statement
     *
     * @param stmt
     * @throws SQLException
     */
    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    /**
     * 对于写数据库写操作提交并关闭事务,当然这里是针对有事务概念的数据库而言的
     *
     * @param conn
     * @throws SQLException
     */
    public static void commitAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.commit();
            } finally {
                conn.close();
            }
        }
    }

    /**
     * 对于数据库写操作,当发生错误或者异常的时候事务回滚并关闭事务
     *
     * @param conn
     * @throws SQLException
     */
    public static void rollbackAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.rollback();
            } finally {
                conn.close();
            }
        }
    }
}
