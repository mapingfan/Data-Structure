package SwordToOffer.Ex40;

import java.util.ArrayList;

/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 * <p>
 * 思路:以每个梳子开始探测.
 */

public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        if (array == null || array.length == 1) return null;
        ArrayList<Integer> result = new ArrayList<>();
        if (array.length == 0 && sum == 0) return result;
        int first = 0, second = 0, multiply = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int pos = search(array, i, sum);
            if (pos != -1) {
                if (multiply > array[i] * array[pos]) {
                    multiply = array[i] * array[pos];
                    first = i;
                    second = pos;
                }
            }
        }

        if (first == second) return result; //不存在返回.
        result.add(array[first]);
        result.add(array[second]);
        return result;
    }

    //从i位置开始往后找,找到第一个array[i]+array[k]=sum的.找不到返回-1.
    private int search(int[] array, int i, int sum) {
        for (int j = i + 1; j < array.length; j++) {
            if (array[i] + array[j] == sum) return j;
        }
        return -1;

    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        ArrayList<Integer> result = new Solution().FindNumbersWithSum(array, 5);
        for (Integer tmp : result) {
            System.out.print(tmp + " ");
        }
    }
}