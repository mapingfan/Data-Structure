package SwordToOffer.Ex04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {

    private HashMap<Integer, Integer> help = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length || pre.length == 0 || in.length == 0) return null;
        for (int i = 0; i < in.length; i++)
            help.put(in[i], i);
        return null;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in, int preL, int preR, int inL, int inR) {
        if (preL>preR) return null;
        TreeNode root = new TreeNode(pre[preL]);
        Integer inIndex = help.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = reConstructBinaryTree(pre, in, preL + 1, preL + leftTreeSize, inL, inL + leftTreeSize - 1);


        return null;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode node = new Solution().reConstructBinaryTree(pre, in);
        System.out.println();
    }
}