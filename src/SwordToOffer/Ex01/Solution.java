package SwordToOffer.Ex01;

public class Solution {
   /* public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0) return false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        return false;
    }*/
    //考虑数据的特殊性,有一个更高效的解法.
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0) return false;
        int row = 0, col = array[0].length - 1; //选取右上角的坐标.
        //从右上角开始遍历.
        while (row < array.length && col >= 0) {
            if (array[row][col] > target) {
                col--;
            } else if (array[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
}
