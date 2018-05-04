package ExDay2;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 反转双链表.
 */
public class ex11 {

    @Data
    private static class Node {
        Node pre;
        Node next;
        int value;
    }

    private static Node reverse(Node head) {
        if (head==null||head.next==null) return head;
        Node pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

}
