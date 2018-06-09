package SwordToOffer.Ex19;

import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return null;
        ArrayList<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int rowUp = 0, colUp = 0;
        int rowDown = rows - 1, colDown = cols - 1;
        while (rowUp <= rowDown && colUp <= colDown) {
            print_help(matrix, rowUp++, colUp++, result, rowDown--, colDown--);
        }
        return result;
    }

    //根据矩形对角线的两个点(rowUp,colUp) (rowDown,colDown),打印出矩形的最外层.
    private void print_help(int[][] matrix, int rowUp, int colUp, ArrayList<Integer> result, int rowDown, int colDown) {
        //对于边界情况处理.如一行一列情况.
        if (rowUp == rowDown) {
            for (int i = colUp; i <= colDown; i++) {
                result.add(matrix[rowUp][i]);
            }
            return;
        }
        if (colUp == colDown) {
            for (int i = rowUp; i <= rowDown; i++) {
                result.add(matrix[i][colUp]);
            }
            return;
        }

        for (int i = colUp; i < colDown; i++) {
            result.add(matrix[rowUp][i]);
        }
        for (int i = rowUp; i < rowDown; i++) {
            result.add(matrix[i][colDown]);
        }
        for (int i = colDown; i > colUp; i--) {
            result.add(matrix[rowDown][i]);
        }
        for (int i = rowDown; i > rowUp; i--) {
            result.add(matrix[i][colUp]);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        /*int[][] matrix = {
                {1},
                {2},
                {3},
                {4}
        };*/
        ArrayList<Integer> result = solution.printMatrix(matrix);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}