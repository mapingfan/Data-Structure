package ExDay2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最后一个题目,返回两个链表相交的第一个节点.
 * 不知道链表是否存在环.先判断链表是否存在环,并求出入环第一个节点.
 * 链表A,B
 * 如果A有环存在,B无环存在.
 * 那么他们不会有相交点.简单分析下:B与A的交点可能出现在如下几个位置,环外,环内.
 * 对于环外,画图可以观察不存在.因为只有一个next指针域.
 * 同理对于环内,也是不存在相交节点的.
 * <p>
 * 所以只需要考虑两个情况:都无环的两条链表+都有环的两条链表.
 * <p>
 * 分别针对这两种情况进行求解即可.
 */
public class ex18 {

    private static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 存在环,找出入环节点.不存在返回null.
     *
     * @param head
     * @return
     */
    private static Node hasCircle(Node head) {
        if (head == null) return head;
        Node fastPtr = head, slowPtr = head;
        while (fastPtr != slowPtr) {
            if (fastPtr.next == null || fastPtr.next.next == null) return null;
            else {
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next.next;
            }
        }
        //相交了,slowPtr回到head(任意一个回去即可),两个指针每次走一步.//folyd大爷提出的方法.
        slowPtr = head;
        while (slowPtr != fastPtr) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next;
        }
            return slowPtr;
        }


        private static Node getIntersectNode (Node headA, Node headB){
            if (headA == null || headB == null) return null;
            Node loopA = hasCircle(headA);
            Node loopB = hasCircle(headB);
            if (loopA == null && loopB == null) { //都不存在环.
                return noLoop(headA, headB);
            } else if (loopA != null && loopB != null) {
                return bothLoop(headA, loopA, headB, loopB);
            } else return null;
        }

        private static Node bothLoop (Node headA, Node loopA, Node headB, Node loopB){
            //两个链表都有环,求交点.
            /**
             * 左神课上已经分析过了,存在三种情况.
             *
             * 两个6 6 不相交.返回null.
             * 两个6 6 相交.
             * 相交情形画图看下.
             *
             */
            if (loopA == loopB) {
                Node aEnd = loopA.next;
                Node cur = headA;
                Set<Node> nodeSet = new HashSet<>();
                while (cur != aEnd) {
                    nodeSet.add(cur);
                    cur = cur.next;
                }
                cur = headB;
                while (cur != aEnd) {
                    if (nodeSet.contains(cur)) return cur;
                    cur = cur.next;
                }
                return null;
            } else { //两个交点时,返回任意一个即可.
                Node cur = loopA.next;
                while (cur != loopA) {
                    if (cur == loopB) return loopA;
                    cur = cur.next;
                }
                return null;
            }
        }

        private static Node noLoop (Node headA, Node headB){
            //找两个无环链表的交点.
            /**
             * 两个思路:一是hash表,简单容易实现.
             * 二是:算出A的长度,算出B的长度.然后让长的先走它们的差值次.然后两人同时开始走.
             * 走到两人相等时候,找到了.
             * 对于思路一比较简单,不实现了.直接实现思路二.
             */

            Node curA = headA, curB = headB;
            int cnt = 0;
            while (curA != null) {
                cnt++;
                curA = curA.next;
            } //这个地方使用一个小机灵.一个变量算出差值.A 长20,B是15.那么差值就是5.
            while (curB != null) {
                cnt--;
                curB = curB.next;
            }
            if (curA != curB) return null; //无环连个相交,最后一个节点必然相等.
            curA = headA;
            curB = headB;
            if (cnt > 0) {
                //A 走
                while (cnt > 0)
                    curA = curA.next;
            } else {
                //B走.
                cnt = Math.abs(cnt);
                while (cnt > 0)
                    curB = curB.next;
            }
            while (curA != curB) {
                curA = curA.next;
                curB = curB.next;
            }
            return curA;
        }
    }
