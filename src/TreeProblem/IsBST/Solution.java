package TreeProblem.IsBST;

import TreeProblem.TreeNode;

import java.util.Arrays;

import static com.sun.javafx.scene.traversal.Hueristic2D.findMin;

/**
 * 判断一棵树是否是二叉搜索树:
 * 分析下:
 * 对于这种判定类题目,一般都需要根据相应的特征来进行判断.
 * 思路一: 对于每个节点,判断是否左孩子小于他,右孩子大于他.
 * 这个思路正确嘛?乍一看是正确的.但是违背了二叉树的定义.
 * 二叉搜索树要求,根节点大于左子树的任意值,小于左子树的任意值.
 * 意思就是左子树的最大值要小于根节点,右子树最小值要大于根节点.
 * 这个思路才是正确的.
 * 下面代码实现.
 *
 * 这个地方的递归实现还是很有启发意义的,原因在于他不同于普通的实现方法,我至少学了两点:
 * 1.对于特殊值的处理.比如下面递归查找最小值的函数,一开始对于root==null的时候,我有点糊涂,
 * 默认让他返回0,那么最后可能就真的返回了0,其实正确的做法应该事返回整数的最大值.
 * 当节点为null的时候,返回的值其实是没意义的,那么返回最大反而是最合理的.
 *
 * 2.对于判断是否是BST的递归,正常思路是:
 *      判断左子树是不是,判断右子树是不是,但是如何找递归基准条件?也就是递归出口?
 *      四种情况:
 *      1.左空,右不空  ,并且你要确保右子树只有一个节点.
 *      2.右空,左不空  ,同上.
 *      3.左右都不空.  ,保证只有三个节点.
 *      4.左右都空     ,直接返回true.
 *      这样判断条件要写一大推.
 *      上次我也遇到这种情况,写了一堆,又臭又长.
 *      这次的代码给了我提示,从反面寻找递归出口.
 *      就拿这个情况来说:
 *      如果左子树不为空,那么确保左子树的最大值小于根值
 *      如果右子树不为空,那么确保右子树的最大值大于根值.
 *      如果写成这样?会有什么问题?
 *      很有可能第一次判断的时候就满足条件,这样得出错误的结果.
 *      也就是说这不是合理的出口.为什么?
 *
 *      正确的思路是找反面条件,
 *      即如果左树不空,那么如果左的最大值大于根植,一定不是.
 *      即如果右树不空,那么如果右的最小值小于根植,一定不是.
 *      这是对根的判断.
 *      下面递归判断左右子树.
 *
 *      三步走战略:
 *      检查根是否满足,检查左子树,检查右子树.
 *      通过这些检查,那么就是二叉搜索树.
 *
 *      废话唠叨写了一堆,多多感悟.
 *
 *
 *
 * 我是这样写的:
 *      1.树空,返回true
 *      2.
 *
 *
 *
 */
public class Solution {

    private static boolean isBST(TreeNode root) {
        if (root == null) return true;
        if (root.getLeft() != null && findMax(root.getLeft()) > root.getValue()) {
            return false;
        }
        if ((root.getRight() != null) && findMin(root.getRight()) < root.getValue()) {
            return false;
        }
        if (!isBST(root.getLeft()) || !isBST(root.getRight())) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(9);
        TreeNode root = node1;
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        System.out.println(isBST(root));
    }

    private static int findMin(TreeNode right) {
        //right不会为空.
        if (right == null) return Integer.MAX_VALUE;
        int leftMin = findMin(right.getLeft());
        int rightMin = findMin(right.getRight());
        return min(leftMin, rightMin, right.getValue());
    }

    private static int min(int leftMin, int rightMin, int value) {
        int[] array = {leftMin, rightMin, value};
        Arrays.sort(array);
        return array[0];
    }

    private static int findMax(TreeNode left) {
        if (left == null) return Integer.MIN_VALUE;
        int leftMax = findMax(left.getLeft());
        int rightMax = findMax(left.getRight());
        return max(leftMax, rightMax, left.getValue());
    }

    private static int max(int leftMax, int rightMax, int value) {
        int[] array = {leftMax, rightMax, value};
        Arrays.sort(array);
        return array[array.length - 1];
    }
}
