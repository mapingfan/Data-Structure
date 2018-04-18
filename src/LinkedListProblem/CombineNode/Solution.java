package LinkedListProblem.CombineNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

/**
 * 两个链表在某个点相交,找出相交点.
 * 问题分析:
 * 已经知道两条链表的头节点,分别设置一个指针,判断两个指针的引用是否相等.
 * 如果相等,那么说明找到相交的点了.
 * 时间复杂度为O(mn),m,n为链表长度.
 * <p>
 * 还可以拿空间换时间策略.用散列表解决这个问题.
 * 随便遍历一条单链表.然后把表中每个节点的引用存入散列表.
 * 然后遍历第二条表,总会遇到公共元素,这时候无法加入进去,即找到了.
 * <p>
 * <p>
 * 思路三:
 * 用栈解决这个问题.把每条链表的所有节点分别压入两个栈中.
 * 然后分别出栈,如果当前元素相等,暂时记录下.
 * 知道遇到第一个不相等的.这个时候,上面记录的点就是相交点.
 * <p>
 * 下面简单实现下.
 */
public class Solution {


    //返回null表示没有相交点.
    private static Node solution(Node headA, Node headB) {
        Node ptrA = headA;
        Node ptrB = headB;
        while (ptrA != null) {
            Node tmpB = ptrB;
            while (tmpB != null) {
                if (ptrA == tmpB) {
                    return ptrA;
                } else {
                    tmpB = tmpB.next;
                }
            }
            ptrA = ptrA.next;
        }
        return null;
    }


    private static Node solutionV2(Node headA, Node headB) {
        Set<Node> set = new HashSet<>();
        Node current = headA;
        while (current != null) {
            set.add(current);
            current = current.next;
        }
        current = headB;
        while (current != null) {
            if (set.contains(current))
                return current;
            current = current.next;
        }
        return null; //没有公共点.
    }


    private static Node solutionV3(Node headA, Node headB) {
        LinkedList<Node> stackA = new LinkedList<>();
        LinkedList<Node> stackB = new LinkedList<>();
        //Java中用链表模拟栈.出栈用removeFirst,进栈用addFirst
        //其实只要能保证这种进出关系即可.
        while (headA != null) {
            stackA.addFirst(headA);
            headA = headA.next;
        }
        while (headB != null) {
            stackB.addFirst(headB);
            headB = headB.next;
        }
        Node temp = null;
        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            Node aFirst = stackA.removeFirst();
            Node bFirst = stackB.removeFirst();
            if (aFirst == bFirst)
                temp = aFirst;
            else
                return temp;

        }
        return temp;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        Node headA = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;


        Node nodea = new Node(7);
        Node nodeb = new Node(8);
        Node nodec = new Node(9);
        Node noded = new Node(10);
        Node nodee = new Node(11);

        Node headB = nodea;
        nodea.next = nodeb;
        nodeb.next = nodec;
        nodec.next = noded;
        noded.next = nodee;
        nodee.next = node3;

        Node result = solution(headA, headB);
        if (result != null)
            System.out.println(result.value);
        else
            System.out.println("无公共节点");
        result = solutionV2(headA, headB);
        if (result != null)
            System.out.println(result.value);
        else
            System.out.println("无公共节点");

        result = solutionV3(headA, headB);
        if (result != null)
            System.out.println(result.value);
        else
            System.out.println("无公共节点");
    }

}


class Node {
    Node next;
    int value;

    public Node(int i) {
        value = i;
    }
}
