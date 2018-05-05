package ExDay2;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 打印两个有序链表的公共部分.
 */
public class ex14 {

    @Data
    private static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    //类似合并数组的思想.稍微改一下判断逻辑就好.
    private static void print(Node headA, Node headB) {
        if (headA == null || headA == null) return;
        Node ptrA = headA, ptrB = headB;
        while (ptrA != null && ptrB != null) {
            if (ptrA.value == ptrB.value) {
                System.out.print(ptrA.value + " ");
                ptrA = ptrA.next;
                ptrB = ptrB.next;
            } else if (ptrA.value < ptrB.value)
                ptrA = ptrA.next;
            else
                ptrB = ptrB.next;
        }
    }

    private static void printList(Node head) {
        if (head == null) return;
        System.out.print(head.value + " ");
        printList(head.next);
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        printList(node1);
        System.out.println();
        printList(node2);
        System.out.println();
        print(node1, node2);
    }
}
