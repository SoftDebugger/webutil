package cn.buglife.common.util;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by CrazyHarry on 2014/12/6.
 */
public abstract class ListMapSortComparator implements Comparator<Map<String, Object>> {

    /**
     * 要进行排序的字段名
     */
    private String key;

    /**
     * asc 或者desc
     */
    private String order;

    public ListMapSortComparator(String key,String order) {
        this.key = key;
        this.order = order;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Map<String, Object> o1, Map<String, Object> o2){
        if (order.equals("asc")) {
            return o1.get(key).toString().compareTo(o2.get(key).toString());
        }else {
            return o2.get(key).toString().compareTo(o1.get(key).toString());
        }
    }

}

