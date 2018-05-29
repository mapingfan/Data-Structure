package SwordToOffer.Ex04;

import java.util.HashMap;

public class Solution {

    private HashMap<Integer, Integer> help = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length || pre.length == 0 || in.length == 0) return null;
        for (int i = 0; i < in.length; i++)
            help.put(in[i], i);
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0, in.length - 1);
    }

    /**
     * @param pre  前序序列
     * @param preL 前序序列的左边界.
     * @param preR 前序序列的右边界.
     * @param inL  中序序列左边界.
     * @param inR  中序序列右边界.
     * @return 返回生成的根节点.
     * <p>
     * 思路:
     * 前序序列的第一个节点,可以把中序序列分割成两部分.
     * 即 左部分+根+右部分.
     * <p>
     * 原函数的功能是根据前序 和中序生成树.
     * 那么子问题:
     * 因为根据前序序列,可以递归划分出子序列.
     * <p>
     * 这个地方的难点就是划分出中序的同时,如何正确划分出前序.
     * 前序要和中序对应起来.
     * 这个地方其实就是找规律了.通过下标计算出.
     * <p>
     * 还有一个需要注意的点就是递归终止条件.
     * <p>
     * 我写了两个终止条件.
     * 一个是左边界>右边界,表明没有孩子节点,直接返回null.
     * 如果左边界有右边界,表明直接返回这个节点.
     * 比如序列中只有一个元素,那么直接返回这个节点就好.
     * <p>
     * 可以不用考虑中序的边界嘛?
     * <p>
     * 不用,因为左序和中序的序列一样长.如果前序序列中,左边界大于右边界,那么中序肯定也是.
     * 如果左边界等于右边界,那么中序中也是如此.
     */
    public TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL, int inR) {
        if (preL > preR) return null;
        TreeNode root = new TreeNode(pre[preL]);
        if (preL == preR) return root;
        Integer inIndex = help.get(root.val);
        root.left = reConstructBinaryTree(pre, preL + 1, preL + inIndex - inL, inL, inIndex - 1);
        root.right = reConstructBinaryTree(pre, inIndex - inL + preL + 1, preR, inIndex + 1, inR);
        return root;
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
        int[] pre = {};
        int[] in = {};
        TreeNode node = new Solution().reConstructBinaryTree(pre, in);
        System.out.println();
    }
}