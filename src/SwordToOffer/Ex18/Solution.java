package SwordToOffer.Ex18;

public class Solution {

    public void Mirror(TreeNode root) {
        if (root==null) return;
        process(root);
    }
    //辅助函数.
    private TreeNode process(TreeNode root) {
        if (root==null) return null;
        TreeNode tmp = root.left;
        root.left = process(root.right);
        root.right = process(tmp);
        return root;
    }


    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;


        public TreeNode(int val) {
            this.val = val;

        }
    }
}