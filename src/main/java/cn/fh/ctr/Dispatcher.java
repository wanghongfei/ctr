package cn.fh.ctr;

import cn.fh.ctr.exception.BaseCtrException;
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

    public void register(String url, CtrMethod method) {
        reqMap.put(url, method);
    }

    /**
     * 调用该URL对应的方法
     * @param url
     * @param paramMap web请求参数
     * @return
     */
    public Object invoke(String url, Map<String, String> paramMap) throws BaseCtrException {
        CtrMethod method = reqMap.get(url);
        return method.invoke(paramMap);
    }
}
