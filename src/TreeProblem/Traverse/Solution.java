package TreeProblem.Traverse;

import java.util.LinkedList;

/**
 * 二叉树的三种非递归遍历实现.借助于栈.
 * <p>
 * 前序非递归:先跟,后左后右.分析下栈中需要保存什么信息.
 * <p>
 * 前序会一直找树的左子节点,然后右子节点,需要保存根节点,不然不法定位右节点.
 */
public class Solution {

    /**
     * 后序非递归遍历.这个有点饶,我自己没想出办法,因为自己对树的问题还是不够清楚,很多
     * 问题无法有很好的想法.网上有了大把例子,找了个比较好的思路看了下,下面简单实现下.
     * <p>
     * 对一个树,向下一直将左子节点都入栈.最后一定是没有左子节点的情况停下来.
     * <p>
     * 那么此时可以访问最后一个左子节点了吗?
     * 问题就出现在这里,不一定啊,因为这个节点可能还有右孩子,这个时候就不能直接访问.
     * <p>
     * 后来看别人的分析,每个节点需要访问两次,第二次才出栈.
     * 所以好的做法是修改节点类定义,添加一个标志位,表示第几次访问.
     * <p>
     * <p>
     * 第一种思路：对于任一结点P，将其入栈，然后沿其左子树一直往下搜索，
     * 直到搜索到没有左孩子的结点，此时该结点出现在栈顶，但是此时不能将其出栈并访问，
     * 因此其右孩子还为被访问。所以接下来按照相同的规则对其右子树进行相同的处理，
     * 当访问完其右孩子时，该结点又出现在栈顶，此时可以将其出栈并访问。
     * 这样就保证了正确的访问顺序。可以看出，
     * 在这个过程中，每个结点都两次出现在栈顶，只有在第二次出现在栈顶时，
     * 才能访问它。因此需要多设置一个变量标识该结点是否是第一次出现在栈顶。
     */
    private static void post_traverse(NewNode root) {
        LinkedList<NewNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                return;
            } else {
                NewNode first = stack.removeFirst();
                if (first.isFirstVisited) {
                    first.isFirstVisited = false;
                    stack.addFirst(first);
                    root = first.right;
                } else {
                    System.out.println(first.value);
                }
            }
        }

    }

    /**
     * 下面补充第二种后续遍历的非递归思路
     * <p>
     * 第二种思路：要保证根结点在左孩子和右孩子访问之后才能访问，
     * 因此对于任一结点P，先将其入栈。如果P不存在左孩子和右孩子，
     * 则可以直接访问它；或者P存在左孩子或者右孩子，
     * 但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
     * 若非上述两种情况，则将P的右孩子和左孩子依次入栈，
     * 这样就保证了每次取栈顶元素的时候，左孩子在右孩子前面被访问，
     * 左孩子和右孩子都在根结点前面被访问。
     */

    private static void post_traverse_2(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node pre = root;
        Node current = root;
        stack.addFirst(current);
        while (!stack.isEmpty()) {
            if ((current.left == null && current.right == null) ||
                    (pre != null && (pre == current.left || pre == current.right))) {
                System.out.println(current.value);
                pre = current;
                stack.removeFirst();
            } else {
                if (current.right != null) {
                    stack.addFirst(current.right);
                }
                if (current.left != null) {
                    stack.addFirst(current.left);
                }
            }
        }

    }


    private static class NewNode {
        NewNode left;
        NewNode right;
        int value;
        boolean isFirstVisited = true;
    }


    private static void in_traverse(Node root) {
        if (root == null) {
            return;
        } else {
            LinkedList<Node> stack = new LinkedList<>();
            while (true) {
                while (root != null) {
                    stack.addFirst(root);
                    root = root.left;
                }
                if (stack.isEmpty()) {
                    break;
                } else {
                    Node node = stack.removeFirst();
                    System.out.println(node.value);
                    root = node.right;
                }
            }
        }
    }

    private static void pre_traverse(Node root) {
        if (root == null) {
            return;
        } else {
            LinkedList<Node> stack = new LinkedList<>();
            while (true) {
                while (root != null) {
                    System.out.println(root.value);
                    stack.addFirst(root);
                    root = root.left;
                }
                if (stack.isEmpty()) {
                    break;
                } else {
                    Node node = stack.removeFirst();
                    root = node.right;
                }
            }
        }
    }
}


class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

