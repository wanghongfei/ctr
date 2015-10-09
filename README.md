# Ctr - HTTP请求路由
该组件的设计目标是提供SpringMVC中`@RequestMapping`注解的功能。Ctr会在运行时扫描标记了`@ReqMap`注解
的方法，然后通过对应URL可以直接找到并调用该方法。

## Why Ctr
Ctr主要用来解决从HTTP请求到Java方法之间的映射问题。由于Java语言中方法(method)并不是一等公民，因此必须通过繁杂的反射
调用来实现直接调用方法。对此SpringMVC的`@RequestMapping`注解很好地解决了这个问题，但缺点是只能在Spring框架中使用，
且需要Web容器支持。Ctr提供一个通用的映射解决方案，你可以在任何Java程序中使用它。