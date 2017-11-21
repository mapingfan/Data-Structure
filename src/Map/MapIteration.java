package Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 学习Java Map的几种遍历方法；
 * 避免使用V2版本，太低效；
 * 推荐使用V1，V4；
 * 只需遍历键或者值时用V3；
 */
public class MapIteration {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i + "", i + "i");
        }
        mapIterationV1(map);
        mapIterationV2(map);
        mapIterationV3(map);
        mapIterationV4(map);

    }

    public static void mapIterationV1(Map<String, String> map) {
        //基于Map.Entry实现
        //For-Each实现
        //在IDEA中，直接输入iter可以生产这种遍历代码
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
        }
    }
    //迭代键，但是可以通过键取得值
    public static void mapIterationV2(Map<String, String> map) {
        for (String s : map.keySet()) {
            System.out.println("key = " + s + " value = " + map.get(s));
        }
    }
    //只迭代值；
    public static void mapIterationV3(Map<String, String> map) {
        for (String s : map.values()) {
            System.out.println("value = " + s);
        }
    }

    public static void mapIterationV4(Map<String,String> map) {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
        }
    }
}
