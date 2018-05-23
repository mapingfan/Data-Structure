package ExDay12;

import lombok.experimental.var;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一堆数字,有正有负,还有0,能否拼出给定的值.如果能,求出最长的那个子串.
 *
 * 学习一个策略,如果在一串数字中最早或者最迟出现的结果策略.如果最早出现sum-aim.
 * 记得补个-1的位置.
 * 其次就是拿map换时间的策略,又学到一个技巧.
 *
 */
public class Ex01 {
    /**
     * 暴力策略,遍历所有子数组,观察是否有满足条件的.
     */
    public static int solution(int[] array, int aim) {
        if (array == null || array.length == 0) return 0;
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            int tmpSum = 0;
            for (int j = i; j < array.length; j++) {
                tmpSum += array[j];
                if (tmpSum == aim) result = Math.max(result, j - i + 1);
            }
        }
        return result;
    }

    //上面的策略是O(N^2).能否优化?答案是可以用map换时间.

    /**
     * 如下的策略.考虑以每个位置结束的组成串.
     * 比如第i为.到第i为的总和是k,那么如果前面能第一次凑出k-aim,那么此时就是找到以k结尾最长的.
     * 遍历一圈,就可以求出最大值.
     * 其实关键的是拿空间换时间.如何快速知道第一次出现k-aim的下标就极为重要了.
     * 其次还有一个关键点就是必须添加一个-1位置出现0.否则会出现错误.
     * 比如湊7,第一个数字就是7.那么需要找到7-7=0.可是map中并不会出现0,这样就导致7这个情况被错过了.
     * 所以这个地方需要人为补一个.
     */
    public static int solution_2(int[] array, int aim) {
        if (array == null || array.length == 0) return 0;
        HashMap<Integer, Integer> help = new HashMap<>();
        help.put(0, -1); //-1位置处的和为0.
        int sum = 0, len = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (help.containsKey(sum - aim)) len = Math.max(len, i - help.get(sum - aim));
            if (!help.containsKey(sum)) help.put(sum, i); //如果sum值没出现过,加入到map中.
        }
        return len;
    }

    //变种题目,一个数组中,只有奇数和偶数,求奇数和偶数个数相等的最长子数组.
    //预处理,把偶数变为-1,奇数变为1.那么转化为求和为0的最长子数组问题.很巧妙.

    public static int solution_3(int[] array) {
        if (array == null || array.length < 2) return 0;
        int tmp[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) tmp[i] = -1;
            else tmp[i] = 1;
        }
        return solution_2(tmp, 0);
    }

    //变种题目三,一个组数中含有0,1,2.求含有1,2个数相等的最长子数组.还是预处理,2变为-1,最后求和为0的最长子数组即可.


    //变种四:给定一个数组,进行切分,切分后得到的子数组,如果异或和为0,那么结果+1,求最优结果,即疑惑和最多的切分.

    /**
     * 复习异或和: A xor A = 0 , A xor 0 = A
     * 其实这个题目像动态规划.
     * 考虑最后一个数字,如果的最优切割中包含最后一个数字n,那么 dp[n]=dp[k]+1 , 其中   1<=k<n&&arr[k+1,n]的异或和是0.
     * 如果不包含最后一个数字,那么 dp[n]=dp[n-1].
     * 现在要求最大值,即 dp[n]= max(dp[k]+1,dp[n-1]).
     * * 现在推倒下边界值:
     * 如果n=1,那么dp[0]=0/1.
     * <p>
     * 下面写了一个暴力递归版本,想办法改成动态规划版本.
     */

    public static int solution_4(int[] array, int n) {
        if (array == null || array.length == 0) return 0;
        if (n == 1) return array[n - 1] == 0 ? 1 : 0;
        int max = 0;
        for (int i = 0; i <= n - 2; i++) {
            int tmp = 0;
            for (int k = i + 1; k <= n-1; k++)
                tmp ^= array[k];
            if (tmp==0)
                max = Math.max(max, solution_4(array, i));
        }
        return Math.max(max + 1, solution_4(array, n - 1));
    }

    public static void main(String[] args) {
        int[] array = {7, 3, 2, 1, 1, 7, -7, 7, 7, 7};
        System.out.println(solution(array, 7));
        System.out.println(solution_2(array, 7));
    }

}
