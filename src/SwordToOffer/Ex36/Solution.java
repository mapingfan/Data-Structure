package SwordToOffer.Ex36;

public class Solution {
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        int lDepth = TreeDepth(root.left);
        int rDepth = TreeDepth(root.right);
        return Math.max(lDepth + 1, rDepth + 1);
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}