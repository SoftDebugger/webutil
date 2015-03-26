package cn.buglife.data.framework;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by CrazyHarry on 2014/12/2.
 */
public interface RecordProcessor {
    /**
     * 将结果集转换成数组
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    Object[] toArray(ResultSet rs) throws SQLException;

    /**
     * 将结果集转换成封装的bean对象
     *
     * @param rs
     * @param type
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> T toBean(ResultSet rs, Class<T> type) throws SQLException;

    /**
     * 将查询出来的多条记录转换成<code>List</code>
     *
     * @param rs
     * @param type
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> List<T> toBeanList(ResultSet rs, Class<T> type) throws SQLException;

    /**
     * 将查询出来的结果集映射为Map结构的对象
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    Map<String, Object> toMap(ResultSet rs) throws SQLException;
}
