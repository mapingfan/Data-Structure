package StackProlem.QueueStack;

import java.util.LinkedList;

/**
 * 用两个队列实现栈.
 * <p>
 * 这个地方把栈当作一种模型来理解,即具有后进先出的结构即可.
 * <p>
 * 然后用两个队列模拟这种操作即可.
 * <p>
 * 队列A,队列B:
 * 任意选一个队列放元素.
 * 定义出栈操作:
 * 因为要满足后进先出.而队列相反,所以我们把队列A的n-1个元素拷贝到队列B中,
 * 此时从队列A出队列,队列A空.
 * 定义入栈操作:
 * 往非空的队列B插入就好.
 * 要想出栈,就再次拷贝到空队列中即可.
 */
public class Solution {

    public static void main(String[] args) {
        StackWithTwoQueue stack = new StackWithTwoQueue();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());

        stack.push(12);
        stack.push(11);

        System.out.println(stack.pop());

    }
}

class StackWithTwoQueue {
    LinkedList<Integer> qA = new java.util.LinkedList<>();
    LinkedList<Integer> qB = new java.util.LinkedList<>();

    //定义出栈入栈操作.过程中,要始终保持一个队列是空的.
    public void push(Integer value) {
        if (qA.isEmpty()) {
            qB.addLast(value);
        } else {
            qA.addLast(value);
        }
    }

    public Integer pop() {
        if (qA.isEmpty() && !qB.isEmpty()) {
            //把队列B的元素拷贝过来.
            while (qB.size() > 1) {
                Integer first = qB.removeFirst();
                qA.addLast(first);
            }
            return qB.removeFirst();
        } else if (qB.isEmpty() && !qA.isEmpty()) {
            while (qA.size() > 1) {
                Integer first = qA.removeFirst();
                qB.addLast(first);
            }
            return qA.removeFirst();
        } else {
            System.out.println("栈为空,请先入栈,然后出栈.");
            return -1; //A
        }
    }

}
