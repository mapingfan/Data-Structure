package LinkedListProblem.ReverseSLL;


//逆转单链表.
public class Solution {

    private static void reversePrint(Node head) {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            System.out.println(head.value);
            return;
        }
        reversePrint(head.next);
        System.out.println(head.value);
    }

    private static Node reverse(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node result = reverse(head.next);
        result.next = head;
        head.next = null;
        return head;
    }

    /**
     * 逆转一个节点,需要知道当前节点的前后信息.
     * 如果不保存后面的信息,那么下面的逆转无法进行.
     * 如果不知道前前一个的信息,那么无法逆转.
     */
    private static Node reverseV2(Node head) {
        Node current = head, pre = null, next = null;
        while (current != null) {
            next = current.next; //先把后面的链条保存起来.
            current.next = pre;
            //逆转结束,current节点成为了pre节点.
            pre = current;
            if (next == null) {
                break;
            } else {
                current = next;//current节点要移动到下一个节点.
            }
        }
        return current;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node head = node1;
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        reverseV2(head);
    }


}

class Node {
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }

}