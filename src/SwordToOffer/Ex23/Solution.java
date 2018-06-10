package SwordToOffer.Ex23;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        //使用空间来简化这个题目.
//        HashMap<RandomListNode, RandomListNode> help = new HashMap<>();

        ArrayList<RandomListNode> help = new ArrayList<>();
        ArrayList<RandomListNode> help_2 = new ArrayList<>();
        RandomListNode current = pHead;
        while (current != null) {
            help.add(current.random);
            current = current.next;
        }
        current = pHead;
        while (current != null) {
            help_2.add(current);
            current = current.next;
        }
        //获得了每个节点的random指针指向谁.
        LinkedList<RandomListNode> assist = new LinkedList<>();
        for (RandomListNode node : help_2) {
            RandomListNode copy = new RandomListNode(node.label);
            assist.add(copy);
        }
        for (int i = 0; i < assist.size() - 1; i++) {
            assist.get(i).next = assist.get(i + 1);
            assist.get(i).random = help.get(i)==null?null:assist.get(help_2.indexOf(help.get(i)));
        } //设置next指针.

        return assist.get(0);
    }

    private static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}