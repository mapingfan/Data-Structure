package TreeProblem.DepthOfTree;

import TreeProblem.TreeNode;

/**
 * 求树的深度
 */
public class Solution {

    private static int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = solution(root.getLeft());
        int rightDepth = solution(root.getRight());
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
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

        System.out.println(solution(root));

    }
}
