package cn.buglife.data.framework;

import cn.buglife.data.framework.common.PageParam;
import cn.buglife.data.framework.common.PageResult;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by CrazyHarry on 2014/11/24.
 */
public class Dao<T> implements IDao<T> {

    @Override
    public void create(Class<T> clazz, T t) {
        Annotation[] annotations = clazz.getAnnotations();
    }

    @Override
    public void batchCreate(Class<T> clazz, List<T> list) {

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
