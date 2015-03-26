package cn.buglife.data.framework.common;

import java.util.List;

/**
 * Created by CrazyHarry on 2014/11/23.
 */
public class PageResult<T> {
    private int total;

    private List<T> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
