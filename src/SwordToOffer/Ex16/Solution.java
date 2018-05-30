package SwordToOffer.Ex16;

/**
 * 合并单链表.
 */
public class Solution {
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode newHead = list1.val > list2.val ? list2 : list1;
        ListNode tail = newHead; //设置尾节点,用于插入.
        if (newHead == list1) {
            list1 = list1.next; //list1从下一个开始计算.
        } else {
            list2 = list2.next;
        }
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            } else {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            }
        }
        if (list1 == null) tail.next = list2;
        else tail.next = list1;
        return newHead;
    }

    private class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}