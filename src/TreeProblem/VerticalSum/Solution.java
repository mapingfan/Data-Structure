package TreeProblem.VerticalSum;

import TreeProblem.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树的垂直和.
 * 利用中序遍历,辅助行数组表进行计算.
 *
 *
 * n个节点,可以组成2^n-n种二叉树.
 *
 */
public class Solution {
    static Map<Integer, Integer> result = new HashMap<>();

    private static void solution(TreeNode root, int column) {
        if (root == null) return;
        solution(root.getLeft(), column - 1);
        result.put(column, root.getValue() + (result.get(column) == null ? 0 : result.get(column)));
        solution(root.getRight(), column + 1);
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

        solution(root, 0);
        for (Integer integer : result.values()) {
            System.out.println(integer);
        }
    }


}
