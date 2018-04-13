package DynamicProgramming.RectangleFill;
//分析思路即可,简单的矩形填充题目.

/**
 * 有2*n的矩形,用1*2,的砖头填满,可以竖着或者横着放.
 * 问有多少种方法.
 * 分析最后一块砖的摆法:
 *  竖着摆,那么前面有n-1格可以摆.那么f(n) = f(n-1).
 *  横着摆,那么前面有n-2个格可以摆,则f(n) = f(n-2).
 *  一共f(n) = f(n-1)+f(n-2).
 *  边界值:
 *  n=1,1种,
 *  n=2,2种
 *  下面就是简单的实现.
 *  此处忽略.
 *  矩阵幂的方法据说可以O(lgn)实现.暂时先不研究了.
 *  一维快速幂和二维快速幂
 *
 */

public class Solution {

    private static int solution(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
