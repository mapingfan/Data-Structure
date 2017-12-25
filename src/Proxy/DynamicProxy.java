package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 静态代理有一个问题还是比较恶心的.
 * 比如下面我们要做的都是添加开启事物,关闭事物的方法.我们并不关心要给save还是find方法添加.
 * 我们是否可以把开启事物,关闭事物的代码再次进行抽取呢?
 * 答案是可以的.这个地方就要用到基于JDK的动态代理技术.
 *
 * 上面的代理技术似乎已经很好了,我们已经实现目标,事物功能就写了一份,并且植入到所有的方法中;
 * 但是这个地方必须要知道目标类的实现的接口.
 * 那么目标类不实现接口,我们就没法进行动态代理吗?
 *
 * 答案是否定的,下面看下Cglib代理技术.
 */
public class DynamicProxy {
    private ISomeService target; // 要代理的对象.采用面向接口编程

    public DynamicProxy(ISomeService target) {
        this.target = target;
    }
    //JDK的动态代理,扔需要指定代理对象.
    //并且,动态代理,我们是不需要实现类的,只需要调用API即可.

    public ISomeService getDynamicProxyObject() {
        ISomeService proxyInstance = (ISomeService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //此处作前置处理;
                        System.out.println("开启事物");
                        Object invoke = method.invoke(target, args);
                        System.out.println("关闭事物");
                        return invoke;
                    }

                }
        );
        return proxyInstance;
    }
}
