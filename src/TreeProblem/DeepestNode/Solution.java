package TreeProblem.DeepestNode;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 最深的节点.其实就是层次遍历的最后一个元素.
 */
public class Solution {

    private static TreeNode lastNode(TreeNode root) {
        if (root==null) return null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        TreeNode treeNode = null;
        while (!queue.isEmpty()) {
            treeNode = queue.removeFirst();
            if (null != treeNode.getLeft()) {
                queue.addLast(treeNode.getLeft());
            }
            if (null != treeNode.getRight()) {
                queue.addLast(treeNode.getRight());
            }
        }
        return treeNode;
    }

}
