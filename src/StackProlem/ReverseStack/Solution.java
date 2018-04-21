package StackProlem.ReverseStack;

import java.util.LinkedList;

/**
 * 逆转栈中的内容;
 * 分析:
 * 递归直接进行逆转.
 * 或者用辅助空间.
 */
public class Solution {

    private static void reverseStack(LinkedList<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        } else {
            Integer first = stack.removeFirst();
            reverseStack(stack);
            stack.addLast(first);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        stack.addFirst(4);
        stack.addFirst(5);

        reverseStack(stack);
        //关于验证,可以通过debug观察内存.

    }
}
