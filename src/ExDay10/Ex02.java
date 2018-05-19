package ExDay10;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个数组,求满足条件的子数组个数.
 * max(arr[i,j]-min(arr[i,j])<num .就是i-j范围内的数组,最大值减去最小值小于num这个值.
 * 求所有的这种数组个数.
 * 本办法就是对于n^2个数组,每个进行一次判断.
 * 或者来一点启发式规则,
 * 如果i-j已经不满足了,那么i-j+n(n>1)肯定不满足.
 * 如果i-j已经满足了,那么i-j里面的子数组都是满足的.
 */
public class Ex02 {

    //不带启发规则的策略.
    public static int solution_0(int[] array, int num) {
        if (array == null) return -1;
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (isValid(array, i, j, num)) result++;
            }
        }
        return result;
    }
    //稍微用了一点启发规则.

    /**
     * 比如现在 i从0开始,j从尾巴开始.那么当遇到第一个j,使得满足条件,那么这个范围内以i开头的子数组都满足.
     * 直接加上j-i+1个解决问题.
     * 然后i跳到下一个,找以这个i开头的所有.
     * <p>
     * 也就是这样:每次找到以当前i开头的所有子数组.
     * <p>
     * 这个策略相较于上面有了 优化,但是最差可能还是O(n^3).
     * 最好的解决方案是O(n).
     */
    public static int solution_1(int[] array, int num) {
        if (array == null) return -1;
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j >= i; j--) {
                if (isValid(array, i, j, num)) {
                    result += (j - i + 1);
                    break;
                }
            }
        }
        return result;
    }

    public static int solution_2(int[] array, int num) {
        if (array == null) return -1;
        //利用窗口内维护最大值最小值结构来求解问题.
        int result = 0;
        LinkedList<Integer> maxHelp = new LinkedList<>(); //记录最小值下标
        LinkedList<Integer> minHelp = new LinkedList<>(); //记录最大值下标.
        int left = 0, right = 0;
        while (left < array.length) {
            while (right < array.length) {
                while (!maxHelp.isEmpty() && array[maxHelp.peekLast()] <= array[right]) {
                    maxHelp.pollLast(); //从右边出去.
                }
                maxHelp.addFirst(right); //加进入当前窗口的最大值.
                while (!minHelp.isEmpty() && array[minHelp.peekLast()] >= array[right]) {
                    minHelp.pollLast();
                }
                minHelp.addLast(right);
                //检查当前的窗口下标是否过期了.需要while?按理来说不需要.写上也没什么.
                while (maxHelp.peekFirst() < left) {
                    maxHelp.pollFirst();
                }
                while (minHelp.peekFirst() < left) {
                    minHelp.pollFirst();
                }
                //检测当前的最大值,最小值是否满足.如果不满足了就找到了.满足了接着往下找.
                if (array[maxHelp.peekFirst()] - array[minHelp.peekFirst()] > num) {
                    break; //跳出循环.
                }
                right++;
            }
            //已经找到所以以right开头的,满足条件的子数组.
            result += (right - left); //这个right已经不满足了,所以不需要减加1.
            left++;
            //注意这个过程中,right是不需要回头的.原先回头的目的是为了再次寻找最值,但是有了这个结构,
            //我们可以简单的更新出最值,不用重新扫描一遍.
        }
        return result;
    }

    //判读i-j范围内的最大值最小值是否满足给定条件.
    private static boolean isValid(int[] array, int i, int j, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            max = Math.max(max, array[k]);
            min = Math.min(min, array[k]);
        }
        if (max - min < num) return true;
        return false;
    }

    public static int[] generate(int size, int seed) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = RandomUtils.nextInt(seed, 3 * seed);
        }
        return result;
    }

    public static int[] copy(int[] array) {
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }

    public static void main(String[] args) {
        for (int i = 5000; i > 0; i--) {
            int[] array = generate(100, 100000);
            int[] bak_1 = copy(array);
            int[] bak_2 = copy(array);
            int num = RandomUtils.nextInt();
            long start = System.currentTimeMillis();
            int r1 = solution_0(array, num);
            System.out.println(System.currentTimeMillis() - start);
            start = System.currentTimeMillis();
            int r2 = solution_1(bak_1, num);
            System.out.println(System.currentTimeMillis() - start);
            start = System.currentTimeMillis();
            int r3 = solution_2(bak_2, num);
            System.out.println(System.currentTimeMillis() - start);
            boolean successful = r1 == r2 && r1 == r3 ? true : false;
            if (successful) System.out.println("nice");
            else {
                System.out.println(r1 + " " + r2 + " " + r3);
                System.out.println(Arrays.toString(array) + " -- " + num);
                System.out.println("fuck");
                break;

            }
        }
    }
}
