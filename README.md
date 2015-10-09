# Ctr - HTTP请求路由
该组件的设计目标是提供SpringMVC中`@RequestMapping`注解的功能。Ctr会在运行时扫描标记了`@ReqMap`注解
的方法，然后通过对应URL可以直接找到并调用该方法。