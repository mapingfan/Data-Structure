package LinkedListProblem.JosephsCircle;


import Heap.Heap;

/**
 * 约瑟夫环问题,n个人围成圈,数数字,数到m退出圈.从1开始继续数.问最后留在圈里是几号.
 * <p>
 * 循环链表模拟这个数据结构.
 */
public class Solution {
    //循环链表,每隔m个节点,删除一个节点,直到最后剩余一个节点.
    private static Node solution(Node head, int n, int m) {
        if (m == 1) {
            //直接找到最后一个节点.
            for (int i = 0; i < n - 1; i++) {
                head = head.next;
            }
            return head;
        }
        if (head == null) {
            return null;
        }
        Node current = head;
        Node pre = current;
        int count = 1;
        while (current.next != current) { //只有一个节点.
            if (count == m) {
                //删除节点,并且置为1,重新开始计数.
                Node tmp = current.next;
                pre.next = tmp;
                count = 0;
            }
            count++;
            pre = current;
            current = current.next;
        }
        return current;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        Node headA = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node2.next = node3;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = headA;

        Node result = solution(headA, 7, 7);
        System.out.println(result.value);
    }

    private static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
