package DynamicProgramming.MSSVariant;

import java.util.Arrays;

/**
 * 求不连续子列和,要求不能三个元素相邻.
 * f(n) = A[n]+A[n-1]+f(n-3) n和n-1都被选了.
 * f(n) = A[n]+f(n-2) n被选,n-1没被选.
 * f(n) = f(n-1) n没有被选.
 * 上面三个取最大.
 * f(1) = A[1],
 * f(2) = max(A[1],A[2],A[1]+A[2]),
 * f(3) = max(A[0],A[1],A[2],A[0+1],A[0+2],A[1+2],
 * <p>
 * f(n)代表n个元素中不能三个元素相邻的最大子列和.
 *
 * 补充一点:
 * 写递归式的时候,n是代表第几个元素.但是在数组中表示第n个元素的下标却是n-1.
 * 那么f(n-1)是否要相应变成n-2呢?
 * 答案是否定的.因为f(n-1)代表的意思就是倒数第二个元素的子列和.如果写成成f(n-2)就表示倒数第三个元素了.
 *
 * 所以数组下标不要和函数递归的规模搞混.切记.
 *
 *
 */
public class Variant {

//动态规划实现一下上面的题目.
    private static int dp_variant(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = max(arr[0], arr[1], arr[1] + arr[0]);
        dp[2] = max(arr[0] + arr[1],
                arr[0] + arr[2],
                arr[1] + arr[2],
                arr[0],
                arr[1],
                arr[2]);
        for (int i = 3; i < arr.length; i++) {
            dp[i] = max(dp[i - 1], arr[i] + dp[i - 2], arr[i] + arr[i - 1] + dp[i - 3]);
        }
        return dp[arr.length - 1];
    }

    private static int variant(int[] arr, int n) { //n个元素.
        if (n == 1) {
            return arr[0];
        }
        if (n == 2) {
            return max(arr[0], arr[1], arr[1] + arr[0]);
        }
        if (n == 3) {
            return max(arr[0] + arr[1],
                    arr[0] + arr[2],
                    arr[1] + arr[2],
                    arr[0],
                    arr[1],
                    arr[2]);
        }
        //n代表下标.
        return max(
                variant(arr, n - 1),
                arr[n - 1] + arr[n - 2] + variant(arr, n - 3),
                arr[n - 1] + variant(arr, n - 2)
        );
    }

    private static int max(int... params) {
        int[] copy = Arrays.copyOf(params, params.length);
        Arrays.sort(copy);
        return copy[copy.length - 1];
    }

    public static void main(String[] args) {
        int[] array = {1, -5, 8, 4,5};
        System.out.println(variant(array, array.length));
        System.out.println(dp_variant(array));
    }

}
