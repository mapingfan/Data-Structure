package SwordToOffer.Ex06;

import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if (array==null||array.length==0) return 0;
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }
    public int minNumberInRotateArray_2(int [] array) {
        if (array==null||array.length==0) return 0;
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= array[right]) { //那么可以转到m+1,right区间.
                left = mid + 1;
            } else{
                right = mid;
            }
        }
        return array[left];
    }



    public static void main(String[] args) {
        int[] array = {3, 4, 5, 1, 2};
        Solution s = new Solution();
        System.out.println(s.minNumberInRotateArray(array));
        System.out.println(s.minNumberInRotateArray_2(array));
    }
}