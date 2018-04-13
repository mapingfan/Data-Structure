package DynamicProgramming.MaxSubMatrixSum;

import sun.plugin.javascript.navig.Array;

import java.util.Arrays;

/**
 * 问题描述:
 * 给定一个矩阵,矩阵内元素为正数和负数,现在求和最大的子矩阵.
 * 分析:
 * 首先这个不是方阵.对于方阵,我们只需要知道任意一个点的下标,然后知道长度即可,或者两个点下标
 * 也可以解决这个问题.
 * 对于非方阵,至少需要三个点才可以确定一个矩形.
 * 假设最大子矩阵出现在原先矩阵的<i,j>行,对于列问题先暂时不考虑.继续分析.
 * 考虑只有一行的特殊情况,那么此时不就成了最大连续子列和问题吗?
 * 对于这个问题,我们已经有了O(n)的解决算法,那么如果能把多维降维度处理,那么就解决这个问题了.
 * <p>
 * 并且,可以注意到一点,我的一维最大连续子序列和问题是可以确定起始和结束下标的,这样矩阵的行宽和
 * 列宽都确定了,从而矩阵大小也确定了.
 * <p>
 * 现在问题转化为如何降维处理.这个地方出现了新知识点,矩阵压缩.
 * 比如,把第1行-第三行的转换为一维数组,那么我们只要把1-3行的列值全部相加,放在一维数组即可.
 * 这样就完成了压缩.
 * <p>
 * 现在压缩算法解决了,问题就转化的非常简单了.
 * <p>
 * 假设现在从0-n-1行.列数先不考虑.
 * <p>
 * 因为上面分析提到过,最大和子矩阵可以出现在<i,j>行.我们不确定是哪个,所以我们需要遍历所有的
 * 可能情况.
 * 这个地方从0开始,一直遍历到结尾.
 * 思路是这样的:
 * 0-1行,压缩,算出最大的子列和 --->压缩成一个一维数组,直接用已有算法求最大子列和.
 * 0-2行,压缩,算出最大的子列和 --->压缩成一个一维数组,直接用已有算法求最大子列和.
 * 比较0-1行与0-2行的最大子列和,如果0-2大一点,那么max = 0-2子列和.
 * 继续循环...
 * 到0-n-1行. 这个时候起始行为0的全部结束了.
 * 下一轮重新开始,从行1开始,然后1-n-1.
 * ...
 * 最后一直到n-2-n-1行.此时求出的最大子列和就是我们需要的值.
 * 求解完毕.
 * 下面编码实现.
 * <p>
 * 记录下编码过程中犯的低级错误.
 * 最外层循环的次数控制错了.我说怎么只有正方形矩阵才不报错,为啥长方形就报错,里面的最大循环变量设置错了.
 * 对于需要几层循环的模型还要加深理解.
 * 过早的清空数组也是个问题.应该在刚进入最外层循环时候清空,因为计算-3行的压缩值,需要用到0-2行.
 * 过早清空就出现问题了.
 * <p>
 * 所以这个地方我们也可以把这个功能给单独提取出来.
 * 写一个函数,计算i-j行的每列和.
 *
 *
 * 总结:
 * 这个问题的思路核心在于压缩矩阵,一维到二维的转化工作,然后利用一维的最大连续子序列和来解决问题.
 * 这个题目到此结束
 */


public class MaxSubMatrixSum {
    /**
     * @param matrix 矩阵
     * @param i      数组下标i,
     * @param j      数组下标j
     * @param k      总共列数.
     * @return i-j行,每行的列值和.
     */
    private static int[] sum(int[][] matrix, int i, int j, int k) {
        int[] dp = new int[k];
        for (int m = 0; m < k; m++) {
            dp[m] = calc(matrix, i, j, m);
        }
        return dp;
    }

    //计算矩阵中,i-j行,m列的所有元素和.
    private static int calc(int[][] matrix, int i, int j, int m) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += matrix[k][m];
        }
        return sum;
    }

    private static int maxSubMatrixSumV2(int[][] matrix) {
        int currentSum = 0, maxSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                //i-j行,列值压缩.
                int[] sum = sum(matrix, i, j, matrix[0].length);
                currentSum = maxSubSequenceSum(sum);
                if (maxSum < currentSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    private static int maxSubMatrixSum(int[][] matrix) {
        //设置一个数组用于存放压缩后的值.
        int maxSum = 0, currentSum = 0;
        int[] dp_copy = new int[matrix[0].length];
        int cols = matrix[0].length;
        int rows = matrix.length;

        for (int i = 0; i < rows; i++) { //0-rows-1列.
            for (int j = i; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    dp_copy[k] += matrix[j][k];
                }
                //到这里压缩完一轮,比如0-0行.
                //用最大子列和求解一个max出来.
                currentSum = maxSubSequenceSum(dp_copy);
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
            //下一轮压抑开始了,要把数组置空.
            Arrays.fill(dp_copy, 0);
        }
        return maxSum;
    }

    private static int maxSubSequenceSum(int[] dp) {
        //这个算法针对全负数处理有问题,当作特例进行处理.
        if (isAllNegative(dp)) {
            return maxInArray(dp);
        }
        int currentSum = 0, maxSum = 0;
        for (int i = 0; i < dp.length; i++) {
            currentSum += dp[i];
            if (maxSum < currentSum) {
                maxSum = currentSum;
            } else if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }

    private static int maxInArray(int[] dp) {
        Arrays.sort(dp);
        return dp[dp.length - 1];
    }

    private static boolean isAllNegative(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, -2, -7, 0, 3},
                {9, 2, -6, 2, 5},
                {-4, 1, -4, 1, 6},
                {-1, 8, 0, -2, 2}
        };

        System.out.println(maxSubMatrixSumV2(matrix));
        System.out.println(maxSubMatrixSum(matrix));
    }
}