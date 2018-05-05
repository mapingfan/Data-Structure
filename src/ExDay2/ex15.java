package ExDay2;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 判断链表是否回文.
 * 思路一: 将链表入栈,然后再次遍历的同时比较栈顶的元素是否相等.
 * 思路二: 逆转后一半单链表,然后判断.
 * 思路三: 将链表装入数组中,然后转换判断数组是否回文.
 */
public class ex15 {

    //使用一半栈空间解决.
    public static boolean fun3(Node head) {
        if (head==null) return false;
        Node fastPtr = head, slowPtr = head;
        while (fastPtr.next != null && fastPtr.next.next != null) {
            fastPtr = fastPtr.next.next; //
            slowPtr = slowPtr.next; //slowPtr->mid
        }
        fastPtr = slowPtr.next; //到右半边的第一个节点.
        slowPtr.next = null; //中间节点断开,next指向null.
        Node n3 = null;
        while (fastPtr != null) { //逆转从fastPtr开始的后半链表.
            n3 = fastPtr.next;
            fastPtr.next = slowPtr;
            slowPtr = fastPtr;
            fastPtr = n3;
        }
        Node rightHead = slowPtr; //后半个链表的头指针.
        Node leftHead = head;
        while (leftHead != null && rightHead != null) {
            if (leftHead.value!=rightHead.value) return false;
            leftHead = leftHead.next;
            rightHead = rightHead.next;
        }
        return true;
    }


    public static boolean fun1(Node head) {
        if (head == null) return false;
        LinkedList<Node> stack = new LinkedList<>();
        Node current = head;
        while (current != null) {
            stack.addFirst(current);
            current = current.next;
        }
        current = head;
        while (current != null) {
            if (current.value != stack.removeFirst().value) return false;
            else current = current.next;
        }
        return true;
    }

    public static boolean fun2(Node head) {
        if (head == null) return false;
        ArrayList<Node> arrayList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            arrayList.add(current);
            current = current.next;
        }
        //下面判断数组是不是回文.
        boolean result = isPalindromic(arrayList, 0, arrayList.size() - 1);
        return result;
    }

    private static boolean isPalindromic(ArrayList<Node> arrayList, int begin, int end) {
        if (arrayList == null) return false;
        if (begin == end) return true;
        if (begin < end && arrayList.get(begin).value == arrayList.get(end).value) {
            return isPalindromic(arrayList, begin + 1, end - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        Node head = null;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        System.out.println(fun1(head));
        System.out.println(fun3(head));
    }




    @Data
    private static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
