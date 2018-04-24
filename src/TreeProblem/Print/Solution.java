package TreeProblem.Print;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 横向,从底到上,从左到用输出.
 *
 * 栈和队列结合使用.队列正常使用,只是先加如右节点,然后左节点.并且把每个节点加入栈中.
 * 这样最后的栈的结果就是这样的横向输出.
 */
public class Solution {

    private static void solution(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (node.getRight() != null) {
                queue.addLast(node.getRight());
//                stack.addFirst(node.getRight());
            }
            if (node.getLeft() != null) {
                queue.addLast(node.getLeft());
//                stack.addFirst(node.getLeft());
            }
            stack.addFirst(node);
        }
        //输出队列中的元素.
        while (!stack.isEmpty()) {
            System.out.println(stack.removeFirst().getValue());
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
        n3.setRight(n7);

        solution(root);

    }

}
