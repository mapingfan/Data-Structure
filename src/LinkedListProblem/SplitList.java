package LinkedListProblem;

import java.util.HashMap;

/**
 * 将一个循环链表(单向)切割成为两个长度相等的链表.如果奇数个节点,则前面的子链表长度加一.
 * 分析:
 * 首先要知道这个循环链表的长度,然后就好办了.
 * 这个地方用空间换时间,用散列表来判断重合的第一个节点.
 *
 * 简单实现很简单,但是考虑到各种边界值,那就有点麻烦了.
 *
 * 时间复杂度O(n),空间复杂度O(n)
 */
public class SplitList {

    private static Pair<Node> solution(Node head) {
        HashMap<Integer, Node> map = new HashMap<>();
        Node current = head;
        Node tail = current;
        Integer index = 1;
        while (current != null) {
            if (map.containsValue(current)) {
                break;
            }
            map.put(index++, current);
            tail = current; //存储最后一个节点.
            current = current.next;
        }
        int lenOfList = map.size();
        if (lenOfList == 1) {
            return new Pair<>(head, null);
        }
        if (lenOfList == 0) {
            return new Pair<>(null, null);
        }
        Node midNode = map.get(lenOfList / 2);
        Node firstListEndNode = null;
        Node secondListBeginNode = null;

        if (lenOfList % 2 == 0) {
            firstListEndNode = midNode;
        } else {
            firstListEndNode = midNode.next;
        }
        secondListBeginNode = firstListEndNode.next;
        firstListEndNode.next = head;
        tail.next = secondListBeginNode;
        return new Pair<>(head, secondListBeginNode);
    }

    private static void print(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        Node current = head.next; //第二个节点.
        while (current != null && current != head) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node head = node1;
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        node1.next = null;
//        node1.next = node2;
//        node2.next = null;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = null;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = node8;
//        node8.next = null;
        Pair<Node> result = solution(head);
        Node nodeA = result.nodeA;
        Node nodeB = result.nodeB;
        if (nodeA != null) {
            print(nodeA);
        }
        System.out.println();
        if (nodeB != null) {
            print(nodeB);
        }


    }

    static class Pair<T> {
        T nodeA;
        T nodeB;

        public Pair(T nodeA, T ndeB) {
            this.nodeA = nodeA;
            this.nodeB = ndeB;
        }
    }

    static class Node {
        Node next;
        int value;

        public Node(int i) {
            value = i;
        }
    }
}
