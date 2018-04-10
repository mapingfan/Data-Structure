package DynamicProgramming.DP_1;

/**
 * 考虑如下递归式:
 * T(0)=T(1)=2;
 * T(n) = sum(2T(i)*T(i-1)),n>1,  1<=i<=n-1.
 * 下面逐步演化.
 *
 * 经验:动态规划,对已有的递归式可以找规律,优化动态规划的求解过程.
 * 这个例子重点理解如何去进行自底向上动态规划的过程.已经优化过程.
 *
 */
public class DPExample {

    private static int rec_fun(int n) {
        if (n == 0 || n == 1) {
            return 2;
        } else {
            int sum = 0;
            for (int i = 1; i <= n - 1; i++) {
                sum += 2 * rec_fun(i) * rec_fun(i - 1);
            }
            return sum;
        }
    }

    //上面的递归版本改为dp版本.
    private static int dp_fun(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 2;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i] += 2 * dp[j] * dp[j - 1];
            }
        }
        return dp[n];
    }

    //上面这个版本复杂度是O(n^2),是否可以进一步优化呢?

    /**
     * 观察如下规律:
     * T(2) = 2*T(1)*T(0);
     * T(3) = 2*T(2)*T(1)+2*T(1)*T(0)
     * T(4) = 2*T(3)*T(2)+2*T(2)*T(1)+2*T(1)*T(0)
     * T(i)＝　２＊T(i-1)*T(i-2)+T(i-1); i>2; 注意i>2时才有这规律.
     */
    private static int dp_fun_2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 2;
        dp[2] = 2 * dp[1] * dp[0];
        for (int i = 3; i <= n; i++) { // i必须从3开始.
            dp[i] = 2 * dp[i - 1] * dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(dp_fun_2(5));
        System.out.println(dp_fun(5));
        System.out.println(rec_fun(5));

    }


}
