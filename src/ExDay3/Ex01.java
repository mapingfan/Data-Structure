package ExDay3;

import java.util.LinkedList;

/**
 * 二叉树的四种遍历+非递归版本实现,一共七个方法.
 */
public class Ex01 {

    public static void levelOrder(Node root) {
        if (root == null) return;
        LinkedList<Node> helpQueue = new LinkedList<>();
        helpQueue.addLast(root);
        while (!helpQueue.isEmpty()) {
            Node node = helpQueue.removeFirst();
            System.out.println(node.value);
            if (node.left != null) helpQueue.addLast(node.left);
            if (node.right != null) helpQueue.addLast(node.right);
        }
    }


    public static void preOrder_rec(Node root) {
        if (root == null) return;
        System.out.println(root.value);
        preOrder_rec(root.left);
        preOrder_rec(root.right);
    }

    /**
     * 这个是我的思路.
     * 下面还有一个是以前写的版本
     */
    public static void preOrder_stack(Node root) {
        if (root == null) return;
        LinkedList<Node> helpStack = new LinkedList<>();
//        helpStack.addFirst(root);
        while (!helpStack.isEmpty() || root != null) {
            while (root != null) {
                System.out.print(root.value + " ");
                helpStack.addFirst(root);
                root = root.left;
            }
            if (!helpStack.isEmpty()) {
                root = helpStack.removeFirst();
                root = root.right;
            }
        }
    }

    //以前写的版本
    private static void pre_traverse(Node root) {
        if (root == null) {
            return;
        } else {
            LinkedList<Node> stack = new LinkedList<>();
            while (true) {
                while (root != null) {
                    System.out.print(root.value + " ");
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

    /**
     * 这个版本是左神的思路.的确更加容易理解.
     * 先根节点入栈,然后右孩子入栈,左孩子入栈.
     * 这样访问时,考虑栈的性质,总是会访问左孩子,然后右孩子.
     * 这个思路写出的代码更加优美,佩服佩服.
     */

    public static void preOrder_stack_2(Node root) {
        if (root == null) return;
        LinkedList<Node> helpStack = new LinkedList<>();
        helpStack.addFirst(root);
        while (!helpStack.isEmpty()) {
            Node node = helpStack.removeFirst();
            System.out.print(node.value + " ");
            if (node.right != null) helpStack.addFirst(node.right);
            if (node.left != null) helpStack.addFirst(node.left);
        }
    }

    //--------------------------------------------------------------

    /**
     * 中序,左根右.先一路向左,栈中要记录访问过的节点,用作回溯.
     * 走到头了不能走了,然后出栈,转向右边.
     * <p>
     * 下面给了好几个版本.专心实现下.
     */
    public static void inOrder_rec(Node root) {
        if (root == null) return;
        inOrder_rec(root.left);
        System.out.print(root.value + " ");
        inOrder_rec(root.right);
    }

    public static void inOrder_stack(Node root) {
        if (root == null) return;
        LinkedList<Node> helpStack = new LinkedList<>();
//        helpStack.push(root);
        while (!helpStack.isEmpty() || root != null) {
            while (root != null) {
                helpStack.push(root);
                root = root.left;
            }
            root = helpStack.pop();
            System.out.print(root.value + " ");
            root = root.right;
        }
    }

    public static void inOrder_stack_2(Node root) {
        if (root == null) return;
        LinkedList<Node> helpStack = new LinkedList<>();
//        helpStack.push(root);
        while (!helpStack.isEmpty() || root != null) {
            if (root != null) {
                helpStack.push(root);
                root = root.left;
            } else {
                root = helpStack.pop();
                System.out.print(root.value + " ");
                root = root.right;
            }
        }
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

    //--------------------------------------------------------------


    public static void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }

    /**
     * 前序遍历的最后一种方法中,可以知道,
     * 我们的策略用一个栈,先加根节点,然后右节点,而后是左节点.
     * 这样输出的是根左右序列.
     * 如果我们先加左,然后加右.
     * 这样输出的是根右左. --> 根右左 到过来不就是左右根嘛?这不就是后序序列嘛.
     */
    public static void postOrder_2(Node root) {
        //这个地方用一种特别简单的方法.双栈解决.
        if (root == null) return;
        LinkedList<Node> helpStackA = new LinkedList<>();
        helpStackA.push(root);
        LinkedList<Node> helpStackB = new LinkedList<>();
        while (!helpStackA.isEmpty()) {
            root = helpStackA.pop();
            helpStackB.push(root);
            if (root.left != null) helpStackA.push(root.left);
            if (root.right != null) helpStackA.push(root.right);
        }
        for (Node node : helpStackB) {
            System.out.print(node.value + " ");
        }
    }

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

    private static class NewNode {
        NewNode left;
        NewNode right;
        int value;
        boolean isFirstVisited = true;
    }

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


    //----------------------------------------------------------------

    public static void main(String[] args) {
        Node root1, root2;
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);
        root1 = head;
        root2 = head;
//        levelOrder(head);
//        pre_traverse(root1);
//        System.out.println();
//        preOrder_stack(root2);
//        System.out.println();
//        preOrder_stack_2(head);

//        inOrder_rec(root1);
//        System.out.println();
//        inOrder_stack(head);
//        System.out.println();
//        inOrder_stack_2(root2);

        postOrder(head);
        System.out.println();
        System.out.println();
        postOrder_2(root2);
    }

}
