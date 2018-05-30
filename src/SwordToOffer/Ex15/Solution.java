package SwordToOffer.Ex15;

/**
 * 输入一个链表，反转链表后，输出链表的所有元素。
 */
public class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head==null) return null;
        ListNode pre = null, current = head;
        while (current != null) {
            ListNode tmp = current.next; //保存好下一个节点.
            current.next = pre;
            pre = current;
            current = tmp;
        }
        //最后pre是头节点.
        //递归打印.
        current = pre;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
        return pre;
    }



    private class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}