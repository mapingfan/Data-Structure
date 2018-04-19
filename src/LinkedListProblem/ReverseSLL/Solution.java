package LinkedListProblem.ReverseSLL;


//逆转单链表.
public class Solution {
    //反转链表不是倒序输出.这个地方理解有问题.
    private static void reversePrint(Node head) {
        if (head == null) {
            return;
        }
        reversePrint(head.next);
        System.out.println(head.value);
    }

    /**
     * 递归反转链表.
     * 思路很简单: 反转一个链表 与反转子链表的关系. 反转链表 = 反转子链表+第一个节点放置到最后一个位置.
     * 推导下边界值: 如果链表只有一个节点,直接返回当前节点即可.如果链表为空,不存在反转,直接返回null.
     * <p>
     * <p>
     * 因为递归反转链表时,最后返回的是头结点,所以设置一个辅助函数,先找出了最后的节点当作头节点.然后反转.
     * 最后返回的反转后链表的头结点,也就是原先的尾节点.
     * <p>
     * 所以最后有了reverse_fix函数.
     */

    //辅助函数,用于寻找反转后的头节点.
    private static Node findLastNode(Node head) {
        Node current = head;
        Node pre = current;
        while (current != null) {
            pre = current;
            current = current.next;
        }
        return pre;
    }

    //修复了单纯反转后丢失头结点的问题.调用了两个辅助函数.
    public static Node reverse_fix(Node head) {
        Node newHead = findLastNode(head);
        rec_reverseList(head);
        return newHead;
    }

    //真正反转的函数.
    private static Node rec_reverseList(Node head) {

        //正常情况,这个情况不会遇到,因为head.next==null,必然在这之前执行了,然后就返回了.但是可能会遇到链表为空的请况,所以
        //这个地方写上.
        if (head == null) {
            return head;
        }
        if (head.next == null) {  //只有一个元素时
            return head;
        } else {
            Node result = rec_reverseList(head.next);
            result.next = head;
            head.next = null;
            return head;
        }
    }


    private static Node reverse(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node result = reverse(head.next);
        result.next = head;
        head.next = null;
        return head;
    }

    /**
     * 逆转一个节点,需要知道当前节点的前后信息.
     * 如果不保存后面的信息,那么下面的逆转无法进行.
     * 如果不知道前前一个的信息,那么无法逆转.
     */
    private static Node reverseV2(Node head) {
        Node current = head, pre = null, next = null;
        while (current != null) {
            next = current.next; //先把后面的链条保存起来.
            current.next = pre;
            //逆转结束,current节点成为了pre节点.
            pre = current;
            if (next == null) {
                break;
            } else {
                current = next;//current节点要移动到下一个节点.
            }
        }
        return current;
    }


    private static Node reverseList(Node head) {
        //循环反转链表.
        /**
         * 分下下思路:考虑第i个节点,如果我们要反转他,需要知道i-1个节点,把i的next指向i-1即可.
         * 如果只是这样,那么i后面节点的信息就丢失了,为了链条不断,需要保存下.
         * 下面开始实现.
         */

        Node preNode = null;
        Node nextNode = null;
        Node currentNode = head;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        return preNode;


    }


    private static void printList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        printList(head.next);
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
        node5.next = null;

        Node result = reverseList(head);
        printList(result);

    }


}

class Node {
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }

}