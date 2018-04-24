package TreeProblem.CountNode;

import Tree.BinarySearchTree.Tree;
import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 统计二叉树中节点个数
 */
public class Solution {
    private static int solution(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return solution(root.getLeft()) + solution(root.getRight()) + 1;
        }
    }

    private static int solutionV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int count = 0;

        while (!queue.isEmpty()) {

            count++;
            TreeNode node = queue.removeFirst();
            if (node.getLeft() != null) {
                queue.addLast(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.addLast(node.getRight());
            }
        }
        return count;
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

//        n4.setRight(n5);

        System.out.println(solution(root));
        System.out.println(solutionV2(root));

    }

}
