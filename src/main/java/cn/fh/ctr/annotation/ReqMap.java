package cn.fh.ctr.annotation;

import cn.fh.ctr.method.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by whf on 10/10/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReqMap {
    String value() default "";
    RequestMethod method() default RequestMethod.GET;
}
