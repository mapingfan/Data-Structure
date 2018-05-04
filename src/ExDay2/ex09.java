package ExDay2;

/**
 * 将正方形旋转90度.
 *
 * 两种方法:
 *  方法一: 直接观察规律,原数组的第i行,成为变换后得第n-i-1列.(从0行开始计算,n是正方形宽度)
 *  这个方法需要开辟一个同大小的数组,然后拷贝回来.
 *
 *  方法二:
 *      原地变换.变换策略是这样的,先旋转最外层正方形,然后是内层正方形.不过需要计算左边,比较繁琐.
 *      如果不卡空间,直接开辟数组解决过掉.
 *
 */
public class ex09 {
    private static int[][] rotate(int[][] matrix) {
        int len = matrix.length;
        int tr = 0, tc = 0, dr = len - 1, dc = len - 1;
        while (tr <= dr && tc <= dc)
            rotateEdge(matrix, tr++, tc++, dr--, dc--);
        return matrix;
    }

    private static void rotateEdge(int[][] matrix, int tr, int tc, int dr, int dc) {
        for (int i = tr; i < dr; i++) {
            subRotate(matrix, tr, i, matrix.length);
        }
    }

    private static void subRotate(int[][] matrix, int ti, int tj, int length) {
        int tmp = matrix[ti][tj];
        matrix[ti][tj] = matrix[length - 1 - tj][ti];
        matrix[length - 1 - tj][ti] = matrix[length - 1 - ti][length - tj - 1];
        matrix[length - 1 - ti][length - tj - 1] = matrix[tj][length - 1 - ti];
        matrix[tj][length - 1 - ti] = tmp;
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 对数方法.
     *
     * @param matrix
     */
    private static void comparator(int[][] matrix) {
        int[][] copy = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            copy(matrix, i, copy);
        }
        for (int i = 0; i < copy.length; i++) {
            System.arraycopy(copy[i], 0, matrix[i], 0, copy[i].length);
        }
    }

    private static int[][] copyArray(int[][] matrix) {
        int[][] copy = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, matrix[i].length);
        }
        return copy;
    }

    private static void copy(int[][] matrix, int i, int[][] copy) {
        for (int j = 0; j < matrix.length; j++) {
            copy[j][matrix.length - 1 - i] = matrix[i][j];
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 44},
                {5, 6, 7, 8, 88},
                {9, 10, 11, 12, 1212},
                {13, 14, 15, 16, 1616},
                {17, 18, 19, 20, 2020}
        };

        int[][] copyArray = copyArray(matrix);
//        print(copyArray);


        //        print(matrix);
        rotate(matrix);

        print(matrix);

        System.out.println("----------");

        comparator(copyArray);
        print(copyArray);
    }

}
