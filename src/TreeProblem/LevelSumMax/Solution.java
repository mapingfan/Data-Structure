package TreeProblem.LevelSumMax;

import TreeProblem.TreeNode;

import java.util.LinkedList;

/**
 * 求同一层节点之和最大值.
 * 层次遍历时,每层的节点和,求其最大值.
 */
public class Solution {

    private static int solution(TreeNode root) {
        if (root == null) throw new IllegalStateException("树空异常");
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int currentLevelWidth = 1; //跟节点所在层宽度;
        int nextLevelWidth = 0; //下一层宽度;
        int max = 0, currentSum = 0;
        while (!queue.isEmpty()) {
            while (currentLevelWidth-- > 0) {
                TreeNode treeNode = queue.removeFirst();
                if (treeNode.getLeft() != null) {
                    nextLevelWidth++;
                    queue.addLast(treeNode.getLeft());
                }
                if (treeNode.getRight() != null) {
                    nextLevelWidth++;
                    queue.addLast(treeNode.getRight());
                }
                currentSum += treeNode.getValue();
            }
            currentLevelWidth = nextLevelWidth;
            nextLevelWidth = 0;
            if (max < currentSum) max = currentSum;
            currentSum = 0;
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(355);
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
        System.out.println(solution(root));

    }

}
