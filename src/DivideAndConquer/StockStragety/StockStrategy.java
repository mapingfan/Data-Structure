package DivideAndConquer.StockStragety;

import java.util.Arrays;

import static jdk.nashorn.internal.objects.NativeMath.max;

/**
 * 股票策略问题,找出一个一个最低买入价格,和最高售出价格,使得利润最大.
 * 也就是说输入一组数字,下标是日期,找出最佳买入卖出.
 * 隐含要求,买入要在卖出之前.
 * <p>
 * 思路一很简单,两两比较2个元素的差值即可.
 */
public class StockStrategy {

    private static int stockStrategy(int[] array) {
        int profit = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] - array[i] > profit) {
                    profit = array[j] - array[i];
                }
            }
        }
        return profit;
    }

    /**
     * 思路二:递归思路求解.最小值,最大值可能出现在三个位置.
     * 左边,右边,左边最小,右边最大.也就是最大连续子序列和的递归思想.
     * 下面代码实现下
     */
    private static int stockStrategy(int[] array, int begin, int end) {
        if (begin == end) {
            return 0; //只有一个元素,利润为0.
        }
        if (begin < end) {
            int mid = begin + (end - begin) / 2;
            int leftProfit = stockStrategy(array, begin, mid);
            int rightProfit = stockStrategy(array, mid + 1, end);
            int tempProfit = max(array, mid + 1, end) - min(array, begin, mid);
            return max(leftProfit, rightProfit, tempProfit);
        }
        return -1;
    }

    private static int min(int[] array, int begin, int mid) {
        int min = array[begin];
        for (int i = begin + 1; i <= mid; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private static int max(int a, int b, int c) {
        int[] temp = {a, b, c};
        Arrays.sort(temp);
        return temp[temp.length - 1];
    }

    private static int max(int[] arr, int begin, int end) {
        int max = arr[begin];
        for (int i = begin; i <= end; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {3, 2, 3, 12, 1, 3, 5, 1, 77};
        System.out.println(stockStrategy(array));
        System.out.println(stockStrategy(array, 0, array.length - 1));

    }

}
