package ExDay2;

/**
 * 将正方形旋转90度.
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

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
//        print(matrix);
        rotate(matrix);
        print(matrix);

    }

}
