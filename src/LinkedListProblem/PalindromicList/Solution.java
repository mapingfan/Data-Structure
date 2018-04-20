package LinkedListProblem.PalindromicList;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个链表是否是回文.
 * <p>
 * 分析:
 * 可否把链表的节点存到数组中,然后递归判断链表是否是回文.也可以循环判断.
 * <p>
 * 思路二:
 * 把链表的后一半逆转,然后同一半比较.比较完再次逆转,得到最后结果.
 */
public class Solution {
    //true是回文,false不是.
    private static boolean isPalindromic(Node head) {
        if (head == null) {
            return false;
        }
        List<Node> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current);
            current = current.next;
        }
        //下面判断一个list是否是回文.
        boolean result = judge(list, 0, list.size() - 1);
        return result;

    }

    private static boolean judge(List<Node> list, int begin, int end) {
        if (begin == end) {
            return true;
        }
        if (end - begin == 1) {
            if (list.get(begin).value == list.get(end).value) {
                return true;
            } else {
                return false;
            }
        }
        if (list.get(0).value == list.get(list.size() - 1).value) {
            return judge(list, begin + 1, list.size() - 2);
        } else {
            return false;
        }
    }

    /**
     * 对于方案二就不实现了,首先还要获取链表的长度,然后确定中间节点时哪个,从那个节点
     * 开始逆转.逆转后还要进行比较操作.最后逆转回去.
     *  虽然省了空间,但是写起来没有上面的简单.
     *
     */




    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(1);
//        Node node3 = new Node(3);
//        Node node4 = new Node(1);


        Node headA = node1;
        node1.next = node2;
        node2.next = null;
//        node3.next = node4;
//        node4.next = null;

        boolean result = isPalindromic(headA);
        if (result) {
            System.out.println("回文");
        } else {
            System.out.println("不是回文");
        }

    }

    private static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
