package cn.buglife.data.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * <p>简单的 {@link java.sql.Driver},即动态加载JDBC驱动的代理类.</p>
 *
 * Created by CrazyHarry on 2014/11/26.
 */
public final class DriverProxy implements Driver {

    private boolean parentLoggerSupported = true;

    /**
     * The adapted JDBC Driver loaded dynamically.
     */
    private final Driver adapted;

    /**
     * Creates a new JDBC Driver that adapts a JDBC Driver loaded dynamically.
     *
     * @param adapted the adapted JDBC Driver loaded dynamically.
     */
    public DriverProxy(Driver adapted) {
        this.adapted = adapted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return adapted.acceptsURL(url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return adapted.connect(url, info);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMajorVersion() {
        return adapted.getMajorVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMinorVersion() {
        return adapted.getMinorVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return adapted.getPropertyInfo(url, info);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean jdbcCompliant() {
        return adapted.jdbcCompliant();
    }

    /**
     * Java 1.7 method.
     */
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        if (parentLoggerSupported) {
            try {
                Method method = adapted.getClass().getMethod("getParentLogger", new Class[0]);
                return (Logger)method.invoke(adapted, new Object[0]);
            } catch (NoSuchMethodException e) {
                parentLoggerSupported = false;
                throw new SQLFeatureNotSupportedException(e);
            } catch (IllegalAccessException e) {
                parentLoggerSupported = false;
                throw new SQLFeatureNotSupportedException(e);
            } catch (InvocationTargetException e) {
                parentLoggerSupported = false;
                throw new SQLFeatureNotSupportedException(e);
            }
        }
        throw new SQLFeatureNotSupportedException();
    }

}
