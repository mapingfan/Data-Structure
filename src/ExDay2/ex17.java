package ExDay2;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制链表.
 * 链表节点比较特殊:有两个指针域.一个是next指针,另一个是随机指针.
 * 现在要求复制这个链表.
 * <p>
 * 思路一: HASH表,空间换时间.把原先的链表的每个节点复制一份.先不考虑random指针,只考虑next.
 */
public class ex17 {

    /**
     * 这个方式实现需要利用哈希表.下面看下O(1)时间复杂度的做法.
     *
     * @param head
     * @return
     */
    private static Node copy(Node head) {
        if (head == null) return head;
        Map<Node, Node> nodeMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            nodeMap.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        //现在开始重设链表.
        cur = head;
        while (cur != null) {
            Node random = cur.random;
            //确保map中不会get(null)发生.
            nodeMap.get(cur).random = random == null ? null : nodeMap.get(random);
            nodeMap.get(cur).next = cur.next != null ? nodeMap.get(cur.next) : null;
            cur = cur.next;
        }
        return nodeMap.get(head);
    }

    //O(1)做法
    private static Node copy2(Node head) {
        if (head == null) return head;
        Node cur = head;
        while (cur != null) {
            Node copyNode = new Node(cur.value);
            Node curNext = cur.next;
            cur.next = copyNode;
            copyNode.next = curNext;
            cur = curNext;
        }
        //上面已经重构好链表,下面开始处理random指针.
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;

            cur = cur.next.next; //一定是偶数个节点.因为每个节点都拷贝了一份.不用担心出现cur.next=null情况.
        }
        //现在random指针设置好了.开始拆分链表.
        // 1 1 2 2 3 3 4 4 ,需要一个新的节点.
        Node newHead = null, newTail = newHead;
        cur = head;
        while (cur != null) {
            if (newTail == null) newHead = newTail = cur.next;
            else newTail.next = cur.next;
            //下面剔除相等节点的后一个节点.
            newTail = cur.next;
            Node curNextNext = cur.next.next;
            cur.next = curNextNext;
            newTail.next = null;
            cur = cur.next;
        }
        return newHead;
    }


    private static class Node {
        Node next, random;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    private static void print(Node head) {
        if (head == null) return;
        System.out.println(head.value);
        print(head.next);
    }

    public static void main(String[] args) {
        Node head = null;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4
//        Node copy = copy(head);
        Node copy = copy2(head);
        print(copy);


    }


}
