package ExDay6;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 金条切割问题.对于一个长度为60的金条,要切成10,20,30三份.但是每次切割存在代价.
 * 如果先切成10/50,代价需要花费60.然后把50切成20/30,代价是50. 总的代价是110.
 * 但是如果先切成30/30,代价60,然后把30切成10/20,代价30.总代价90.比上一次小.
 * 现在要求最小代价.
 * <p>
 * 利用哈夫曼树+优先队列谈贪心解决.
 * 先选出切分数组中的最小那么数字,生个一个非叶节点,加回数组中,然后再次选出两个最小的,
 * 循环,知道最后队列中只有一个元素停止.
 * 其中每次生成的非叶节点就是最小代价.
 */
public class Ex01 {

    private static int minCost(int[] array) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < array.length; i++) {
            pq.add(array[i]);
        }
        int cost = 0;
        while (pq.size() > 1) {
            Integer first = pq.poll();
            Integer second = pq.poll();
            cost += (first + second);
            pq.add(first+second);
        }
        return cost;
    }

    public static void main(String[] args) {
        int[] array = {6, 7, 8, 9};
        System.out.println(minCost(array));
    }
    /**
     * 67 -> 13,8,9
     * 8,9-> 13,17
     * 13,17 -> 30 +13+17 = 60
     */
}
