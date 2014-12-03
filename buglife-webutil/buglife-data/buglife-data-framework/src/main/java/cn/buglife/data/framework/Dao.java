package cn.buglife.data.framework;

import cn.buglife.data.framework.annotation.Column;
import cn.buglife.data.framework.common.PageParam;
import cn.buglife.data.framework.common.PageResult;
import cn.buglife.data.framework.exception.FWException;
import cn.buglife.data.framework.handler.BeanHandler;
import cn.buglife.data.framework.handler.BeanListHandler;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by CrazyHarry on 2014/11/24.
 */
public abstract class Dao<T> implements IDao<T> {

    private DataSource dataSource;

    @Override
    public void create(Class<T> clazz, T t) throws FWException, NoSuchFieldException, SQLException, IllegalAccessException {
        Entity entity = HandleAnnotation.read(clazz);

        //构造插入语句
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ");
        String table = entity.getTable();
        sql.append(table);
        List<Column> columns = entity.getColumns();
        //获取字段名信息以及字段值
        StringBuffer sb = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for (int i = 0; i < columns.size(); i++) {
            Class tmp = t.getClass();
            Field field = tmp.getField(columns.get(i).name());
            values.append(field.get(t));
            sb.append(columns.get(i).name());
            if (i != columns.size() - 1) {
                sb.append(",");
                values.append(",");
            }
        }
        String tableColumns = sb.toString();
        sql.append(" (");
        sql.append(tableColumns.toString());
        sql.append(")");
        sql.append(" values(");
        sql.append(values.toString());
        sql.append(")");
        new SQLHandler(dataSource).insert(sql.toString(), new BeanHandler<T>(clazz));
    }

    @Override
    public void batchCreate(Class<T> clazz, List<T> list) throws FWException, NoSuchFieldException, SQLException, IllegalAccessException {
        Entity entity = HandleAnnotation.read(clazz);

        //构造插入语句
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ");
        String table = entity.getTable();
        sql.append(table);
        List<Column> columns = entity.getColumns();
        //获取字段名信息以及字段值
        StringBuffer sb = new StringBuffer();
        String tableColumns = sb.toString();
        sql.append(" (");
        sql.append(tableColumns.toString());
        sql.append(")");
        sql.append(" values(");
        StringBuffer values = new StringBuffer();
        if (list != null && list.size() != 0) {
            for (int k = 0; k < list.size(); k++) {
                StringBuffer value = new StringBuffer();
                for (int i = 0; i < columns.size(); i++) {
                    Class tmp = list.get(k).getClass();
                    Field field = tmp.getField(columns.get(i).name());
                    value.append(field.get(list.get(k)));
                    sb.append(columns.get(i).name());
                    if (i != columns.size() - 1) {
                        sb.append(",");
                        value.append(",");
                    }
                }
                if (k != list.size() - 1) {
                    value.append("),");
                } else {
                    value.append(")");
                }
                values.append(value);
            }
        }

        sql.append(values.toString());
        sql.append(";");
        new SQLHandler(dataSource).insertBatch(sql.toString(), new BeanHandler<T>(clazz), null);
    }

    @Override
    public T getById(Class<T> clazz, Object id) {
        return null;
    }

    @Override
    public List<T> getAll(Class<T> clazz) {
        return null;
    }

    @Override
    public List<T> getByColumn(Class<T> clazz, String column, String value) {
        return null;
    }

    @Override
    public PageResult<T> getAllByPage(Class<T> clazz, PageParam pageParam) {
        return null;
    }

    @Override
    public void update(Class<T> clazz, T t) {

    }

    @Override
    public void delete(Class<T> clazz, T t) {

    }

    @Override
    public void deleteById(Class<T> clazz, Object id) {

    }

    @Override
    public void batchDeleteByColumn(Class<T> clazz, Object column, String value) {

    }
}
