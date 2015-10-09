package cn.fh.ctr.method;

import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * JDK中反射类Method的封装
 * Created by whf on 10/10/15.
 */
public class CtrMethod {
    /**
     * 方法的接收者
     */
    private Object handler;

    /**
     * 方法对象本身
     */
    private Method method;

    /**
     * 构造一个方法对象
     * @param beanName 方法接收者在spring容器中的名字
     * @param method
     * @param ctx
     */
    public CtrMethod(String beanName, Method method, ApplicationContext ctx) {
        this.method = method;
        this.handler = ctx.getBean(beanName);
    }

    /**
     * 调用该方法
     * @param map
     * @return 返回该方法调用的返回值
     */
    public Object invoke(Map<String, String> map) {

        try {
            Object[] params = makeParameters(map);
            return method.invoke(handler, params);

        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();

        }

        return null;
    }

    /**
     * 构造反射调用方法所需要的参数数组
     * @param map
     * @return
     */
    private Object[] makeParameters(Map<String, String> map) {
        // 得到目标方法参数数量
        int tot = method.getParameterCount();
        // 构造参数数组
        Object[] paramArray = new Object[tot];

        // 遍历目标方法参数对象
        Parameter[] parms = method.getParameters();
        for (int ix = 0 ; ix < parms.length ; ++ix) {
            Parameter parm = parms[ix];
            Class type = parm.getType();

            // 如果有Map类型的参数
            // 则设置该参数
            if (type == Map.class) {
                paramArray[ix] = map;
            }
        }

        return paramArray;
    }
}
