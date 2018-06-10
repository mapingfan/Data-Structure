package SwordToOffer.Ex27;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (k > input.length || input.length == 0 || input == null) return new ArrayList<>();
        Arrays.sort(input);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> result = new Solution().GetLeastNumbers_Solution(array, 4);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}