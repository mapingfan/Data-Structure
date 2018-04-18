package LinkedListProblem;

/**
 * 判断链表节点的个数是偶数还是级数.
 * 提供一种新的思路,设置一个指针,每次走跳两步.如果最后这个指针指向null,偶数,否则为奇数.
 */
public class EvenNode {

    private static boolean solution(Node head) {
        Node current = head;
        while (current != null) {
            current = current.next;
            if (current != null) {
                if (current.next == null) {
                    return false; //偶数个.
                } else {
                    current = current.next;
                    if (current.next == null) {
                        return true; //奇数个.
                    }
                }
            } else {
                return true;
            }

        }
        return false; //空链表偶数个.
    }


    //一个简单写法:

    /**
     * 这个写法很有启发意义.我们最后要判断两种情况,到底是指针是空还是指针的下一个为空.两种情况不能都发生.
     *      * 所以这个地方有个设计技巧,把两种情况and起来放在循环里跑.跑到最后循环出来,肯定是因为有一种情况不满足(两种不可能同时发生).
     *      * 这个时候简单判断下就好.
     *
     * 启迪意义很大.一是这个一次走两步的方法,二是这种书写代码的技巧值得学习.
     */
    private static boolean solutionV2(Node head) {
        while (head != null && head.next != null) {
            head = head.next.next;
        }
        if (head == null) {
            return false; //偶数
        } else {
            return true; //奇数.
        }
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);


        Node headA = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = null;

        System.out.println(solution(headA));
        System.out.println(solutionV2(headA));
    }
}

class Node {
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }
}
