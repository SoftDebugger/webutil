package cn.buglife.data.framework.annotation;

import java.lang.annotation.*;

/**
 * <p>这里定义了一个描述数据表的注解</p>
 * <p/>
 * Created by CrazyHarry on 2014/11/24.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    public String name();
}
