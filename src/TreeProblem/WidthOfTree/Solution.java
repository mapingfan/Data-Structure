package TreeProblem.WidthOfTree;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 求树宽度
 */
public class Solution {

    private static int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        int currentWidth = 1;
        int nextLevelWidth = 0;
        int maxWidth = currentWidth;
        while (!queue.isEmpty()) {
            while (currentWidth-- > 0) {
                TreeNode treeNode = queue.removeFirst();
                if (null != treeNode.getLeft()) {
                    nextLevelWidth++;
                    queue.addLast(treeNode.getLeft());
                }
                if (null != treeNode.getRight()) {
                    nextLevelWidth++;
                    queue.addLast(treeNode.getRight());
                }
            }
            currentWidth = nextLevelWidth;
            nextLevelWidth = 0;
            if (maxWidth < currentWidth) {
                maxWidth = currentWidth;
            }
        }
        return maxWidth;
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

//        n6.setRight(n7);

        System.out.println(solution(root));

    }

}
