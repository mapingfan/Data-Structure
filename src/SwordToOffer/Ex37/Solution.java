package SwordToOffer.Ex37;

public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        return process(root).isBalance;
    }

    public ReturnData process(TreeNode root) {
        if (root == null) return new ReturnData(true, 0);
        ReturnData lResult = process(root.left);
        ReturnData rResult = process(root.right);
        if (!lResult.isBalance || !rResult.isBalance)
            return new ReturnData(false, Math.max(lResult.height, rResult.height) + 1);
        else
            return Math.abs(lResult.height - rResult.height) <= 1 ? new ReturnData(true, Math.max(lResult.height, rResult.height) + 1) : new ReturnData(false, Math.max(lResult.height, rResult.height) + 1);
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    private class ReturnData {
        boolean isBalance;
        int height;

        public ReturnData(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }
}