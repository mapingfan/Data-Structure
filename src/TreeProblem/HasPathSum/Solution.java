package TreeProblem.HasPathSum;

import TreeProblem.TreeNode;

/**
 * 分析下这个问题:
 *  递归思路:
 *      树与子树的关系 -> 子树的路径和+跟值 =? k.
 */
public class Solution {

//是否存在路径和等于k.
    private static boolean solution(TreeNode root, int k) {
        if (k==0) return true;
        if (root==null) return false;  //隐含k!=0.
        if (root.getValue()==k) return true;
        boolean result = solution(root.getLeft(), k - root.getValue());
        if (result) return result;
        return solution(root.getRight(), k - root.getValue());
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
        System.out.println(solution(root, 88));

    }
}
