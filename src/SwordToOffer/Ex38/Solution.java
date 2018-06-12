package SwordToOffer.Ex38;

import java.util.HashSet;

//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class Solution {
    //空间换时间策略.

    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length <= 1) return;
        if (array.length == 2 && (num1[0] = array[0]) != (num2[0] = array[1])) return;
        HashSet<Integer> filter = new HashSet<>();
        HashSet<Integer> help = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (!filter.contains(array[i])) {
                filter.add(array[i]);
            } else {
                help.add(array[i]);
            }
        }
        //help里面包含所有重复元素.
        int i;
        for (i = 0; i < array.length; i++) {
            if (!help.contains(array[i])) {
                num1[0] = array[i];
                break;
            }
        }
        for (int j = i+1; j < array.length; j++) {
            if (!help.contains(array[j])) {
                num2[0] = array[j];
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        new Solution().FindNumsAppearOnce(new int[]{0,1, 1, 2, 2, 3, 3, 4, 5}, num1, num2);
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }
}