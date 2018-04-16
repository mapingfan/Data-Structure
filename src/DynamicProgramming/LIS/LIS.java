package DynamicProgramming.LIS;

import java.util.Arrays;

/**
 * 通过这个问题引入动态规划.这个问题可以说是动态规划最简单的例子,但是很有助于理解内涵.
 * 求给定数组最长递增子序列问题:Longest increasing subsequence.
 */
public class LIS {
    /**
     * 求最长子序列的最大长度,不要求组成子序列的元素连续.
     *
     * @param arr 传入的数组
     * @param n   数组共n个数字
     * @return 返回最长子序列长度.
     * <p>
     * {
     * {                lis(n-1);  如果第n个数字包含在最长子序列中.
     * lis(n)=    {
     * {                lis(n-1)+1;  第n个数字不包含在最长子序列中.
     * {
     * 问题转化为什么时候第n个数组包含在最长子序列中?
     * if ( arr[n]<arr[n-1],肯定不包含.
     * <p>
     * -----------------------------------------------------
     * 上面的问题无法解决,因为无法知道什么时候第n个数字包含在里面,无法确定条件,无法求解.
     * 下面看下正确思路:
     * 定义lis(n)为最长子序列长度
     * 那么 lis(n) = Max(lis(i)+1|0<i<n&arr[i]<arr[n]),
     * 如果都不满足,那么list(n) = 1;
     * 要遍历出lis(i),求出最大的一个.
     * 上面我的方法就陷入误区,根本无法求解.下面别人的思路就可以完美解决.
     * 定义lis(n)为n项的最长子序列长度,考虑到动态规划的性质,如何找到子问题呢,也就是n与前面小于n的数字有什么关系.
     * 传统的思维就是看看n-1/n-2.但是这个题目确需要考虑的事lis(i),i取值从0,到n-1,闭区间.要考虑前面的任意多项.
     * <p>
     * 我的思维误区在于,钻进了lis(n)/lis(n-1)的死胡同.这是因为即使arr[n-1]<arr[n],那也不能保证lis(n)=lis(n-1)
     * 因为无法保证第n-1项被包含在序列中.即lis(n-1)可能等于lis(k),k<n-1中最大的那个.
     * 正是因为无法确定第n-1是否被包含在序列中,所以考虑lis(n-1)没有任何作用.lis(n)可能等于lis(i)中的最大一个.
     */
    private static int rec_lis(int[] arr, int n) { //n>1;
        if (n == 1) { //数组长度为1,直接返回1,最长子序列就是自身.
            return 1;
        } else {
//            如果长度不为一,那么list(n)= Max(lis(i)+1;
            int max = 0;
            for (int i = n - 2; i >= 0; i--) {
                if (arr[i] < arr[n - 1]) {
                    int temp;
                    if (max < (temp = rec_lis(arr, i + 1))) {
                        max = temp;
                    }
                }
            }
            return max + 1;
        }
    }
    //下面用动态规划实现下.可以观察到,需要计算lis(i),i<n,那么计算lis(i-1),会有很多重复计算.所以用备忘录加速求解.

    static int[] dp = new int[1000];  //预估1000.

    static {
        Arrays.fill(dp, 0);
    }

    /**
     * 这个方法自顶向下合并,而不是常见的自底向上的合并方式.
     * 对于自底向上的合并方法,这个题目实现起来有点繁琐.
     */
    private static int dp_lis(int[] arr, int n) {
        //int[] dp = new int[arr.length];  //其实可以把数组放到外面,免得递归开辟过多内存.
        if (n == 1) {
            return dp[n - 1] = 1;
        } else {
            if (dp[n - 1] != 0) {
                return dp[n - 1];
            }
            int max = 0;
            for (int i = n - 2; i >= 0; i--) {
                if (dp[i] == 0) {
                    dp[i] = dp_lis(arr, i + 1);
                }
                if (arr[i] < arr[n - 1]) {
                    if (max < dp[i]) {
                        max = dp[i];
                    }
                }
            }
            return max + 1;
        }
    }

    public static void main(String[] args) {

        int[] array = {5, 2, 8, 6, 3, 6, 9, 7};
        System.out.println(dp_lis(array, array.length));
    }

}
