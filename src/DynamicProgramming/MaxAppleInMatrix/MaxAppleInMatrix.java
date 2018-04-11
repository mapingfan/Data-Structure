package DynamicProgramming.MaxAppleInMatrix;

/**
 * m*n的矩阵里面,每个单元存放了若干苹果.
 * 现在从左上角出发,每次可以往下或者往右移动.一直移动到右下角.
 * 求在这个过程中能收集的最大苹果数量.
 * <p>
 * 典型的动态规划题目:
 * 分析:
 * 设 M(i,j)为在(i,j)处收集到的最大苹果数量.
 * 那么
 * M(i,j) = A[i][j]+M(i-1,j)
 * M(i,j) = A[i][j]+M(i,j-1)
 * 取两者中最大的一个就为M(i,j).
 * 考虑i,j的边界值.
 * i =1 时, M(i,j) = sum_row(数组第一行)
 * j =1 时, M(i,j) = sum_row(数组第一列)
 * <p>
 * 下面代码实现.
 */
public class MaxAppleInMatrix {
    /**
     * @param apples 矩阵
     * @param i      第i行,对应数组下标
     * @param j      第j列.对应数组下标.
     * @return 返回最大的苹果数量.
     */
    private static int maxApple(int[][] apples, int i, int j) {
        if (i == 0) {
            return sum_row(apples, i, j);
        }
        if (j == 0) {
            return sum_col(apples, i, j);
        }
        return Math.max
                (
                        apples[i][j] + maxApple(apples, i - 1, j),
                        apples[i][j] + maxApple(apples, i, j - 1)
                );
    }

    /**
     *
     * @param apples 矩阵
     * @param m 非下标.m行矩阵
     * @param n 非下标,n列矩阵.
     * @return
     */

    private static int dp_maxApple(int[][] apples, int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = apples[0][0];
        for (int i = 1; i < n; i++) {
//            dp[0][i] = sum_row(apples, 0, i);
            dp[0][i] = dp[0][i - 1] + apples[0][i];
        }

        for (int i = 1; i < m; i++) {
//            dp[i][0] = sum_col(apples, i, 0);
            dp[i][0] = dp[i - 1][0] + apples[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = apples[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];

    }

    private static int sum_col(int[][] apples, int i, int j) {
        int sum = 0;
        for (int k = 0; k <= i; k++) {
            sum += apples[k][j];
        }
        return sum;
    }

    private static int sum_row(int[][] apples, int i, int j) {
        int sum = 0;
        for (int k = 0; k <= j; k++) {
            sum += apples[i][k];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] apples = {
                {1, 2, 3}
                , {4, 5, 6}
        };
   /*     int[][] apples = {
                {5, 8, 5, 7, 1, 8},
                {1, 3, 2, 8, 7, 9},
                {7, 8, 6, 6, 8, 7},
                {9, 9, 8, 1, 6, 3},
                {2, 4, 10, 2, 6, 2},
                {5, 5, 2, 1, 8, 8}
        };
        */
       /* int[][] apples = {
                {4, 9, 5, 3},
                {2, 3, 8, 5},
                {7, 2, 5, 1},
                {3, 9, 4, 2}
        };*/
        System.out.println(dp_maxApple(apples, 2, 3));
        System.out.println(maxApple(apples, 1, 3 - 1));
    }

}
