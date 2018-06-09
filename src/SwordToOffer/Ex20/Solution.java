package SwordToOffer.Ex20;

import java.util.LinkedList;
import java.util.Stack;

public class Solution {

    LinkedList<Integer> dataStack = new LinkedList<>();
    LinkedList<Integer> helpStack = new LinkedList<>();

    public void push(int node) {
        dataStack.push(node);
        if (helpStack.isEmpty()) helpStack.addFirst(node);
        else helpStack.addFirst(helpStack.peekFirst() > node ? node : helpStack.peekFirst());
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.removeFirst();
            helpStack.removeFirst();
        }
    }

    public int top() {
        if (!dataStack.isEmpty()) return dataStack.peekFirst();
        return -1;
    }

    public int min() {
        if (!dataStack.isEmpty()) return helpStack.peekFirst();
        return -1;
    }
}