package Proxy;

public class SomeServiceImpl implements ISomeService {

    @Override
    public void save() {
        System.out.println("执行保存");
    }

    @Override
    public void find() {
        System.out.println("执行查询");
    }

}

/**
 * 现在有如下需求,我们需要在save/find方法之前开启事物,但是我们不想每个方法都写.
 * 如何写一次,让每个方法都有?
 * 这个地方我们的目的就是增强方法的功能,核心思想就行装饰模式,继承,动态代理.
 * 下面先学习最基础的动态代理.
 */
