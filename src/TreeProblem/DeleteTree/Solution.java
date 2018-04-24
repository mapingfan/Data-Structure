package TreeProblem.DeleteTree;

import TreeProblem.TreeNode;

/**
 * 删除二叉树
 */
public class Solution {
    //删除操作就是将节点置为null.
    private static void solution(TreeNode root) {
        if (root == null) {
            return;
        }
        solution(root.getLeft());
        solution(root.getRight());
        root = null;
    }

}
