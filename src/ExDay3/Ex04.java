package ExDay3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 二叉树进行序列化和反序列化
 * 空的加上#! ,否则是其值加上!. 以!结尾.
 */
public class Ex04 {

    /**
     * 序列化一颗二叉树=序列化跟节点+序列化左子树+序列化右子树.
     * 对于null节点,也保存,记号为#_.
     */
    public static String serialByPre(Node root) {
        if (root == null) return "#_";
        return root.value + "_" + serialByPre(root.left) + serialByPre(root.right);
    }

    /**
     * 根据给定序列给定出树
     */
    public static Node reconstructByPre(String preSequence) {
        //这个地方要做一个初步处理过程,把
        if (preSequence == null) return null;
        String[] strings = preSequence.split("_");
        //把这些序列放到一个容易中,这个容易可以很方便的删除元素,之所以不用string,用队列就是
        //因为队列可以很方便的在每次循环时删除一个元素.
        LinkedList<String> helpQueue = new LinkedList<>();
        for (String string : strings) {
            helpQueue.offer(string);
        }
        return buildTreeWithPreOrderSequence(helpQueue);
    }

    //队列里面装的是前序遍历的结果
    private static Node buildTreeWithPreOrderSequence(LinkedList<String> helpQueue) {
        Node node = generateNode(helpQueue.pop());
        if (node != null) {
            node.left = buildTreeWithPreOrderSequence(helpQueue);
            node.right = buildTreeWithPreOrderSequence(helpQueue);
        }
        return node;
    }

    private static Node generateNode(String pop) {
        if (pop.equals("#")) return null;
        return new Node(Integer.parseInt(pop));
    }

   /* public static Node reconstructByIn(String inSequence) {
        if (inSequence==null) return null;
        //把序列放入队列中.
        LinkedList<String> helpQueue = new LinkedList<>();
        String[] strings = inSequence.split("_");
        for (String string : strings) {
            helpQueue.offer(string);
        }

        return buildTreeWithInOrderSequence(helpQueue);
    }

    private static Node buildTreeWithInOrderSequence(LinkedList<String> helpQueue) {
        //此处不用检查序列是否为空.因为上面调用的函数已经检查过.


    }*/

    /*public static Node reconstructByPost(String postSequence) {

    }*/


    public static String serialByIn(Node root) {
        if (root == null) return "#_";
        return serialByIn(root.left) + root.value + "_" + serialByIn(root.right);
    }

    public static String serialByPost(Node root) {
        if (root == null) return "#_";
        return root.value + "_" + serialByPost(root.left) + serialByPost(root.right);
    }

    public static String serialByLevel(Node root) {
        if (root == null) return "#_";
        String sequence = "";
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            if (node != null) {
//                helpQueue.offer(node.value + "_");
                sequence += node.value + "_";
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
//                helpQueue.offer("#_");
                sequence += "#_";
            }
        }
        return sequence;
    }


    public static Node reconstructByLevel(String sequence) {
        if (sequence == null) return null;
        String[] strings = sequence.split("_");
        int index = 0;
        Node head = generateNode(strings[index++]);
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            node.left = generateNode(strings[index++]);
            node.right = generateNode(strings[index++]);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return head;
    }


    public static void main(String[] args) {
        Node root, node1 = new Node(1);
        root = node1;
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.left = node2;
        node2.left = node3;
        node1.right = node4;
        node4.right = node5;
        node5.right = node6;

        System.out.println(serialByPre(root));
        System.out.println(serialByIn(root));
        System.out.println(serialByPost(root));
        System.out.println(serialByLevel(root));

    }

}
