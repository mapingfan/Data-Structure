package SwordToOffer.Ex48;


public class Solution {
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0) return null;
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = buildBIth(A, i);
        }
        return result;
    }

    public int buildBIth(int[] A, int i) {
        int res = 1;
        for (int j = 0; j < i; j++) {
            res *= A[j];
        }
        for (int j = i + 1; j < A.length; j++) {
            res *= A[j];
        }
        return res;
    }
}