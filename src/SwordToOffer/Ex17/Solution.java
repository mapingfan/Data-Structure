package SwordToOffer.Ex17;


import Tree.BinarySearchTree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;

//输入两棵二叉树A，B，判断B是不是A的子结构
//约定空树不是任意一个树的子结构
//在root1中找到所有和root2值相等的节点.
public class Solution {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1==null||root2==null) return false;
        ArrayList<TreeNode> help = findNodeEqualRoot2(root1, root2);
        for (TreeNode root : help) {
            if (help(root, root2)) return true;
            else continue;
        }
        return false;
    }

    private boolean help(TreeNode root, TreeNode root2) {
        if (root2==null) return true;
        if (root==null) return false;
        if (root.val!=root2.val) return false;
        return help(root.left, root2.left) && help(root.right, root2.right);
    }

    private ArrayList<TreeNode> findNodeEqualRoot2(TreeNode root1, TreeNode root2) {
        //root1空判断前面已经进行过.
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<TreeNode> result = new ArrayList<>();
        queue.addLast(root1);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (node.val == root2.val) {
                result.add(node);
            }
            if (node.left!=null) queue.addLast(node.left);
            if (node.right!=null) queue.addLast(node.right);
        }
        return result;
    }


    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;


        public TreeNode(int val) {
            this.val = val;

        }
    }
}