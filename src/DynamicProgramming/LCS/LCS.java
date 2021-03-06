package DynamicProgramming.LCS;

import java.util.Arrays;

public class LCS {

    /**
     * 求最长公共子序列.
     * 这个问题看起来就很复杂,暴力法可以解决,比如求出A的所有组合.然后拿到B中进行比较.
     * 所以是否可以考虑递归.把问题转换为子问题的解决.
     * 假设A,B的第一个字母匹配,那么问题就转换为lcs(A-1,B-1)的匹配问题.
     * 如果第一个不匹配,那么有两个可能,lcs(A-1,B)/lcs(A,B-1).
     * 通过递归条件导出边界值.找到递归式,就是采用两种递归方法进行合并求解.
     * 关键是观察能否转换为更小规模的子问题.
     * 这个地方只是输出长度,并没有输出所有可能的字符序列.
     *
     * @param arrA 序列A.
     * @param arrB 序列B
     * @param aS   序列A的起始位置
     * @param aE   序列B的起始位置.
     * @param bS
     * @param bE   s = start /E = end
     * @return 返回最长公共子序列.
     */
    private static int rec_lcs(char[] arrA, char[] arrB, int aS, int aE, int bS, int bE) {
        if (aS == aE) {
            if (contain(arrB, arrA[aS])) {
                return 1;
            } else {
                return 0;
            }
        }
        if (bS == bE) {
            if (contain(arrA, arrB[bS])) {
                return 1;
            } else {
                return 0;
            }
        }
        if (arrA[aS] == arrB[bS]) {
            return rec_lcs(arrA, arrB, aS + 1, aE, bS + 1, bE) + 1;
        } else {
            return Math.max(
                    rec_lcs(arrA, arrB, aS + 1, aE, bS, bE),
                    rec_lcs(arrA, arrB, aS, aE, bS + 1, bE)
            );
        }
    }

    static int[][] dp = new int[7][6];

    private static int dp_lcs_V2(char[] arrA, char[] arrB, int aS, int bS) {

        if (arrA[arrA.length - 1] == arrB[arrB.length - 1]) {
            dp[arrA.length - 1][arrB.length - 1] = 1;
        } else {
            dp[arrA.length - 1][arrB.length - 1] = 0;
        }
        if (arrA[arrA.length - 1] == arrB[arrB.length - 1] || arrA[arrA.length - 1] == arrB[arrB.length - 2]) {
            dp[arrA.length - 1][arrB.length - 2] = 1;
        } else {
            dp[arrA.length - 1][arrB.length - 2] = 0;
        }
        if (arrB[arrB.length - 1] == arrA[arrA.length - 1] || arrB[arrB.length - 1] == arrA[arrA.length - 2]) {
            dp[arrA.length - 2][arrB.length - 1] = 1;
        } else {
            dp[arrA.length - 2][arrB.length - 1] = 0;
        }
        //需要计算边角值.

        for (int i = arrA.length - 2; i >= 0; i--) {
            for (int j = arrB.length - 2; j >= 0; j--) {
                if (arrA[i] == arrB[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }



    private static boolean contain(char[] arr, char value) {
        //数组arr中是否包含 value值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[] A = {'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] B = {'B', 'D', 'C', 'A', 'B', 'A'};
//        char[] A = {'A', 'B'};
//        char[] B = {'C', 'D'};
        System.out.println(dp_lcs_V2(A, B, 0, 0));
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("输出最大序列");
        int aI = 0;
        int bI = 0;
        while (aI < A.length - 1 && bI < B.length - 1) {
            if (dp[aI + 1][bI + 1] == dp[aI][bI]) {
                bI += 1;
                aI += 1;
            } else {
                if (A[aI] != B[bI] && A[aI + 1] == B[bI] && dp[aI + 1][bI] > dp[aI][bI + 1]) {
                    aI += 1;
                } else {
                    if (A[aI] != B[bI] && A[aI] == B[bI + 1]) {
                        bI += 1;
                    }
                }
            }
            if (A[aI] == B[bI])
            {
                System.out.println(A[aI]);
            }

        }

    }
}

/**
 *    0 1 2 3 4 5
 * 0  4 3 3 2 1 0
 * 1  4 3 3 2 1 0
 * 2  4 3 3 2 1 0
 * 3  4 3 2 2 1 0
 * 4  3 3 2 2 1 0
 * 5  2 2 2 2 1 1
 * 6  0 0 0 0 1 0
 */