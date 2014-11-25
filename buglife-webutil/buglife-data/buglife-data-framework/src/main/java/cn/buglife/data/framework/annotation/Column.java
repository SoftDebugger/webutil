package cn.buglife.data.framework.annotation;

import java.lang.annotation.*;

/**
 * <p><code>Column</code>用来作为数据表字段的注解描述字段信息，主要包括属性名，属性长度和默认值</p>
 * Created by CrazyHarry on 2014/11/25.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    /**
     * 表示属性名
     */
    public String name();

    /**
     * 表示属性长度
     */
    public int length();

    /**
     * 属性默认值
     */
    public String value();
}
