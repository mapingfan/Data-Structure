package Proxy;

/**
 * 假设这个地方,我们想代理SomeServiceImpl类的方法,从而实现增强.
 * 我们需要知道代理哪些方法,哪些方法不代理.
 * 代理的对象是什么.
 *
 *
 * 静态代理有一个问题还是比较恶心的.
 * 比如下面我们要做的都是添加开启事物,关闭事物的方法.我们并不关心要给save还是find方法添加.
 * 我们是否可以把开启事物,关闭事物的代码再次进行抽取呢?
 * 答案是可以的.这个地方就要用到基于JDK的动态代理技术.
 */
public class StaticProxy implements ISomeService {
    private ISomeService target; // 要代理的对象.采用面向接口编程

    public StaticProxy(ISomeService target) {
        this.target = target;
    }

    /*
    下面的方法就是要代理的方法.
    我们在原有方法的功能基础上进行增强.
     */
    @Override
    public void save() {
        //在此处我们进行增强.可以类比Spring中的before植入.
        System.out.println("开启事物");
        target.save();
        //后处理,后植入.
        System.out.println("关闭事物");
    }

    @Override
    public void find() {
        System.out.println("开启事物");

        target.find();

        System.out.println("关闭事物");

    }
}
