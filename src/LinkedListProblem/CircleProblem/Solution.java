package LinkedListProblem.CircleProblem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 判断链表中是否存在环.
 * 分析:
 * 思路一: 如果链表中存在环,那么会存在两个节点有同一个后继.这个是特征,可以基于这种特征来判断.
 * <p>
 * 思路2: 遍历带环的链表时,会出现死循环.那么会导致同一个节点被访问两次.
 * 此时可以相处另一种思路:每次遍历时,把当前节点的hash值存入一个set中,
 * 那么当遍历同一个节点两次时,会两次加入,此时即可判断成环,跳出死循环即可.
 * <p>
 * 思路3: 龟兔赛跑的思路.两个指针,快慢指针,一个一次走一步,一个一次走两步.
 * 如果是直线赛道,那么两个指针不会相遇.如果是环形跑道,那么两个指针最终是会相遇的.
 * 如果快慢相遇了,那么肯定有环.
 * 这个思路也是利用一种启发式规则,带环链表中不存在某个节点的next指向null.
 * <p>
 * 上述三个思路,基于了三种启发式规则进行优化.其中思路一不好实现,因为不好判断.
 * 这个地方就不进行实现了,因为还得单独实现链表.有个思路就好.
 */
public class Solution {
    //head指向第一个节点的位置.
    private static boolean judge(Node head) {
        Set<Node> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head); //把第一个节点的引用存进去.
            head = head.next;
        }
        return false;
    }

    /**
     * 解释下为什么  while (slowPtr.next != null && fastPtr.next.next != null)
     * 循环需要这么写?
     * 考虑不存在环的情况下,如果只在循环体内走两步,可能会已经到最后一个元素,然后又走了两步,这样就会出现
     * 对空指针求next,会异常报错.为了避免这种情况,在循环条件进行判断.
     * 但是对于走一步的其实不需要.即使已经到了最后一个元素,下一个最多是Null,下次循环就结束了.
     * 但是对于这种走多步的,判断是必要的.
     *
     * @param head
     * @return
     */
    private static boolean judgeV2(Node head) {
        Node slowPtr = head, fastPtr = head;
        while (fastPtr.next != null && slowPtr != null && slowPtr.next != null && fastPtr != null && fastPtr.next.next != null) {
            slowPtr = slowPtr.next;
//            fastPtr = fastPtr.next.next; //这个地方有坑,如果fastPtr.next已经等于null,那么直接退出.
            fastPtr = fastPtr.next;
            if (fastPtr != null) {
                fastPtr = fastPtr.next;
            } else {
                return false;
            }
            if (fastPtr==null){
                return false;
            }
            if (slowPtr == fastPtr) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node head = node1;
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node4;
        System.out.println(judge(head));
        System.out.println(judgeV2(head));

    }


}


class Node {
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
