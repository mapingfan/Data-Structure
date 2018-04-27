package TreeProblem.Zigzag;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 蜿蜒遍历
 */
public class Solution {

    private static void zigzag(TreeNode root) {
        if (root == null) return;
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> queue_help = new LinkedList<>();
        queue.addLast(root);
        int currentWidth = 1;
        int nextWidth = 0;
        boolean flag = true;
        System.out.print(root.getValue() + " ");
        while (!queue.isEmpty()) {
            while (currentWidth-- > 0) {
                TreeNode node = queue.removeFirst();
                if (node.getLeft() != null) {
                    queue.addLast(node.getLeft());
                    queue_help.addLast(node.getLeft());
                    nextWidth++;
                }
                if (node.getRight() != null) {
                    queue.addLast(node.getRight());
                    queue_help.addLast(node.getRight());
                    nextWidth++;
                }
            }
            currentWidth = nextWidth;
            nextWidth = 0;
            if (currentWidth == 0) {
                break;
            }
            if (flag) {
                flag = false;
                //反向输出.
                while (!queue_help.isEmpty()) {
                    TreeNode last = queue_help.removeLast();
                    System.out.print(last.getValue() + " ");
                }
            } else {
                //正向输出
                flag = true;
                while (!queue_help.isEmpty()) {
                    TreeNode first = queue_help.removeFirst();
                    System.out.print(first.getValue() + " ");
                }
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

//        n3.setLeft(n6);
        n3.setRight(n7);
        zigzag(root);

    }
}
