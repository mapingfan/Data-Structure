package LinkedListProblem.MiddleNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 求解一个链表的中间节点.
 * <p>
 * 两个思路:扫描一遍,得到链表的长度n,然后第二轮直接扫描n/2次.得解.
 * <p>
 * 思路二:散列表解决,表中每项是<表中节点下标,节点的的引用> 这样扫描一遍就能取得值.
 */
public class Solution {

    private static Node findMiddleNode(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        current = head;
        Node pre = head;
        for (int i = 0; i < count / 2; i++) {
            pre = current;
            current = current.next;
        }
        //奇数个,偶数个修复下中间点.
        if (count % 2 == 0)
            return pre;
        else
            return current;
    }

    private static Node findMiddleV2(Node head) {
        Map<Integer, Node> map = new HashMap<>();
        Integer index = 1;
        while (head != null) {
            map.put(index++, head);
            head = head.next;
        }
        Node node = map.get(map.size() % 2 == 0 ? map.size() / 2 : map.size() / 2 + 1); //奇数个,偶数个修复下中间点.
        return node;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);


        Node headA = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        System.out.println(findMiddleNode(headA).value);

        System.out.println(findMiddleV2(headA).value);


    }
}

class Node {
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }
}
