package SwordToOffer.Ex34;

import java.util.HashSet;

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        HashSet<ListNode> assist = new HashSet<>();
        while (pHead1 != null) {
            assist.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            if (assist.contains(pHead2)) {
                return pHead2;
            } else {
                assist.add(pHead2);
                pHead2 = pHead2.next;
            }
        }
        return null;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}