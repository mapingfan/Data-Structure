package TreeProblem.CountFullNode;

import Tree.BinarySearchTree.Tree;
import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 统计满节点个数.
 */
public class Solution {
    /**
     * 这个地方仔细思考下如何定义递归的出口?
     * 首先root==null,返回0是没问题的,那么还有别的出口嘛?
     *
     * 这个地方写出口条件要考虑仔细.实现起来反而没有层次遍历看的舒服.
     */
    private static int count(TreeNode root) {
        if (root == null) return 0;

        if (root.getLeft() != null && root.getRight() != null
                && root.getLeft().getLeft() == null
                && root.getLeft().getRight() == null
                && root.getRight().getLeft() == null
                && root.getRight().getRight() == null
                ) return 1;
        if (root.getLeft() != null && root.getRight() == null
                && root.getLeft().getLeft() == null
                && root.getLeft().getRight() == null) return 0;
        if (root.getLeft() == null && root.getRight() != null
                && root.getRight().getLeft() == null
                && root.getRight().getRight() == null) return 0;

        int left = count(root.getLeft());
        int right = count(root.getRight());
        if (root.getLeft() != null && root.getRight() != null) return left + right + 1;
        else return left + right;
    }

    private static int countV2(TreeNode root) {

        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            if (treeNode.getRight() != null && treeNode.getLeft() != null) {
                count++;
            }
            if (treeNode.getLeft() != null) {
                queue.addLast(treeNode.getLeft());
            }
            if (treeNode.getRight() != null) {
                queue.addLast(treeNode.getRight());
            }
        }
        return count;
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
//        n3.setRight(n7);

        n6.setRight(n7);

        System.out.println(count(root));
        System.out.println(countV2(root));
    }
}
