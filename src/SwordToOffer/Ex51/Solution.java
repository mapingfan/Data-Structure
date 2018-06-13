package SwordToOffer.Ex51;

import java.util.HashSet;

public class Solution {

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        HashSet<ListNode> filter = new HashSet<>();
//        ListNode pre = null;
        while (pHead != null) {
            if (!filter.contains(pHead)) {
                filter.add(pHead);
//                pre = pHead;
                pHead = pHead.next;
            } else {
                break;
            }
//            if (filter.contains(pHead)) break;
        }
        if (pHead == null) return null;
        return pHead;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}