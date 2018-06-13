package SwordToOffer.Ex52;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Solution {
    public ListNode deleteDuplication(ListNode pHead) {
        HashSet<Integer> help = new HashSet<>();
        HashSet<Integer> filter = new HashSet<>();
        ArrayList<ListNode> tmp = new ArrayList<>();
        while (pHead != null) {
            if (!help.contains(pHead.val)) {
                help.add(pHead.val);
            } else {
                filter.add(pHead.val);
            }
            tmp.add(pHead);
            pHead = pHead.next;
        }
        //filter里面包含重复元素的值.
        Iterator<ListNode> iterator = tmp.iterator();
        while (iterator.hasNext()) {
            ListNode node = iterator.next();
            if (filter.contains(node.val)) {
                iterator.remove();
            }
        }
        if (tmp == null || tmp.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < tmp.size() - 1; i++) {
                tmp.get(i).next = tmp.get(i + 1);
            }
            tmp.get(tmp.size() - 1).next = null;
        }
        return tmp.get(0);
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public void print(ListNode head) {
        if (head != null) {
            System.out.print(head.val + " ");
            print(head.next);
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode nod2 = new ListNode(2);
        ListNode nod3 = new ListNode(3);
        ListNode nod4 = new ListNode(3);
        ListNode nod5 = new ListNode(4);
        ListNode nod6 = new ListNode(5);
        ListNode nod7 = new ListNode(6);
        node.next = nod2;
        nod2.next = nod3;
        nod3.next = nod4;
        nod4.next = nod5;
        nod5.next = nod6;
        nod6.next = nod7;

        Solution s = new Solution();
        ListNode head = s.deleteDuplication(node);
        s.print(head);

    }
}