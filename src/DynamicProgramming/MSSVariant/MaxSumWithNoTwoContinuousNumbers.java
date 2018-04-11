package DynamicProgramming.MSSVariant;

public class MaxSumWithNoTwoContinuousNumbers {


    /**
     * 求序列中最大的子序列和,要求不能选择相邻的数字.
     * <p>
     * f(n) = Max(A[i]+f(n-2),f(n-1) n>2; 这个地方的n不代表下标.是第n个元素.
     * f(n) = A[1] n = 1;
     * f(2) = Max(A[1],A[2]) n=2
     */

    private static int maxSumVariant(int[] arr, int n) {
        if (n == 0) {
            return arr[0];
        }
        if (n == 1) {
            return Math.max(arr[0], arr[1]);
        }
        return Math.max(arr[n] + maxSumVariant(arr, n - 2), maxSumVariant(arr, n - 1));
    }

    private static int dp_maxSumVariant(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[arr.length- 1];
    }

    public static void main(String[] args) {
        int[] array = {1, -5, 8, 3, -4, 15, -8};
        System.out.println(dp_maxSumVariant(array));
        System.out.println(maxSumVariant(array, array.length-1));

    }

}