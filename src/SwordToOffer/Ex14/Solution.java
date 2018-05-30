package SwordToOffer.Ex14;

import java.util.ArrayList;
public class Solution {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (k <= 0 || head == null) return null;
        ArrayList<ListNode> help = new ArrayList<>();
        while (head != null) {
            help.add(head);
            head = head.next;
        }
        int len = help.size();
        if (k > len) return null; //不存在.
        return help.get(len - k);
    }

    private class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}