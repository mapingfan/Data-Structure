package ExDay2;

/**
 * 转圈打印矩阵.最外圈,内圈逐层打印.
 * <p>
 * 宏观分析:
 * 考虑每一圈的打印,然后复用打印逻辑.
 */
public class ex08 {
    /**
     * @param matrix 矩形
     * @param tlR    矩形左上角row行坐标
     * @param tlC    矩形左上角col列坐标
     * @param drR    矩形右下角row行坐标
     * @param drC    矩形右下角col列坐标
     *               tl: Top left
     *               dr: Down right
     *               <p>
     *               这个函数的功能就是给定一个矩形的左上角,右下角,打印出矩形最外圈.
     */
    private static void printEdge(int[][] matrix, int tlR, int tlC, int drR, int drC) {
        if (tlR == drR) { //只有一行.
            for (int i = tlC; i <= drC; i++) {
                System.out.print(matrix[tlR][i] + " ");
            }
            return;
        }
        if (tlC == drC) {
            for (int i = tlR; i <= drR; i++) {
                System.out.print(matrix[i][tlC] + " ");
            }
            return;
        }
        for (int i = tlC; i < drC; i++) {
            System.out.print(matrix[tlR][i] + " ");
        }
        System.out.println();
        for (int i = tlR; i < drR; i++) {
            System.out.print(matrix[i][drC] + " ");
        }
        System.out.println();
        for (int i = drC; i > tlC; i--) {
            System.out.print(matrix[drR][i] + " ");
        }
        System.out.println();
        for (int i = drR; i > tlR; i--) {
            System.out.print(matrix[i][tlC] + " ");
        }
        System.out.println();
    }

    private static void printMatrix(int[][] matrix) {
        if (matrix == null) return;
        int tr = 0, tc = 0, dr = matrix.length - 1, dc = matrix[0].length - 1;
        while (tr <= dr && tc <= dc) { //注意最后的边界判断.
//            printEdge(matrix, tr, tc, dr, dc);
//            tr++;
//            tc++;
//            dr--;
//            dc--;
            //优化写法.五行成一行.
            printEdge(matrix, tr++, tc++, dr++, dr++);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 33, 22},
                {5, 6, 7, 8, 44, 12},
                {9, 10, 11, 12, 112, 121},
                {13, 14, 15, 16, 133, 323},
                {21, 21, 3, 132, 12, 132, 12}
        };

        printMatrix(matrix);
    }
}
