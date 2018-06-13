package SwordToOffer.Ex60;

import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (size > num.length) return result;
        if (size == 0) {
            return result;
        }
        for (int i = 0; i < num.length && i + size - 1 < num.length; i++) {
            result.add(calMaxInWindow(num, i, size));
        }
        return result;

    }

    private Integer calMaxInWindow(int[] num, int i, int size) {
        int max = num[i];
        for (int j = i + 1; j < i + size; j++) {
            max = Math.max(max, num[j]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 2, 6, 2, 5, 1};
        Solution solution = new Solution();
        ArrayList<Integer> arrayList = solution.maxInWindows(array, 3);
        for (Integer integer : arrayList) {
            System.out.print(integer + " ");
        }
    }
}