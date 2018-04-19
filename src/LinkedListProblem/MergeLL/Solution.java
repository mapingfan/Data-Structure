package LinkedListProblem.MergeLL;

/**
 * 合并两个有序的单链表.
 * 两个思路:递归+常规的双指针.
 *
 * 递归法:
 *      合并两个链表 = 两个链表找到一个头,然后把子链表和剩下的链表递归进行合并操作.
 *      边界条件:有一个为空了,直接返回另一个链表即可.
 * 常规指针法:
 *      两个指针比较,谁小把设先加进去结果链表,并且移动到下一个元素进行合并.
 *      最后的结果就是合并好的链表.
 *
 */
public class Solution {

    private static Node rec_merge(Node headA, Node headB) {
        if (headA == null) {
            return headB;
        }
        if (headB == null) {
            return headA;
        }
        Node result;
        if (headA.value <= headB.value) {
            result = headA;
            Node merge = rec_merge(headA.next, headB);
            result.next = merge;
        } else {
            result = headB;
            Node merge = rec_merge(headA, headB.next);
            result.next = merge;
        }
        return result;
    }

    private static Node merge(Node headA, Node headB) {
        Node ptrA = headA;
        Node ptrB = headB;
        Node result, newHead;
        if (ptrA.value <= ptrB.value) {
            result = ptrA;
            ptrA = ptrA.next;
        } else {
            result = ptrB;
            ptrB = ptrB.next;
        }
        newHead = result;
        while (ptrA != null && ptrB != null) {
            if (ptrA.value <= ptrB.value) {
                result.next = ptrA;
                result = ptrA;
                ptrA = ptrA.next;

            } else {
                result.next = ptrB;
                result = ptrB;
                ptrB = ptrB.next;
            }
        }
        if (ptrA == null) {
            if (result != null)
                result.next = ptrB;
        } else {
            if (result != null)
                result.next = ptrA;
        }
        return newHead;
    }

    private static void printList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        printList(head.next);
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node headA = node1;
        node1.next = null;
//        Node node2 = new Node(3);
//        Node node3 = new Node(5);
//        Node node4 = new Node(7);
//        Node node5 = new Node(9);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = null;


        Node node11 = new Node(2);
        Node headB = node11;
        Node node22 = new Node(4);
        Node node33 = new Node(6);
        Node node44 = new Node(8);
        Node node55 = new Node(10);
        node11.next = node22;
        node22.next = node33;
        node33.next = node44;
        node44.next = node55;
        node55.next = null;

        Node result = rec_merge(headA, headB);
        printList(result);

    }


}


class Node {
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }
}
