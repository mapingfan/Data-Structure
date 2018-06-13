package SwordToOffer.Ex56;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) return new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        queue.addLast(pRoot);
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            while (curSize-- > 0) {
                TreeNode node = queue.removeFirst();
//                    System.out.println(node.val);
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
                tmp.add(node.val);
            }
            result.add(tmp);
        }

        return result;
    }


    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(9);
        node1.left = node2;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        node4.left = node7;
        node4.right = node8;


        ArrayList<ArrayList<Integer>> result = new Solution().Print(node1);
        for (ArrayList<Integer> arrayList : result) {
            for (Integer one : arrayList) {
                System.out.print(one + " ");
            }
            System.out.println();
        }
    }
}