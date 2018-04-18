package LinkedListProblem.PrintList;

//倒序输出链表.
public class Solution {


    private static void print(Node head) {
        if (head == null) {
            return;
        }
        print(head.next);
        System.out.println(head.value);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);


        Node headA = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        print(headA);
    }
}

class Node {
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }
}
