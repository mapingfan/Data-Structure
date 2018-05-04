package ExDay2;

/**
 * 在行和列都有序的矩形总寻找数字,比如m行n列的矩阵.要求时间复杂度为O(m+n).空间复杂度的O(1).
 * 这种最优解取决于所给数据的特殊性.
 * 从右上角出发寻找.
 * 比如矩形为
 * 0 1 2 5
 * 2 3 4 7
 * 4 4 4 8
 * 5 7 7 9
 * <p>
 * 假设我们寻找3,从右上角开始,5比3大,那么不能往下走了,只能往左走.
 * 对于下一个点继续判断应该往左还是往下.
 * while循环判断,如果任何一个出边界还没有找到,返回false.
 */
public class ex13 {


    private static boolean find(int[][] matrix, int value) {
        if (matrix == null) return false;
        int row = 0;
        int col = matrix[0].length - 1;
        //从右上角开始查找.
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == value) return true;
            if (matrix[row][col] < value) row++;
            else col--;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6},// 0
                {10, 12, 13, 15, 16, 17, 18},// 1
                {23, 24, 25, 26, 27, 28, 29},// 2
                {44, 45, 46, 47, 48, 49, 50},// 3
                {65, 66, 67, 68, 69, 70, 71},// 4
                {96, 97, 98, 99, 100, 111, 122},// 5
                {166, 176, 186, 187, 190, 195, 200},// 6
                {233, 243, 321, 341, 356, 370, 380} // 7
        };
        int value = 243;
        System.out.println(find(matrix, value));
    }
}
