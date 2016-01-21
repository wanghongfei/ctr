package cn.fh.ctr.test;

import cn.fh.ctr.method.CtrMethod;
import cn.fh.ctr.Dispatcher;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whf on 10/10/15.
 */
public class InvokeTest {
    @Test
    public void test() throws Exception {
        Method[] methods = TestBean.class.getDeclaredMethods();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("root-context.xml");
        Dispatcher dispatcher = new Dispatcher(10);
        dispatcher.register("/hello", new CtrMethod("testBean", methods[0], ctx));

        Map<String, String> map = new HashMap<>();
        map.put("hello", "whf");
        dispatcher.invoke("/hello", map);
    }



}
