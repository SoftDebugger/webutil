package cn.buglife.data.framework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by CrazyHarry on 2014/11/30.
 */
public abstract class RSHandler<T> {
    public abstract T handle(ResultSet rs) throws SQLException;
}
