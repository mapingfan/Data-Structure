package ExDay4;

import java.util.HashMap;
import java.util.Random;

/**
 * 实现一个结构,要求能够O(1)时间插入,删除,和随机返回一个值.
 * 具体要求如下:
 * insert(key) ,不能重复加入
 * delete(key) ,移除一个已经在结构中的key.
 * getRandom() 要等概率返回任意一个key,注意严格等概率.
 * <p>
 * 分析下:
 * 对于一个普通的Java哈希表,原本就可以做到常数时间插入删除.但是等概率返回就有点麻烦了
 * 首先要知道结构有多少个元素.这个也很好解决,map有size属性,可以常数时间获得.
 * 可以用这个数字范围产生一个随机数.然后随机返回一个就好.但是如何知道第3个数字对应那个key,
 * 如果对map进行扫描,那么就不是常数时间了.
 * 又是一个需要空间换时间的问题.
 * <p>
 * 下面看下具体实现
 */
public class Ex01<T> {

    private final HashMap<T, Integer> dataMap = new HashMap<>();
    private final HashMap<Integer, T> assistMap = new HashMap<>();
    private int size = 0;
    private static final Random random = new Random();

    public void insert(T key) {
        //重复性检查
        if (!dataMap.containsKey(key)) {
            assistMap.put(size, key);
            dataMap.put(key, size++);
        }
    }

    /**
     * 同步删除操作.每次只删除辅助map中的最后一个元素.让产生随机数时不会出现空洞.
     * 注意dataMap也要注意同步修改.
     */
    public void delete(T key) {
        //检查在map中才可以删除.
        if (dataMap.containsKey(key)) {
            Integer integer = dataMap.get(key);
            T last = assistMap.get(size - 1);
            assistMap.put(integer, last);
            dataMap.put(last, integer);
            dataMap.remove(key);
            assistMap.remove(--size);
        }
    }

    public T getRandom() {
        if (size==0) return null;
        int i = random.nextInt(size);  //0-size-1的范围.
        T t = assistMap.get(i);
        return t;
    }


    public static void main(String[] args) {

    }
}
