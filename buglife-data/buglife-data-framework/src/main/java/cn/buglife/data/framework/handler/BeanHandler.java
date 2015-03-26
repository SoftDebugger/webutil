package cn.buglife.data.framework.handler;

import cn.buglife.data.framework.RecordProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by CrazyHarry on 2014/11/30.
 */
public class BeanHandler<T> extends RSHandler<T> {
    private final Class<T> type;

    private final RecordProcessor convert;

    public BeanHandler(Class<T> type) {
        this(type, ArrayHandler.RECORD_PROCESSOR);
    }

    public BeanHandler(Class<T> type, RecordProcessor convert) {
        this.type = type;
        this.convert = convert;
    }

    @Override
    public T handle(ResultSet rs) throws SQLException {
        return rs.next() ? this.convert.toBean(rs, this.type) : null;
    }
}
