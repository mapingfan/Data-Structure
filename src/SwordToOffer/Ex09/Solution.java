package SwordToOffer.Ex09;
public class Solution {
    public int JumpFloorII(int target) {
        if (target<=0) return 1;
        if (target==1) return 1;
        if (target==2) return 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < target + 1; i++) {
            dp[i] = process(dp, i - 1);
        }
        return dp[target];
    }
    //计算dp[0]+...dp[i]
    private int process(int[] dp, int i) {
        int sum = 0;
        for (int k = 0; k <= i; k++) {
            sum += dp[k];
        }
        return sum;
    }
}