package TreeProblem.MirrorOfTrr;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 求一颗树的镜像.
 * 其实很简单,递归交换左右子节点.
 * 下面实现
 */
public class Solution {

    private static void print(TreeNode root) {
        if (root == null) return;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            System.out.print(treeNode.getValue() + " ");
            if (treeNode.getLeft() != null) queue.addLast(treeNode.getLeft());
            if (treeNode.getRight() != null) queue.addLast(treeNode.getRight());
        }
    }

    private static TreeNode solution(TreeNode root) {
        if (root == null) return null;
        //寻找递归出口条件.
//        if (root.getLeft() == null && root.getRight() == null) {
//            return root;
//        }


        TreeNode tmp = root.getLeft();
        root.setLeft(solution(root.getRight()));
        //因为上一步已经把root的left改变了,如果下面还是root.getLeft,那么可能就出了问题.所以得先保存值.
        root.setRight(solution(tmp));
//        root.setRight(solution(root.getLeft()));

        return root;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
//        TreeNode n6 = new TreeNode(6);
//        TreeNode n7 = new TreeNode(7);
        TreeNode root = n1;

        n1.setLeft(n2);
        n1.setRight(n3);

        n2.setLeft(n4);
        n2.setRight(n5);

//        n3.setLeft(n6);
//        n3.setRight(n7);

//        n6.setRight(n7);
        print(root);
        solution(root);
        System.out.println();
        print(root);

    }
}
