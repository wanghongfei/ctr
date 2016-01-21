package cn.fh.ctr;

import cn.fh.ctr.annotation.ReqMap;
import cn.fh.ctr.exception.BaseCtrException;
import cn.fh.ctr.exception.NoMappingException;
import cn.fh.ctr.method.CtrMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 保存从URL到方法的映射, 并提供通过URL直接调用方法的方法
 * Created by whf on 10/10/15.
 */
public class Dispatcher {
    private Map<String, CtrMethod> reqMap;

    public Dispatcher(int cap) {
        reqMap = new HashMap<>(cap);
    }

    public Dispatcher() {
        this(50);
    }

    /**
     * 注册一个 请求-方法映射.
     * 指定Method对应的URL，此时忽略Method上的注解
     * @param url
     * @param method
     */
    public void register(String url, CtrMethod method) {
        reqMap.put(url, method);
    }

    /**
     * 注册一个 请求-方法映射.
     * 请求URL由方法上的注解决定
     * @param ctrMethod
     */
    public void register(CtrMethod ctrMethod) {
        ReqMap mapInfo = ctrMethod.parseAnnotation();

        String url = "/";
        if (null != mapInfo) {
            url = mapInfo.value();
        }

        register(url, ctrMethod);
    }

    /**
     * 调用该URL对应的方法
     * @param url
     * @param paramMap web请求参数
     * @return
     */
    public Object invoke(String url, Map<String, String> paramMap) throws BaseCtrException {
        CtrMethod method = reqMap.get(url);
        if (null == method) {
            throw new NoMappingException("no mapping for " + url);
        }

        return method.invoke(paramMap);
    }
}
