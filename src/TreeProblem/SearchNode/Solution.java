package TreeProblem.SearchNode;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 二叉树中查找给定的节点值,存在返回true,否则false.
 */
public class Solution {

    private static boolean solution(TreeNode root, int value) {
        if (root == null) {
            return false;
        }
        if (root.getValue() == value) {
            return true;
        } else {
            boolean result;
            result = solution(root.getLeft(), value);
            if (result) {
                return true;
            } else {
                return solution(root.getRight(), value);
            }
        }
    }

    private static boolean solutionV2(TreeNode root, int value) {
        if (root == null) {
            return false;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            if (treeNode.getValue() == value) {
                return true;
            }
            if (treeNode.getLeft() != null) {
                queue.addLast(treeNode.getLeft());
            }
            if (treeNode.getRight() != null) {
                queue.addLast(treeNode.getRight());
            }
        }
        return false;
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(23);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode root = n1;

        n1.setLeft(n2);
        n1.setRight(n3);

        n2.setLeft(n4);

        n4.setRight(n5);

        System.out.println(solution(root, 223));
        System.out.println(solutionV2(root, 23));

    }


}
