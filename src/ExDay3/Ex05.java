package ExDay3;

import java.util.LinkedList;

/**
 * 判断一棵树是否是平衡二叉树.
 * 平衡二叉树的定义如下:
 * 对于以任意一个节点为根的子树,左右子树高度差不能超过1.小于等于1,不能大于1.
 * 我的思路,检查每个节点,并且检查它的左右子树高度就好.
 * 有n个节点,获取它的左右子树高度.
 * 一个不满足就返回.
 * 分析后,我们需要一个函数求输的高度.
 * <p>
 * 然而还有更好的解决思路:
 * 递归解决:
 * 如果为空,那么平衡
 * 如果不为空
 * 左右子树右一个不平衡,那么就不平衡.
 * 两个都平衡,继续判断左右子树的高度差 ->得出结果.
 * 这个思路是很简单的,但是遇到一个问题.
 * 返回值怎么确定?有时候我们需要返回的是一个布尔值,有时候还需要高度.
 * 这个时候设计返回值就比较重要,参考左神的思路.
 */
public class Ex05 {

    //有了这个函数,扫描整棵树就好.
    public static boolean isBalancedTree(Node root) {
        if (root == null) return true;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            int leftHeight = treeHeight(node.left);
            int rightHeight = treeHeight(node.right);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return false;
            } else {
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return true;
    }

    private static int treeHeight(Node root) {
        if (root == null) return 0;
        return treeHeight(root.left) > treeHeight(root.right) ? treeHeight(root.left) + 1 : treeHeight(root.right) + 1;
    }

    private static class ReturnData {
        boolean isBalance;
        int height;

        public ReturnData(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static boolean isBalancedTreeWarpper(Node root) {
        return isBalancedTree_core(root).isBalance;
    }

    public static ReturnData isBalancedTree_core(Node root) {
        if (root == null) return new ReturnData(true, 0);
        ReturnData leftResult = isBalancedTree_core(root.left);
        ReturnData rightResult = isBalancedTree_core(root.right);
        if (!leftResult.isBalance || !rightResult.isBalance)
            return new ReturnData(false, Math.max(leftResult.height, rightResult.height) + 1);
        else return Math.abs(leftResult.height - rightResult.height) < 1 ?
                new ReturnData(true, Math.max(leftResult.height, rightResult.height) + 1) :
                new ReturnData(false, Math.max(leftResult.height, rightResult.height) + 1);
    }


    public static void main(String[] args) {

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);


//        Node root, node1 = new Node(1);
//        root = node1;
//        Node n2 = new Node(2);
//        Node n3 = new Node(3);
//        Node n4 = new Node(4);
//        Node n5 = new Node(5);
//        Node n6 = new Node(6);
//        root.left = n2;
//        root.right = n3;
//        n2.left = n4;
//        n4.left = n5;
//        n5.right = n6;

        System.out.println(isBalancedTree(head));
        System.out.println(isBalancedTreeWarpper(head));
    }
}
