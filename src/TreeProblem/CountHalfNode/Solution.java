package TreeProblem.CountHalfNode;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 统计半节点.
 */
public class Solution {

    private static int count(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            if (treeNode.getLeft() != null && treeNode.getRight() == null) {
                count++;
                queue.addLast(treeNode.getLeft());
                continue; //直接跳入下一次循环.
            }
            if (treeNode.getRight() != null && treeNode.getLeft() == null) {
                count++;
                queue.addFirst(treeNode.getRight());
                continue;
            }
            if (treeNode.getLeft() != null && treeNode.getRight() != null) {
                queue.addLast(treeNode.getLeft());
                queue.addLast(treeNode.getRight());
            }
        }
        return count;
    }
        //递归版本就不实现了,写起来没有难度.
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

        }
}
