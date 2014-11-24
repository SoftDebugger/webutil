package cn.buglife.data.framework;

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
    private String[] columns;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
}
