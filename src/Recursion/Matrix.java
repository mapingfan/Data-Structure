package Recursion;

import java.util.Arrays;

/**
 *学习利用分治法解决矩阵乘法问题，由于分治法重在思想的感悟，这个地方就不写具体的代码。
 * 因为要分解成许多小矩阵，对代码能力锻炼不是很多，所以不进行拆分实验；
 * 这个地方有两个递归解决办法，一个是普通的分治，复杂度仍是O(n^3)。另一个STRASSEN算法，复杂度为O(n^log7);
 * 相比三次方算法，有了改进；
 */
public class Matrix {
    static int[][] matrixA = {
            {1,2,3,4},
            {3,4,1,3},
            {2,3,4,6},
            {2,5,6,7}
    };

    static int[][] matrixB = {
            {2,2,3,4},
            {3,5,1,3},
            {2,3,23,6},
            {2,5,6,21}
    };

    int[][] matrixC = new int[4][];
    

    public static void main(String[] args) {
        int[][] matrixC = matrixMultiplicationV1(matrixA,matrixB);
        System.out.println(Arrays.deepToString(matrixC));
    }

    public static int[][] matrixMultiplicationV1(int[][] matrixA, int[][] matrixB) {
        int[][] matrixC = new int[matrixA.length][];
        for (int i = 0; i < matrixA.length; i++) {
                matrixC[i] = new int[4];
        }
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                for (int k = 0; k < matrixA.length; k++) {
                    matrixC[i][j] = matrixA[i][k]*matrixB[k][j];
                }
            }
        }
        return matrixC;
    }

}
