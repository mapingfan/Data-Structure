package TreeProblem.InsertNode;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 在二叉树中插入一个节点
 * 层次遍历,寻找一个节点的左孩子或者右孩子为空,插入完成.
 */
public class Solution {

    private static void insert(TreeNode root, int value) {
        TreeNode node = new TreeNode(value);
        if (root == null) {
            root = node;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode first = queue.removeFirst();
            if (first.getLeft() == null) {
                first.setLeft(node);
                return;
            } else {
                queue.addLast(first.getLeft());
            }
            if (first.getRight() == null) {
                first.setRight(node);
                return;
            } else {
                queue.addLast(first.getRight());
            }
        }
    }
}
