package SwordToOffer.Ex35;

public class Solution {
    public int GetNumberOfK(int[] array, int k) {
        return GetNumberOfK(array, 0, array.length - 1, k);
    }

    private int GetNumberOfK(int[] array, int begin, int end, int k) {
        if (array == null || (begin == end && array[begin] != k) || begin > end) return 0;
        if (begin == end && array[begin] == k) return 1;
        int mid = begin + (end - begin) / 2;
        if (array[mid]<k) return GetNumberOfK(array, mid + 1, end, k);
        else if (array[mid]>k) return GetNumberOfK(array, begin, mid - 1, k);
        else return 1 + GetNumberOfK(array, begin, mid - 1, k) + GetNumberOfK(array, mid + 1, end, k);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().GetNumberOfK(new int[]{}, 33));
    }
}