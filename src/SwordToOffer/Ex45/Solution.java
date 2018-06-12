package SwordToOffer.Ex45;

public class Solution {
    public int Sum_Solution(int n) {
        if (n < 0) return 0;
        if (n <= 1) return n;
        return n + Sum_Solution(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().Sum_Solution(100));
    }
}