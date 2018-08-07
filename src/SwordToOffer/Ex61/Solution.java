package SwordToOffer.Ex61;

public class Solution {
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold <= 0) return 0;
        //矩阵按行查找,查找每行能走的最大格数.
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            search(i, cols, threshold, matrix);
        }
        //matrix中所有值为1的格子,都表示机器人能到的地方.
        //求matrix中所有可达的1.对于每个位置设置一个感染函数.
//        print(matrix);

        int result = countOneAdjacentInMatrix(matrix);
//        System.out.println("After Infect ");
//        print(matrix);


        return result;
    }

    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int countOneAdjacentInMatrix(int[][] matrix) {
        infect(matrix, 0, 0);
        return countOneInMatrix(matrix, 2);
    }

    private void infect(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] != 1) return;
        matrix[i][j] = 2;
        infect(matrix, i, j - 1);
        infect(matrix, i + 1, j);
        infect(matrix, i, j + 1);
        infect(matrix, i - 1, j);
    }

    private int countOneInMatrix(int[][] matrix, int v) {
        int cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == v) cnt++;
            }
        }
        return cnt;
    }

    private void search(int i, int cols, int threshold, int[][] matrix) {

        for (int j = 0; j < cols; j++) {
            if (bitSum(i) + bitSum(j) <= threshold) matrix[i][j] = 1;
        }
    }


    //机器从(i,j)出发,然后走.

    /**
     * 思路错误!
     *
     * 分析:
     * 要求机器人最多可以走的格子,如下考虑问题:
     * case 1: 第一次移动往上移动
     * case 2: 第一次移动往右移动
     * case 3: 第一次移动往下移动
     * case 4: 第一次移动往左移动
     * <p>
     * 只要求出这四种移动情况后的最大值即可.
     * <p>
     * tip:    1.边界问题需要考虑,(i,j)不能越过四个边界.
     * 2.递归考虑解决问题.
     */


    /**
     * @param num
     * @return 返回num的各位之和, 如 num=123, 那么最后返回1+2+#= 6
     */
    private int bitSum(int num) {
        if (num < 0) return -1; //异常情况.
        if (num < 10) return num;
        return num % 10 + bitSum(num / 10);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.movingCount(10, 1, 100));
    }
}