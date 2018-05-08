package ExDay3;

/**
 * 求完全二叉树节点个数
 * 方法太多,这个地方介绍一直比较新的方法.
 * 原理:一棵完全二叉树可以拆成一个子满树+一棵子完全树.
 * 从而达到问题规模缩小,递归求解子树.满树直接根据高度求即可.
 */
public class Ex07 {
    public static int countNodeOfCBT(Node root) {
        if (root == null) return 0;
        int left = leftDepthOfSubtreeToMostLeftNode(root.left);
        int right = rightDepthOfSubtreeToMostLeftNode(root.right);
        if (left == right) {
            return (1 << left) + countNodeOfCBT(root.right);
        } else {
            return (1 << right) + countNodeOfCBT(root.left);
        }
    }

    private static int rightDepthOfSubtreeToMostLeftNode(Node root) {
        return leftDepthOfSubtreeToMostLeftNode(root);
    }

    private static int leftDepthOfSubtreeToMostLeftNode(Node root) {
        int cnt = 0;
        while (root != null) {
            cnt++;
            root = root.left;
        }
        return cnt;
    }

    public static int comparator(Node root) {
        if (root == null) return 0;
        return 1 + comparator(root.left) + comparator(root.right);
    }

    public static void main(String[] args) {

        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
//        head.right.left = new Node(55);
        System.out.println(comparator(head));
        System.out.println(countNodeOfCBT(head));
    }

}
