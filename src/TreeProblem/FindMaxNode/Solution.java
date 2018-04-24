package TreeProblem.FindMaxNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树查找值最大的节点;
 */
public class Solution {


    private static int solution(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftMax = solution(root.left);
        int rightMax = solution(root.right);
        return threeMax(leftMax, rightMax, root.value);
    }

    private static int threeMax(int leftMax, int rightMax, int value) {
        int[] arr = {leftMax, rightMax, value};
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }


    private static int solutionV2(TreeNode root) {
        //层次遍历找最大值.
        if (root == null) {
            throw new IllegalStateException("树空异常");
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int max = root.value;
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (node.value > max) {
                max = node.value;
            }
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
        return max;
    }



    public static void main(String[] args) {


        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(23);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode root = n1;

        n1.setLeft(n2);
        n1.setRight(n3);

        n2.setLeft(n4);

        n4.setRight(n5);

        System.out.println(solution(root));
        System.out.println(solutionV2(root));
    }

}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int value;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(TreeNode left, TreeNode right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
