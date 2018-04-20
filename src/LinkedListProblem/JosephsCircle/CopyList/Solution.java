package LinkedListProblem.JosephsCircle.CopyList;

import java.util.HashMap;
import java.util.Map;

/**
 * 拷贝一个链表.链表的每个节点由三部分组成:值+next指针+random指针.
 * 其中random指针可以随机指向链表的任意一个元素.
 *
 * 这个问题的关键就是不知道random指针指向谁,否则一次扫描就解决问题.
 * 所以这个地方肯定得想办法知道random具体指向谁.
 * 用散列表就是一个很好的策略.一旦存储到散列表中,就可以线性时间查找出random指针到底指向什么.
 */
public class Solution {

    private static Node copyList(Node head) {
        if (head == null) {
            return null;
        } else {
            Map<Node, Node> map = new HashMap<>();
            Node current = head;
            while (current != null) {
                map.put(current, new Node(current.value));
                current = current.next;
            }
            //下面开始设置指针的指向.
            current = head;
            while (current != null) {

                Node random = current.random;
                Node randomNode = map.get(random); //random指针的指向.
                Node readySettingNode = map.get(current);
                Node nextNode = map.get(current.next);
                readySettingNode.next = nextNode;
                readySettingNode.random = randomNode;
                current = current.next;
            }
            return map.get(head);
        }
    }

    private static class Node {
        int value;
        Node next;
        Node random;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node head = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        node1.random = node3;

        node2.random = node5;
        node3.random = node2;

        node4.random = node1;
        node5.random = node4;

        Node result = copyList(head);



    }


}
