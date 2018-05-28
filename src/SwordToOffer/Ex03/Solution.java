package SwordToOffer.Ex03;

/**
 *    public class ListNode {
 *        int val;
 *        ListNode next = null;
 *
 *        ListNode(int val) {
 *            this.val = val;
 *        }
 *    }
 *
 */
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode==null) return null;
        ArrayList<Integer> help = new ArrayList<>();
        while (listNode != null) {
            help.add(0, listNode.val);
            listNode = listNode.next;
        }
        return help;
    }

    private class ListNode {
        int val;
        ListNode next;
    }
}