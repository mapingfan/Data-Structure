package SwordToOffer.Ex54;

public class Solution {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        String result = serialize(pRoot);
        System.out.println(result);

        //镜像二叉树.
        pRoot = mirror(pRoot);
        String afterResult = serialize(pRoot);
        System.out.println(afterResult);

        if (result.equals(afterResult)) {
            return true;
        }
        return false;
    }

    private TreeNode mirror(TreeNode pRoot) {
        if (pRoot == null) return pRoot;
        TreeNode tmp = pRoot.right;
        pRoot.right = mirror(pRoot.left);
        pRoot.left = mirror(tmp);
        return pRoot;
    }

    private String serialize(TreeNode pRoot) {
        if (pRoot == null) return "!#";
        return pRoot.val + "#" + serialize(pRoot.left) + serialize(pRoot.right);
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(6);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(5);
        node1.left = node2;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        node4.left = node7;
        node4.right = node8;

        System.out.println(new Solution().isSymmetrical(node1));

    }
}