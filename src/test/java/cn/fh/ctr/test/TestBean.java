package cn.fh.ctr.test;

import java.util.Map;

/**
 * Created by whf on 10/10/15.
 */
public class TestBean {
    public void print(Object o, Map<String, String> map, Integer obj) {
        System.out.println("print!!!");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", val = " + entry.getValue());
        }
    }
}
