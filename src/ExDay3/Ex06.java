package ExDay3;

import java.util.LinkedList;

/**
 * 判读一颗树是否是二叉搜索树
 * 中序遍历有序一定是.左边小于根,右边大于跟.
 */
public class Ex06 {
    //思路一: 根据中序遍历,非递归版本进行修改.
    private static boolean isBST(Node root) {
        if (root == null) return true;
        int pre = Integer.MIN_VALUE;
        LinkedList<Node> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left; //一路向左.并把路过的点入栈,用于回溯.
            }
            root = stack.pop();
            if (root.value < pre) return false;
            else pre = root.value;
            root = root.right; //换到左边.
        }
        return true;
    }

    //思路二,层次遍历的思想.对于每个节点,如果左子树的最大值小于它,右子树的最小值大于它.
    //那么就是BST.否则不是.
    private static boolean isBST_2(Node root) {
        if (root == null) return true;
        LinkedList<Node> assistQueue = new LinkedList<>();
        assistQueue.offer(root);
        while (!assistQueue.isEmpty()) {
            Node node = assistQueue.removeFirst();
            int leftValue = Integer.MIN_VALUE, rightValue = Integer.MAX_VALUE;
            if (node.left != null) {
                assistQueue.offer(node.left);
                leftValue = getLeftSubtreeMax(node.left);
            }
            if (node.right != null) {
                assistQueue.offer(node.right);
                rightValue = getRightSubtreeMin(node.right);
            }
            if (node.value < leftValue || node.value > rightValue) return false;
        }
        return true;
    }

    private static int getRightSubtreeMin(Node root) {
        if (root == null) return Integer.MAX_VALUE;
        int min = Math.min(getRightSubtreeMin(root.left), getRightSubtreeMin(root.right));
        return min < root.value ? min : root.value;

    }

    private static int getLeftSubtreeMax(Node root) {
        if (root == null) return Integer.MIN_VALUE;
        int max = Math.max(getLeftSubtreeMax(root.left), getLeftSubtreeMax(root.right));
        return max > root.value ? max : root.value;
    }
    //判断一颗树是否是完全树.完全的特点.

    /**
     * 对于任意一个节点,如果存在右孩子,没有左孩子,那么一定不是完全二叉树.(从左往右堆满)
     * 如果有左孩子,右孩子没有 - > 那么该节点后续都为叶子节点.
     * 如果该节点左右孩子都没有 -> 那么其后续节点都是叶子节点.
     * 对于左右孩子都存在,直接下一次循环.
     * <p>
     * 使用层次遍历的方式:
     * 对于当前节点,判断左后孩子.
     * <p>
     * 如何判断一个节点是叶子节点 -> 左右孩子都为空.
     */
    private static boolean istCBT(Node root) {
        if (root == null) return true;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leafMode = false;
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            Node lchild = node.left, rchild = node.right;
            if (leafMode && (lchild != null || rchild != null)) return false;
            if (rchild != null && lchild == null) return false;
            else if (lchild != null && rchild == null) {
                //要求后继节点都是叶子节点.
                leafMode = true;
            } else if (lchild == null && rchild == null) {
                //也要求后继节点为空.
                leafMode = true;
            } else ; //do nothing;
            if (lchild != null) queue.offer(lchild);
            if (rchild != null) queue.offer(rchild);
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(55);
        System.out.println(isBST(head));
        System.out.println(isBST_2(head));
        System.out.println(istCBT(head));

    }

}
