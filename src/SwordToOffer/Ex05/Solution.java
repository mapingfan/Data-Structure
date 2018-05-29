package SwordToOffer.Ex05;

import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    //如果stack2不空,那么直接从stack2出.如果空了,从stack1先拷贝过来.
    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            //拷贝元素.
            if (stack1.isEmpty()) {
                return -1; //异常检测.
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }
}