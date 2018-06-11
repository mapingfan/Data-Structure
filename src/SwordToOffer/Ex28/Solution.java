package SwordToOffer.Ex28;

public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        return FindGreatestSumOfSubArray(array, array.length);
    }

    //数组长度为n,分为两种情况.包含第n个数字,不包含第n个数字.
    private int FindGreatestSumOfSubArray(int[] array, int n) {
        if (array == null || array.length == 0) return 0;
        if (n == 1) return array[n - 1];
        return Math.max(includeNthElement(array, n), FindGreatestSumOfSubArray(array, n - 1));
    }

    //以第n个元素为末位的最大子列和.
    private int includeNthElement(int[] array, int n) {
        int maxSum = array[n - 1]; //
        int curSum = array[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            curSum += array[i];
            if (curSum > maxSum) maxSum = curSum;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = {6, -3, -2, 7, -15, 1, 2, 2};
        System.out.println(new Solution().FindGreatestSumOfSubArray(array));
    }
}