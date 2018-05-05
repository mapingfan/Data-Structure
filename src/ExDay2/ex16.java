package ExDay2;


import java.util.ArrayList;

/**
 * 链表划分问题.给地一个链表,内容整形数值.现在给定一个数字,讲链表划分为三个部分.
 * 小于等于大于.然后划分后链表仍然连续.
 * 荷兰国旗问题的变种.荷兰国旗问题针对数组,这个地方针对链表.
 * 思路一: 把链表放到数组中实现.
 * 思路一很自然,但是需要开辟额外空间,是否有O(1)空间的办法呢?
 * <p>
 * 思路二: 荷兰国旗在链表问题下的特殊解决方案,设置三个变量(三个桶),然后扫描这个链表
 * 把小于给定值的节点添加到相应节点后面.一遍扫描结束后,会得到三条子链,最后把这三条合并.
 * 其实这个思路也可以用于数组的荷兰问题实现,开始三个数组,然后分别放给定范围数字,然后合并.
 * 只不过不是最优而已.
 * 下面试下下思路二.
 */
public class ex16 {


    private static Node fun2(Node head, int value) {
        if (head == null || head.next == null) return head; //特判.
        Node less = null, equal = null, more = null, current = head;
        Node lessTail = less, euqalTail = equal, moreTail = more;
        while (current != null) {
            Node tmp = current.next;
            if (current.value < value) {
                if (lessTail == null) {
                    less = lessTail = current;
                } else {
                    lessTail.next = current;
                    lessTail = current;
                }
                lessTail.next = null;
            } else if (current.value > value) {
                if (moreTail == null) {
                    more = moreTail = current;
                } else {
                    moreTail.next = current;
                    moreTail = current;
                }
                moreTail.next = null;
            } else {
                if (euqalTail == null) {
                    equal = euqalTail = current;
                } else {
                    euqalTail.next = current;
                    euqalTail = current;
                }
                euqalTail.next = null;
            }
            current = tmp;
        }
        //合并链条.注意是否有空链条存在.
        if (less != null) {
            if (equal != null) {
                lessTail.next = equal;
                if (more != null)
                    euqalTail.next = more;
                else ;//do nothing;

            } else {
                if (more != null) lessTail.next = more;
                else ; //do nothing.
            }
            return less;
        } else {
            if (equal != null) {
                if (more != null) {
                    euqalTail.next = more;
                } else ; //do nothing ;
                return equal;
            } else {
                if (more!=null)
                    return more;
                else
                    return null;
            }
        }
    }


    private static Node fun1(Node head, int value) {
        if (head == null || head.next == null) return head;
        ArrayList<Node> array = new ArrayList<>();
        Node current = head;
        while (current != null) {
            array.add(current);
            current = current.next;
        }
        //对数组进行划分.
        partition(array, value);
        for (int i = 1; i < array.size(); i++) {
            array.get(i - 1).next = array.get(i);
        }
        array.get(array.size() - 1).next = null;
        return array.get(0);
    }

    private static void partition(ArrayList<Node> array, int value) {
        int lessRegion = -1, moreRegion = array.size(), current = 0;
        while (current < moreRegion) {
            if (array.get(current).value < value) {
                swap(array, ++lessRegion, current++);
            } else if (array.get(current).value > value) {
                swap(array, --moreRegion, current);
            } else current++;
        }
    }

    private static void swap(ArrayList<Node> array, int i, int current) {
        Node tmp = array.get(i);
        array.set(i, array.get(current));
        array.set(current, tmp);
    }


    private static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    private static void print(Node head) {
        if (head == null) return;
        System.out.print(head.value + " ");
        print(head.next);
    }


    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        print(head1);
        Node newHead = fun2(head1, 5);
        System.out.println();
        print(newHead);
//        7 9 1 8 5 2 5
//        2 1 5 5 8 9 7

    }
}
