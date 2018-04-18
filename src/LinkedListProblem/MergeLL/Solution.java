package LinkedListProblem.MergeLL;

/**
 * 合并两个有序的单链表.
 */
public class Solution {

    private static Node rec_merge(Node headA, Node headB) {
        if (headA == null) {
            return headB;
        }
        if (headB == null) {
            return headA;
        }
        Node result = null;
        if (headA.value <= headB.value) {
            result = headA;
            rec_merge(headA.next, headB);
        } else {
            result = headB;
            rec_merge(headA, headB.next);
        }
        return result;
    }

    private static Node merge(Node headA, Node headB) {
        Node ptrA = headA;
        Node ptrB = headB;
        Node result = new Node(0);
        while (ptrA != null && ptrB != null) {
            if (ptrA.value <= ptrB.value) {
                result.next = ptrA;
                result = ptrA;
                ptrA = ptrA.next;

            } else {
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
        return result;
    }


}


class Node {
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }
}
