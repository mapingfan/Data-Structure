package DynamicProgramming.MaxSubSquare;

import java.util.Arrays;

/**
 * 在一个0,1构成的矩阵中,找一个最大子方阵,全由1构成.
 * <p>
 * 0 1 1 0 1
 * 1 1 0 1 0
 * 0 1 1 1 0
 * 1 1 1 1 0
 * 1 1 1 1 1
 * 0 0 0 0 0
 * <p>
 * 最大方阵肉眼可见,但是如何用代码求呢?
 * <p>
 * 这个地方先把答案抄写下来.
 * 1) 原矩阵为A[m][n],基于这个矩阵构造一个L[m][n], L[i][j]表示包括A[i][j]在内的值全为1的子方阵的大小.
 * 2) 把L[m][n]的第一行第一列设置同A[m][n]一样.
 * 3) 对于L中的其他元素,按照下面公司计算:
 * if(A[i][j]!=0){
 * L[i][j] = min(L[i-1][j],L[i-1][j-1],L[i][j-1])+1;
 * } else{
 * L[i][j] = 0;
 * }
 * 4)找出L[m][n]中的最大值,并且记录最大值的两个下标.
 * 5)根据找出的最大值,和连个下标.去A中输出子阵.
 * for(int i = max_i; i> max_i - max_L; i--){
 * for(int j = max_j; j>max_j - max_L; j--){
 * System.out.print(A[i][j]+" ");
 * }
 * System.out.println();
 * }
 * <p>
 * 时空复杂度都是O(mn).
 * 查阅了许多资料,终于把这个问题搞明白了.
 * 可以参考这个链接:http://hijob0533.blog.163.com/blog/static/189626274201152943040374
 * 具体实现明天写.
 * <p>
 * 实现下求最大子方阵.
 *
 * 上面给了动态规划的思路,下面给下递归的思路.
 * if A[i][j]==1
 * size(i,j) = min(L[i-1][j],L[i-1][j-1],L[i][j-1])+1
 * else
 * size(i,j) = max(L[i-1][j],L[i-1][j-1],L[i][j-1])
 *
 * 还有一些边界值的处理.
 *
 */
public class MaxSubSquare {

    //m,n代表下标.所以src的要分配m+1/n+1.
    private static int maxSubSquareSize(int[][] src, int m, int n) {
        //构造一个二维举证.
        int[][] copy = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            copy[0][i] = src[0][i];
        }
        for (int i = 0; i <= m; i++) {
            copy[i][0] = src[i][0];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (src[i][j] != 0) {
                    copy[i][j] = 1 + min(copy[i - 1][j - 1], copy[i - 1][j], copy[i][j - 1]);
                } else {
                    copy[i][j] = 0;
                }
            }
        }
        int max = copy[0][0], max_i = 0, max_j = 0;
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                if (max < copy[i][j]) {
                    max = copy[i][j];
                    max_i = i;
                    max_j = j;
                }
            }
        }
//        for (int i = max_i; i > max_i - max; i--) {
//            for (int j = max_j; j > max_j - max; j--) {
//                System.out.print(src[i][j] + " ");
//            }
//            System.out.println();
//        }
        return max;
    }

    //用递归写一次.m,n表示下标.
    private static int maxSubSquareSizeV2(int[][] src, int m, int n) {
        if (m == 0) {
            if (oneExistInArrayRow(src,n)) {
                return 1;
            }
            return 0;
        }
        if (n == 0) {
            if (oneExistInArrayCol(src,m)) {
                return 1;
            }
            return 0;
        }
        if (src[m][n] != 0) {
            return 1 + min(
                    maxSubSquareSizeV2(src, m - 1, n - 1),
                    maxSubSquareSizeV2(src, m - 1, n),
                    maxSubSquareSizeV2(src, m, n - 1)
            );
        } else {
            return max(
                    maxSubSquareSizeV2(src, m - 1, n - 1),
                    maxSubSquareSizeV2(src, m - 1, n),
                    maxSubSquareSizeV2(src, m, n - 1));
        }



    }

    private static boolean oneExistInArrayCol(int[][] src, int m) {
        for (int i = 0; i <= m; i++) {
            if (src[i][0] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean oneExistInArrayRow(int[][] src, int n) {
        for (int i = 0; i <= n; i++) {
            if (src[0][i] == 1) {
                return true;
            }
        }
        return false;

    }

    private static int max(int i, int i1, int i2) {
        int[] arr = {i, i1, i2};
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }


    private static int min(int i, int j, int k) {
        int[] arr = {i, j, k};
        Arrays.sort(arr);
        return arr[0];
    }

    public static void main(String[] args) {
        int[][] src = {
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1}
        };
        System.out.println(maxSubSquareSize(src, 3, 4));
        System.out.println(maxSubSquareSize(src, 3, 4));
    }


}
