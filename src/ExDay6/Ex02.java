package ExDay6;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有两个数组,一个代表成本数组,一个表示利润数组,
 * 例如
 * 10,30,50,100,200  成本
 * 5, 12,32, 21, 12  利润
 * <p>
 * 现在有启动资金30,要求怎样可以赚钱最多.
 * 常规的策略就是首先找出能做的项目多,利润最多的项目.
 * 做完然后再次看看能赚利润最多的项目.
 * 假设最多可以做k个项目.
 * 最后可能存在做不满k个项目的情况.
 * <p>
 * 所以首先是找出能做的项目,然后从能做的项目中找出利润最多的.
 * 这个地方还是优先队列的使用.
 */
public class Ex02 {

    private static class Data {
        int cost;
        int profit;

        public Data(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    /**
     * @param cost      每个项目的成本
     * @param profit    每个项目的利润.
     * @param k         最多能做k个项目.规定每次只能做一个项目.
     * @param initMoney 初始资金.
     * @return
     */
    private static int maxProfit(int[] cost, int[] profit, int k, int initMoney) {
        if (cost == null || profit == null) return -1;
        if (cost.length != profit.length) return -1;
        PriorityQueue<Data> assist_1 = new PriorityQueue<>(Comparator.comparingInt(d -> d.cost)); //按照cost排列的小根堆.
        PriorityQueue<Data> assist_2 = new PriorityQueue<>((d1, d2) -> d2.profit - d1.profit); //按照利润排序的大根堆.
        for (int i = 0; i < cost.length; i++) {
            assist_1.add(new Data(cost[i], profit[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!assist_1.isEmpty()&&assist_1.peek().cost <= initMoney) {
                assist_2.add(assist_1.poll());
            }
            if (assist_2.isEmpty()) {
                return initMoney;
            }
            Data data = assist_2.poll();
            initMoney += data.profit;
        }
        return initMoney;
    }

    public static void main(String[] args) {

    }

}
