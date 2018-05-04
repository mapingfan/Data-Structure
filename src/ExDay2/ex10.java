package ExDay2;

/**
 * 反转单链表
 * <p>
 * 思路一: 双指针法.
 * 思路二: 递归,不过需要包装下,提前找下头指针
 */
public class ex10 {

    private static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    private static Node reverse(Node head) {
        if (head == null || head.next == null) return head;
        Node current = head, next, pre = null;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    private static Node findNewHead(Node head) {
        if (head == null) return head;
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    //递归反转单链表.这样反转后找不到头节点了,所以需要包装下.
    private static Node rec_reverse(Node head) {
        if (head == null || head.next == null) return head; //空或者只有一个节点,不用逆转,直接返回.
        Node tmp = rec_reverse(head.next);
        tmp.next = head;
        head.next = null;
        return head;
    }

    private static Node reverseV2(Node head) {
        Node newHead = findNewHead(head);
        rec_reverse(head);
        return newHead;
    }

    private static void printList(Node head) {
        if (head == null) return;
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        Node head, node1 = new Node(1);
        head = node1;
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        printList(head);

//        Node newHead = reverse(head);
        Node newHead = reverseV2(head);
        System.out.println();
        printList(newHead);


    }

}
