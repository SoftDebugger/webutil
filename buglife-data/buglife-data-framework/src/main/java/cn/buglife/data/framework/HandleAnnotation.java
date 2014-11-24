package cn.buglife.data.framework;

import cn.buglife.data.framework.annotation.Table;
import javafx.scene.control.Tab;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CrazyHarry on 2014/11/24.
 */
public class HandleAnnotation {
    /**
     * 获取所有的注解
     *
     * @param clazz 要获取注解的类
     */
    public static List<Annotation> getAll(Class clazz) {
        return Arrays.asList(clazz.getAnnotations());
    }

    /**
     * 从注解中读取实体的信息
     *
     */
    public static Entity read(Class clazz) {
        Entity entity = new Entity();
        Table table = (Table)clazz.getAnnotation(Table.class);
        entity.setTable(table.name());

        return entity;
    }
}
