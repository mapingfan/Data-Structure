package QueueProblem.ReverseQueue;

import java.util.LinkedList;

/**
 * 逆转队列;
 * 过于简单,直接写了.
 */
public class Solution {

    private static LinkedList<Integer> reverseQueue(LinkedList<Integer> queue) {
        LinkedList<Integer> stack = new LinkedList<>();
        while (!queue.isEmpty()) {
            stack.addFirst(queue.removeFirst());
        }
        while (!stack.isEmpty()) {
            queue.addLast(stack.removeFirst());
        }
        return queue;
    }

    //写个递归版本

    private static void rec_reverse(LinkedList<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        } else {
            Integer first = queue.removeFirst();
            rec_reverse(queue);
            queue.addLast(first);
        }
    }

    private static LinkedList<Integer> reverse_wrap(LinkedList<Integer> queue) {
        rec_reverse(queue);
        return queue;
    }


    private static void print(LinkedList<Integer> queue) {
        for (Integer integer : queue) {
            System.out.println(integer);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);
        print(queue);

        System.out.println(reverse_wrap(queue));

    }
}
