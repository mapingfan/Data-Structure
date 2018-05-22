package ExDay11;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 不知道这是第几次写二叉树遍历.但是没办法,孰能生巧.
 */
public class Ex02 {

    public static void pre_rec(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            pre_rec(root.left);
            pre_rec(root.right);
        }
    }

    public static void pre_stack(Node root) {
        if (root == null) return;
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            System.out.print(tmp.value + " ");
            //对于每个节点,先访问跟节点,然后右孩子先进去,左孩子后进.
            //这样出来的时候就是先访问左孩子.
            if (tmp.right != null) stack.push(tmp.right);
            if (tmp.left != null) stack.push(tmp.left);
        }
    }

    public static void in_rec(Node root) {
        if (root != null) {
            in_rec(root.left);
            System.out.print(root.value + " ");
            in_rec(root.right);
        }
    }

    public static void in_stack(Node root) {
        if (root == null) return;
        LinkedList<Node> stack = new LinkedList<>();
        //下面的方法有点变扭,我就想先把跟节点入栈.
        //为何要加上一个root!=null呢?好变扭,不加又不行.
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left; //一路向着左边走.
            }
            if (!stack.isEmpty()) {
                root = stack.pop(); //回退到上一个访问的节点.因为root=null,表明root没有做左孩子了,访问root,然后找root的兄弟了.
                System.out.print(root.value + " "); //最后一次访问的是null.回退到上一个节点.
                root = root.right;
            }
        }
    }


    public static void post_rec(Node root) {
        if (root != null) {
            post_rec(root.left);
            post_rec(root.right);
            System.out.print(root.value + " ");
        }
    }

    /**
     * 后序的非递归版本比较麻烦.
     * 但是可以借助前序改一个出来.
     * 前序中,对于每个节点,我们的处理策略是输出自身,右孩子先进去,左孩子进去.这样可以保证下次一定先访问这个左孩子.
     * <p>
     * 但是,如果我们先进去左孩子,然后右孩子.那么得到的序列就是 根右左 -> 如果用栈逆置下 -> 左右根.
     * 完美解决.
     */
    public static void post_stack(Node root) {
        if (root == null) return;
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Node> assistStack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            assistStack.push(root); //现在栈里面的序列是根右左.
            if (root.left != null) stack.push(root.left);
            if (root.right != null) stack.push(root.right);
        }
        //最后逆转栈.
        while (!assistStack.isEmpty()) {
            System.out.print(assistStack.removeFirst().value + " ");
        }
        System.out.println();
    }

    public static void level_stack(Node root) {
        if (root != null) {
            LinkedList<Node> queue = new LinkedList<>();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                Node tmp = queue.removeFirst();
                System.out.print(tmp.value + " ");
                if (tmp.left != null) queue.addLast(tmp.left);
                if (tmp.right != null) queue.addLast(tmp.right);
            }
        }
    }

    //1.如果当前节点没有左孩子,那么移动到右孩子上.
    //2.如果有左孩子,那么找到左子树的最右节点.
    //  如果最右孩子的右指针指向null,那么改为指向当前节点,
    //  如果最右孩子的右指针不指向空(已经指向当前节点),那么改为指向null.
    public static void morris_pre(Node root) {
        if (root == null) return;
        Node current = root;
        Node mostRight;
        while (current != null) { //什么时候遍历结束呢?
            if (current.left == null) {
                System.out.print(current.value + " ");
                current = current.right;
            } else {
                mostRight = findMostRight(current.left, current); //获得左子树的最右节点.此时需要注意了,最右节点可能已经指向自己了.这样找下去会出现错误.
                if (mostRight.right == null) {
                    mostRight.right = current;
                    System.out.print(current.value + " ");
                    current = current.left;
                } else {
                    mostRight.right = null;
                    current = current.right;
                }
            }
        }
    }


    public static void morris_in(Node root) {
        if (root == null) return;
        Node current = root;
        Node mostRight;
        while (current != null) { //什么时候遍历结束呢?
            if (current.left == null) {
                System.out.print(current.value + " ");
                current = current.right;
            } else {
                mostRight = findMostRight(current.left, current); //获得左子树的最右节点.此时需要注意了,最右节点可能已经指向自己了.这样找下去会出现错误.
                if (mostRight.right == null) {
                    mostRight.right = current;
                    current = current.left;
                } else {
                    mostRight.right = null;
                    System.out.print(current.value + " ");
                    current = current.right;
                }
            }
        }
    }


    public static void morris_post(Node root) {
        if (root == null) return;
        Node current = root;
        Node mostRight;
        while (current != null) { //什么时候遍历结束呢?
            if (current.left == null) {
                current = current.right;
            } else {
                mostRight = findMostRight(current.left, current); //获得左子树的最右节点.此时需要注意了,最右节点可能已经指向自己了.这样找下去会出现错误.
                if (mostRight.right == null) {
                    mostRight.right = current;
                    current = current.left;
                } else {
                    mostRight.right = null;
                    //逆序打印当前节点左子树的右边界.
                    printEdge(current.left);
                    current = current.right;
                }
            }
        }
        printEdge(root);
        System.out.println();
    }

    private static void printEdge(Node current) {
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(current);
        while (current.right != null) {
            stack.push(current.right);
            current = current.right;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().value + " ");
        }
    }


    private static Node findMostRight(Node root, Node current) {
//        if (root == null) return null;
        //root不可能为null,因为调用者已经判断过了.
        while (root.right != null && root.right != current) //指回自己.
            root = root.right;
        return root;
    }


    public static void main(String[] args) {
        Node root;
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        root = n1;
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.right = n5;
     /*   System.out.println("-----Test Pre Order------");
        pre_rec(root);
        System.out.println();
        pre_stack(root);
        System.out.println();
        morris_pre(root);
        System.out.println("-----End------");*/

/*
        System.out.println("-----Test------");
        in_rec(root);
        System.out.println();
        in_stack(root);
        System.out.println();
        morris_in(root);
        System.out.println("-----End------");*/


        System.out.println("-----Test------");
        post_rec(root);
        System.out.println();
        post_stack(root);
        morris_post(root);
        System.out.println("-----End------");

        /*
        System.out.println("-----Test------");
        level_stack(root);
        System.out.println("-----End------");
        */
    }

    private static class Node {
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
