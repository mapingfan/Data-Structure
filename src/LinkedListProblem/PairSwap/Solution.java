package LinkedListProblem.PairSwap;

/**
 * 对于链表 1-->2-->3-->4-->X ,逐对交换,变换为
 * 2-->1-->4-->3-->X
 * 分析:
 * 先考虑偶数的情况,两两一组,然后组内交换两个元素.
 * 交换两个元素,需要涉及4个元素,所以需要4个临时变量保存中间结果.
 *
 * 总结下:这个题目做的有点久了,原因就是某个细微的点疏忽了.
 * 前面分析需要4个指针.对于pre指针,我一开始想当然以为是一下就可以.
 * 当时我只分析了四个元素.当6个元素时,pre指针其实要求移动两次的.
 * 一个疏忽,调试了一个小时.
 * 其次就是这个题目的一些小技巧:第一次交换后,要保存住新头结点的位置,最后要返回这个头结点,用于检查结果.
 *
 *
 *
 *
 */
public class Solution {

    private static Node solution(Node head) {
        Node ptrA, prePtrA = null, ptrB, nextPtrB = null;
        ptrA = head;
        ptrB = head.next;
        while (ptrA != null && ptrB != null) {
            nextPtrB = ptrB.next;
            ptrB.next = ptrA;
            ptrA.next = nextPtrB;
            Node tmp = ptrA;
            ptrA = ptrB;
            ptrB = tmp;
            if (prePtrA != null) {
                prePtrA.next = ptrA;
                prePtrA = ptrA.next;  //注意这个地方,一开始只考虑四个元素的时候,我想当然以为pre指针只跳一格子.其实也是两个,一个小疏忽导致我调试半天.
            } else {
                head = ptrA;
                prePtrA = ptrB;
            }
            ptrA = ptrA.next;
            if (ptrA.next == null) {
                return head;
            } else {
                ptrA = ptrA.next;
            }
            ptrB = ptrB.next;
            if (ptrB.next == null) {
                return head;
            } else {
                ptrB = ptrB.next;
            }

        }
        return head;
    }

    private static Node rec_solution(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            //两个节点直接逆转.
            Node currentA = head;
            Node currentB = head.next;
            Node nextB = currentB.next;
            currentB.next = currentA;
            currentA.next = nextB;
            return currentB;
        }
        Node result = rec_solution(head.next.next);
        Node nextHead = head.next;
        head.next = result;
        nextHead.next = head;
        return nextHead;
    }

//    private static void print(Node head) {
//        if (head == null) {
//            return;
//        }
//        System.out.println(head.value);
//        print(head.next);
//    }

    private static void print(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;

        }
    }


    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node head = node1;
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = null;
//        Node result = solution(head);

        Node result = rec_solution(head);
        print(result);
    }

    static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
