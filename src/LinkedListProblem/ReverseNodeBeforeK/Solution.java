package LinkedListProblem.ReverseNodeBeforeK;

/**
 * 逆转链表中的前k个节点.
 * 其实最初的版本就是逆转整个链表,现在就是控制下逆转的范围.
 * 对于这种带范围的逆转,似乎也可以用递归实现.
 * <p>
 * 逆转一个链表的中的前k个节点=逆转后+后面没逆转的.
 */
public class Solution {

    private static Node reverseK(Node head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 1 || k == 0) {
            return head;
        } else {
            Node result = reverseK(head.next, k - 1);
            result.next = head;
            head.next = null;
            return head;
        }
    }


    private static Pair<Node> findK(Node head,int k) {
        for (int i = 1; i <= k-1; i++) {
            head = head.next;
        }
        return new Pair<>(head, head.next);
    }

    private static Node reverse_final(Node head, int k) {
        Pair<Node> findResult = findK(head, k);
        Node result = reverseK(head, k);
        result.next = findResult.nodeB;
        return findResult.nodeA;
    }

    static class Pair<T> {
        T nodeA;
        T nodeB;

        public Pair(T head, T next) {
            nodeA = head;
            nodeB = next;
        }
    }


    private static void print(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        Node headA = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;
        print(headA);
        Node result = reverse_final(headA, 5);
        print(result);


    }

    private static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
