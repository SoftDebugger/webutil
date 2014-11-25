package cn.buglife.data.framework;

import cn.buglife.data.framework.annotation.Column;
import cn.buglife.data.framework.annotation.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 处理实体中的注解
 *
 * Created by CrazyHarry on 2014/11/24.
 */
public class HandleAnnotation {

    /**
     * 从注解中读取实体的信息
     */
    public static Entity read(Class clazz) {
        Entity entity = new Entity();
        Table table = (Table) clazz.getAnnotation(Table.class);
        entity.setTable(table.name());

        List<Column> columns = new ArrayList<Column>();
        Field[] fileds = clazz.getDeclaredFields();
        if (fileds != null && fileds.length != 0) {
            for (Field field : fileds) {
                Column column = field.getAnnotation(Column.class);
                columns.add(column);
            }
            entity.setColumns(columns);
        }
        return entity;
    }
}
