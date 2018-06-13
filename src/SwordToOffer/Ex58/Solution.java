package SwordToOffer.Ex58;


import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (k <= 0 || pRoot == null) return null;
        ArrayList<TreeNode> result = inOrder(pRoot);
        return k <= result.size() ? result.get(k - 1) : null;
    }

    private ArrayList<TreeNode> inOrder(TreeNode pRoot) {
        ArrayList<TreeNode> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || pRoot != null) {
            while (pRoot != null) {
                stack.push(pRoot);
                pRoot = pRoot.left;
            }
            if (!stack.isEmpty()) {
                pRoot = stack.pop();
                result.add(pRoot);
                pRoot = pRoot.right;
            }
        }
        return result;
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