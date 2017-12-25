package Proxy.test;

import Proxy.Cglib.CglibProxy;
import Proxy.Cglib.SomeService;
import Proxy.DynamicProxy;
import Proxy.ISomeService;
import Proxy.SomeServiceImpl;
import Proxy.StaticProxy;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * 用于测试静态代理,动态代理.
 */
public class TestProxy {

    @Test
    public void testStaticProxy() {
        ISomeService service = new SomeServiceImpl(); //准备目标对象;
        StaticProxy staticProxyObject = new StaticProxy(service); //返回代理对象;
        //下面执行代理后的方法;
        System.out.println("-----开始执行find方法-------");
        staticProxyObject.find();
        System.out.println("-----find方法执行结束-------");
        System.out.println("-----开始执行save方法-------");
        staticProxyObject.save();
        System.out.println("-----save方法执行结束-------");
    }

    @Test
    public void testDynamicProxy() {
        ISomeService service = new SomeServiceImpl(); //准备目标对象;
        DynamicProxy dynamicProxy = new DynamicProxy(service);
        ISomeService proxyObject = dynamicProxy.getDynamicProxyObject();

        System.out.println("测试动态代理");
        System.out.println("-----开始执行find方法-------");
        proxyObject.find();
        System.out.println("-----find方法执行结束-------");
        System.out.println("-----开始执行save方法-------");
        proxyObject.save();
        System.out.println("-----save方法执行结束-------");
    }

    @Test
    public void testCglibProxy() {
        System.out.println("测试Cglib代理");
        CglibProxy proxy = new CglibProxy();
        SomeService service = proxy.getCglibProxy();

        System.out.println("-----开始执行find方法-------");
        service.find();
        System.out.println("-----find方法执行结束-------");

    }
}
