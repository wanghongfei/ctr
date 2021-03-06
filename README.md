# Ctr - HTTP请求路由

该组件的设计目标是提供SpringMVC中`@RequestMapping`注解的功能。Ctr会在运行时扫描标记了`@ReqMap`注解的方法，然后通过对应URL可以直接找到并调用该方法。



> 与[pkg-scanner - 类扫描工具](https://github.com/wanghongfei/pkg-scanner)一同使用更佳。

## Why Ctr

Ctr主要用来解决从HTTP请求到Java方法之间的映射问题。由于Java语言中方法(method)并不是一等公民，因此必须通过繁杂的反射调用来实现直接调用方法。对此SpringMVC的`@RequestMapping`注解很好地解决了这个问题，但缺点是只能在Spring框架中使用，且需要Web容器支持。**Ctr提供一个通用的映射解决方案，你可以在任何Java程序中使用它**。



## Demo

`请求` - `方法`映射由`Dispatcher`类负责处理，`CtrMethod`是对`java.lang.reflect.Method`类的封装。

如，对于URI`/hello`, 需要调用`TestBean#print()`方法，类似于SpringMVC中的`@RequestMapping`, 我们**对目标方法(即`TestBean#print()`)签名没有任何要求，可以无参，也可以有多个参数**。框架在调用目标方法时，会自动查找方法形参中有没有`Map<String, String>`类型的参数，如果有则注入，没有也不会报错。

``` java
Method[] methods = TestBean.class.getDeclaredMethods();

// 构造Dispatcher对象
Dispatcher dispatcher = new Dispatcher(10);
// 注册方法映射
// methods[0]就是print()方法
dispatcher.register(new CtrMethod(methods[0], new TestBean()) );

// 构造请求参数
Map<String, String> map = new HashMap<>();
map.put("hello", "whf");

// 调用/hello对应的方法
// map中包含请求参数
dispatcher.invoke("/hello", map);
```

`TestBean类`:

``` java
public class TestBean {
    @ReqMap("/hello")
    public void print(Object o, Map<String, String> map, Integer obj) {
        System.out.println("print!!!");

      	// 打印请求参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", val = " + entry.getValue());
        }
    }
}
```

