package ExDay5;

/**
 * 岛问题.
 * 对于一个给定矩形,每个位置可以和自己的上下左右4个位置相连,如果有一片1连在一起,成为一个岛.
 * 矩形中只有0,1两种元素.
 * <p>
 * 分析问题:
 * 给定矩形,求所有1连成的岛.左神给了一个感染的思路.非常精妙.
 * 矩阵按行遍历,如果当前值是1,那么从这个位置开始感染其四周的点.
 * 如果是0,那么跳到下一个.对于被感染的值,设置成2,表示已经感染,下次不在进行感染.
 * <p>
 * 最后感染的次数就是总的岛数.核心就是实现一个感染函数.
 */
public class Ex01 {

    private static int solution(int[][] matrix) {
        if (matrix == null) return 0;
        int width = matrix[0].length;
        int height = matrix.length;
        int cnt = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != 1) {
                    continue;
                } else {
                    cnt++;
                    infect(matrix, i, j, width, height);

                }
            }
        }
        return cnt;
    }

    /**
     * 感染点(i,j)的上下左右区域.
     *
     * @param matrix
     * @param i
     * @param j
     * @param width
     * @param height
     */
    private static void infect(int[][] matrix, int i, int j, int width, int height) {
        if (i < 0 || i >= height || j < 0 || j >= width) return;
        matrix[i][j] = 2; //被感染的点设置为2 .
        //顺时针感染.从上开始进行.
        if (check(i - 1, j, width, height) && matrix[i - 1][j] == 1) infect(matrix, i - 1, j, width, height);
        if (check(i, j + 1, width, height) && matrix[i][j + 1] == 1) infect(matrix, i, j + 1, width, height);
        if (check(i + 1, j, width, height) && matrix[i + 1][j] == 1) infect(matrix, i + 1, j, width, height);
        if (check(i, j - 1, width, height) && matrix[i][j - 1] == 1) infect(matrix, i, j - 1, width, height);
    }

    private static boolean check(int i, int j, int width, int height) {
        if (i < 0 || i >= height || j < 0 || j >= width) return false;
        return true;
    }

    private static void print(int[][] matrix) {
        if (matrix==null) return;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int[][] m1 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(solution(m1));
        print(m1);

        int[][] m2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(solution(m2));
        print(m2);

        int[][] m3 = {
                {0, 0, 1, 0, 1, 0},
                {1, 1, 1, 0, 1, 0},
                {1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        System.out.println(solution(m3));
        print(m3);
    }

}
