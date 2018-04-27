package TreeProblem.PrintAncestor;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 打印某个节点的所有祖先.即打印从根节点到此节点的路径值.
 * <p>
 * 考虑原树与子树的关系: 到某个节点的路径 = 输出根节点+子树到到此节点的路径.
 * 存在递归关系,可以递归书写.
 */
public class Solution {

    private static boolean print(TreeNode root, TreeNode givenNode) {
        if(root==null||givenNode==null) return false;
        if (root==givenNode) return true;
        if (print(root.getLeft(), givenNode) || print(root.getRight(), givenNode)) {
            System.out.println(root.getValue());
            return true;
        }
        return false;
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

        print(root, n6);
    }

}
