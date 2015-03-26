package cn.buglife.data.framework.handler;

import cn.buglife.data.framework.RecordProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by CrazyHarry on 2014/12/3.
 */
public class BeanListHandler<T> extends RSHandler<List<T>>{
    private Class<T> type;

    private RecordProcessor convert;

    public BeanListHandler(Class<T> type) {
        this.type = type;
    }

    public BeanListHandler(Class<T> type, RecordProcessor convert) {
        this.type = type;
        this.convert = convert;
    }

    @Override
    public List<T> handle(ResultSet rs) throws SQLException {
        return this.convert.toBeanList(rs,type);
    }
}
