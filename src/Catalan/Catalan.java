package Catalan;

/**
 * 卡特兰数计算公式.
 * C(n) = (2n)! /(n!*(n+1)!)
 */
public class Catalan {

    private static long fac(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }
        return n * fac(n - 1);
    }

    static long[] dp = new long[1024];
    static {
        dp[0] = dp[1] = 1;
    }

    private static long dp_fac(int n) {
        if (n > 1024) {
            throw new IllegalStateException("数字过大异常.");
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        for (int i = 2; i <= n; i++) {
            dp[i] = i * dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(fac(2));
        System.out.println(catalan(10));

    }

    /**
     * C(n) = fac(2n)/(fac(n)*fac(n+1)),
     * 这个地方有重叠计算.用备忘录进行优化.
     */
    private static long catalan(int n) {
        return fac(2 * n) / (fac(n) * fac(n + 1));
    }

}
