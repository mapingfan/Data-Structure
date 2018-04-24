package TreeProblem.LeafNodeCount;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 获取叶子节点个数.
 */
public class Solution {

    private static int countLeafNode(TreeNode root) {
        if (root == null) return 1;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            if (treeNode.getLeft() != null) {
                queue.addLast(treeNode.getLeft());
            }
            if (treeNode.getRight() != null) {
                queue.add(treeNode.getRight());
            }
            if (treeNode.getRight() == null && treeNode.getLeft() == null) {
                count++;
            }
        }
        return count;
    }

    private static int rec_count(TreeNode root) {
        if (root == null) return 0;
        if (root.getLeft() == null && root.getRight() == null) return 1;
//        int left = rec_count(root.getLeft());
//        int right = rec_count(root.getRight());
        return rec_count(root.getLeft()) + rec_count(root.getRight());
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode root = n1;

        n1.setLeft(n2);
        n1.setRight(n3);

        n2.setLeft(n4);
        n2.setRight(n5);

        n3.setLeft(n6);
        n3.setRight(n7);

//        n6.setRight(n7);


        System.out.println(countLeafNode(root));
        System.out.println(rec_count(root));

    }
}
