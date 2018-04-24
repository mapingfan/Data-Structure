package TreeProblem.DepthOfTree.DeepestNode.DeleteNode;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 删除给定节点值.
 * 这个地方的策略是狸猫换太子,我们每次删除都是删层次遍历最后一个节点.
 * 我们把要删除节点的值设置为最后一个节点,然后删除最后一个节点即可.
 * <p>
 * 对于二叉搜索树,堆的删除算法就有要求了,可以复习前面的内容.
 */
public class Solution {

    private static void solution(TreeNode root, int value) {
        if (null == root) return;
        TreeNode node = findNode(root, value);
        TreeNode lastNode = findLastNode(root);
        node.setValue(lastNode.getValue());
        Pair result = findLastNodeParent(root, lastNode);
        if (result.isLeft) {
            result.node.setLeft(null);
        } else {
            result.node.setRight(null);
        }
    }

    private static class Pair {
        TreeNode node;
        boolean isLeft;

        public Pair(boolean b, TreeNode treeNode) {
            isLeft = b;
            node = treeNode;
        }
    }


    private static Pair findLastNodeParent(TreeNode root, TreeNode lastNode) {
        //找lastNode的父节点.
        if (root == null) return null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            if (treeNode.getLeft() == lastNode) {
                return new Pair(true, treeNode);
            }
            if (treeNode.getRight() == lastNode) {
                return new Pair(false, treeNode);
            }
            if (null != treeNode.getLeft()) {
                queue.addLast(treeNode.getLeft());
            }
            if (null != treeNode.getRight()) {
                queue.addLast(treeNode.getRight());
            }
        }
        return null;
    }

    private static TreeNode findLastNode(TreeNode root) {
        if (root == null) return null;
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
        return treeNode; //最深的节点.
    }


    private static TreeNode findNode(TreeNode root, int value) {
        if (null == root) return null;
        if (root.getValue() == value) return root;
        TreeNode node = findNode(root.getLeft(), value);
        if (node != null) return node;
        else return findNode(root.getRight(), value);
    }

    private static void print(TreeNode root) {
        if (root == null) return;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            System.out.println(treeNode.getValue());
            if (null != treeNode.getLeft()) {
                queue.addLast(treeNode.getLeft());
            }
            if (null != treeNode.getRight()) {
                queue.addLast(treeNode.getRight());
            }
        }
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

        print(root);
        solution(root, 3);
        print(root);

    }

}
