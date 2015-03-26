package cn.buglife.webutil.quartz.task;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by CrazyHarry on 2014/11/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring.xml")
public class TaskDemoTest extends TestCase {

    @Test
    public void testExecute(){
        System.out.print("SS");
    }
}
