package Proxy.Cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 引入cglib需要的包.
 */
public class CglibProxy implements MethodInterceptor {
    //获得代理对象
    public SomeService  getCglibProxy() {
        Enhancer enhancer = new Enhancer();
        //基于继承的代理技术,设置要代理的类作为父类;
        enhancer.setSuperclass(SomeService.class);
        //设置回调函数.
        enhancer.setCallback(this);
        return (SomeService) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启事物");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("关闭事物");
        return invoke;
    }
}
