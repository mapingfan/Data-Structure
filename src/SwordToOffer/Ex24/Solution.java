package SwordToOffer.Ex24;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {


    /*public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        //中序遍历,将节点放到容器中,然后设置在指针走向即可.
        ArrayList<TreeNode> help = new ArrayList<>();
        inOrder(pRootOfTree, help);
        //遍历help设置指针.
        for (int i = 0; i < help.size() - 1; i++) {
            help.get(i).right = help.get(i + 1);
        }
        help.get(help.size() - 1).right = null;
        for (int i = help.size() - 1; i >= 1; i--) {
            help.get(i).left = help.get(i - 1);
        }
        help.get(0).left = null;
        return help.get(0);
    }

    private void inOrder(TreeNode pRootOfTree, ArrayList<TreeNode> help) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || pRootOfTree != null) {
            while (pRootOfTree != null) {
                stack.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            if (!help.isEmpty()) {
                pRootOfTree = stack.pop();
                help.add(pRootOfTree);
                pRootOfTree = pRootOfTree.right; //访问右边.
            }
        }
    }*/

   /* public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        if (pRootOfTree.left == null && pRootOfTree.right == null) {
            return pRootOfTree;
        }
        TreeNode left = Convert(pRootOfTree.left);
        //left是左子树的第一个节点.寻找左子树的最后一个节点.
        TreeNode lTail = left;
        while (lTail != null && lTail.right != null) {
            lTail = lTail.right;
        }
        //此时的lTail是左子树中的最后一个节点.
        if (left != null) {
            lTail.right = pRootOfTree;
            pRootOfTree.left = lTail;
        }
        TreeNode right = Convert(pRootOfTree.right);
        if (right != null) {
            pRootOfTree.right = right;
            right.left = pRootOfTree;
        }
        return left == null ? pRootOfTree : left;
    }*/

    public TreeNode Convert(TreeNode pRootOfTree) {
        return Convert_2(pRootOfTree).head;
    }

    public ComplexResult Convert_2(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return new ComplexResult(null, null);
        ComplexResult left = Convert_2(pRootOfTree.left);
        if (left.head != null) {
            left.tail.right = pRootOfTree;
            pRootOfTree.left = left.tail;
        }
        ComplexResult right = Convert_2(pRootOfTree.right);
        if (right.head != null) {
            pRootOfTree.right = right.head;
            right.head.left = pRootOfTree;
        }
        return new ComplexResult(left.head == null ? pRootOfTree : left.head,
                right.head == null ? pRootOfTree : right.tail);
    }


    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static class ComplexResult {
        TreeNode head;
        TreeNode tail;

        public ComplexResult(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }
}

