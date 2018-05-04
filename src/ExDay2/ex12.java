package ExDay2;

/**
 * zigzag打印矩阵
 * <p>
 * 1 2 3 4
 * 5 6 7 8
 * 3 4 7 9
 *
 * 通过观察,可以发现是打印一条条斜线.这个斜线通过两个点确定.
 * 两个点移动构成的直线,逐条打印这些线即可.
 *
 * 这个地方的策略是使用了两个点确定斜线.然后写出打印任意两个点构成斜线的策略.
 *
 * 抽象出一个策略后,就可以写while循环打印出所有的斜线.
 *
 */
public class ex12 {
    /**
     * @param lr
     * @param lc 确定一个点(lr,lc)
     * @param dr
     * @param dc 确定一个点(dr,dc)
     *           <p>
     *           然后打印这两个点确定的直线.
     */
    private static void printEdge(int[][] matrix, int lr, int lc, int dr, int dc, boolean order) {
        if (order) {
            while (dr >= lr && dc <= lc) {
                System.out.print(matrix[dr--][dc++] + "  ");
            }
        } else {
            while (lr <= dr && lc >= dc) {
                System.out.print(matrix[lr++][lc--] + " ");
            }
        }
    }

    private static void zigZag(int[][] matrix) {
        int lr, lc, dr, dc = 0;
        lr = lc = dr = dc;
        int height = matrix.length;
        int width = matrix[0].length;
        boolean order = true;
        while (dc < width && lr < height) {
            printEdge(matrix, lr, lc, dr, dc, order);
            order = !order;
            if (dr == height - 1) {
                dr = height - 1;
                dc++;
            } else {
                dr++;
            }
            if (lc == width - 1) {
                lc = width - 1;
                lr++;
            } else {
                lc++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {11, 23, 43, 12}};
        zigZag(matrix);

    }


}
