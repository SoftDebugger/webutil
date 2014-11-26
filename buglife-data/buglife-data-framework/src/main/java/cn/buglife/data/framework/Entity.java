package cn.buglife.data.framework;

import cn.buglife.data.framework.annotation.Column;

import java.util.List;

/**
 * Created by CrazyHarry on 2014/11/24.
 */
public class Entity {
    /**
     * 表名
     */
    private String table;

    /**
     * 表里的所有属性(字段)
     */
    private List<Column> columns;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
