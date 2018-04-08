package DynamicProgramming.LIS;

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
     * {   lis(n-1);  如果第n个数字包含在最长子序列中.
     * lis(n)=    {
     * {   lis(n-1)+1;  第n个数字不包含在最长子序列中.
     * {
     * 问题转化为什么时候第n个数组包含在最长子序列中?
     * if ( arr[n]<arr[n-1],肯定不包含.
     * <p>
     * -----------------------------------------------------
     * 上面的问题无法解决,因为无法知道什么时候第n个数字包含在里面,无法确定条件,无法求解.
     * 下面看下正确思路:
     * 定义lis(n)为最长子序列长度
     * 那么 lis(n) = Max(lis(i)+1|0<i<n&arr[i]<arr[n]),
     * 要遍历出lis(i),求出最大的一个.
     * 最后的结果并不是lis(n),而是lis(i)中的最大值.0<i<=n;
     */
    private static int Lis(int[] arr, int n) {
        if (arr.length == 1) { //数组长度为1,直接返回1,最长子序列就是自身.
            return 1;
        } else {
            return 0;
        }
    }

}
