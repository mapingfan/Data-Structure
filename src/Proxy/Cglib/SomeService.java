package Proxy.Cglib;


/**
 * 如何代理SomeService这个类呢?我们没有接口可以用.所以JDK的动态代理技术是不用考虑了.
 * 下面引入cglib代理技术.
 *
 */
public class SomeService {
    public void find() {
        System.out.println("查询操作");
    }

    public void delete() {
        System.out.println("删除操作");
    }
}
