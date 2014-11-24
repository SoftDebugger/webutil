package cn.buglife.data.framework;

import cn.buglife.data.framework.common.PageParam;
import cn.buglife.data.framework.common.PageResult;

import java.util.List;

/**
 * <p>这里主要定义了日常数据库操作会用到的几个方法:1.基本的增删查改;2.分页查询;3.批量操作</p>
 * <p/>
 * Created by CrazyHarry on 2014/11/23.
 */
public interface IDao<T> {

    /**
     * 添加记录
     *
     * @param t
     */
    public void create(Class<T> clazz, T t);

    /**
     * 批量插入记录
     *
     * @param list
     */
    public void batchCreate(Class<T> clazz, List<T> list);

    /**
     * 根据ID查询一个记录
     *
     * @return <code>T</code>
     */
    public T getById(Class<T> clazz, Object id);

    /**
     * 获取所有的记录
     *
     * @return<code>List<T></code>
     */
    public List<T> getAll(Class<T> clazz);

    /**
     * 根据一个属性值查询记录
     *
     * @param column 属性名
     * @param value  属性值
     * @return <code>List<T></></code>
     */
    public List<T> getByColumn(Class<T> clazz, String column, String value);

    /**
     * 分页查询记录
     *
     * @param pageParam 带有页码和页面大小的分页对象
     * @return <code>PageResult</code>分页查询结果集
     */
    public PageResult<T> getAllByPage(Class<T> clazz, PageParam pageParam);

    /**
     * 更新对象
     *
     * @param t 要更新的记录
     */
    public void update(Class<T> clazz, T t);

    /**
     * 删除记录
     *
     * @param t
     */
    public void delete(Class<T> clazz, T t);

    /**
     * 根据Id删除记录
     *
     * @param id
     */
    public void deleteById(Class<T> clazz, Object id);

    /**
     * 根据属性值批量删除记录
     *
     * @param column
     * @param value
     */
    public void batchDeleteByColumn(Class<T> clazz, Object column, String value);
}
