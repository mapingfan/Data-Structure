package DynamicProgramming.LCS;

import java.util.Arrays;

public class LCS {

    /**
     * 求最长公共子序列.
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


    //    下面用动态规划实现一次.
    /*static int[][] dp = new int[7][6];

    private static int dp_lcs(char[] arrA, char[] arrB, int aS, int bS) {
        if (dp[aS][bS] != 0) {
            return dp[aS][bS];
        }
        if (aS == arrA.length - 1) {
            if (contain(arrB, arrA[aS])) {
//                for (int i = 0; i < arrB.length; i++) {
//                    dp[aS][i] = 1;
//                }
                return 1;
            } else {
                return 0;
            }
        }
        if (bS == arrB.length - 1) {
            if (contain(arrA, arrB[bS])) {
//                for (int i = 0; i < arrA.length; i++) {
//                    dp[bS][i] = 1;
//                }
                return 1;
            } else {
                return 0;
            }
        }
        if (arrA[aS] == arrB[bS]) {
            if (dp[aS + 1][bS + 1] == 0) {
                dp[aS + 1][bS + 1] = rec_lcs(arrA, arrB, aS + 1, arrA.length - 1, bS + 1, arrB.length - 1) + 1;
            }
            return dp[aS][bS] = dp[aS + 1][bS + 1];
        } else {
            if (dp[aS + 1][bS] == 0) {
                dp[aS + 1][bS] = rec_lcs(arrA, arrB, aS + 1, arrA.length - 1, bS, arrB.length - 1);
            }
            if (dp[aS][bS + 1] == 0) {
                dp[aS][bS + 1] = rec_lcs(arrA, arrB, aS, arrA.length - 1, bS + 1, arrB.length - 1);
            }
            return dp[aS][bS] = Math.max(dp[aS + 1][bS], dp[aS][bS + 1]);
        }
    }*/

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
        for (int i = 0; i < A.length-1; i++) {
            for (int j = 0; j < B.length-1; j++) {
                if (dp[i + 1][j + 1] > dp[i][j + 1]) {
                    if (dp[i + 1][j + 1] > dp[i + 1][j]) {
                        if (A[i + 1] == B[j + 1]) {

                            System.out.println(A[i + 1]);
                        }
                    } else {
                        if (A[i + 1] == B[j]) {
                            System.out.println(A[i + 1]);
                        }
                    }
                } else {
                    if (dp[i][j + 1] > dp[i + 1][j]) {
                        if (A[i] == B[j + 1]) {

                            System.out.println(A[i]);
                        }
                    } else {
                        if (A[i + 1] == B[j]) {
                            System.out.println(A[i + 1]);
                        }
                    }
                }
            }
        }

    }
}

/**

      0 1 2 3 4 5
   0  4 3 3 2 1 0
   1  4 3 3 2 1 0
   2  4 3 3 2 1 0
   3  4 3 2 2 1 0
   4  3 3 2 2 1 0
   5  2 2 2 2 1 1
   6  0 0 0 0 1 0
 */