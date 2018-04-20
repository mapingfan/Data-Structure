package LinkedListProblem.PrintList.FindNode;

/**
 * 设n为节点个数.节点编号为i,寻找使得i%k==0的最后一个节点. n,k已知.
 * 若n=19,k=3,那么最后的结果是18.
 * 分析下,其实思路很多,散列表完美解决,尝试下别的方法.
 *
 */
public class Solution {

    private static Node findNode(Node head, int n, int k) {
        Node current = head;
        Node result = current;
        if (head == null) {
            return null;
        }
        for (int i = 1; i <= n; i++) {
            if (i % k == 0) {
                result = current;
            }
            current = current.next;
        }
        return result;


    }


    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}
