package Stack.StackList;

import Stack.Exception.StackEmptyException;
import Stack.Interface.Stack;

public class StackList implements Stack {
    private StackNode head;
    private StackNode top;
    int size;

    public StackNode getHead() {
        return head;
    }

    public StackList() {
        head = new StackNode();
        top = head;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(Object object) {
        StackNode tmp = new StackNode(object);
        tmp.setNext(head.getNext());
        head.setNext(tmp);
        top = tmp;
        size++;
    }

    @Override
    public Object pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("The stack is empty");
        }
        StackNode temp_node = top;
        head.setNext(temp_node.getNext());
        size--;
        top = head.getNext();
        return temp_node.getElemet();
    }

    @Override
    public Object peek() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("The stack is empty");
        }
        return top.getElemet();
    }

    public StackListIterator iterator() {
        return new StackListIterator(this);
    }

    public static void main(String[] args) {
        StackList stackList = new StackList();
        System.out.println(stackList.getSize());
        System.out.println(stackList.isEmpty());
        stackList.push(new Integer(999));
        System.out.println(stackList.getSize());
        System.out.println(stackList.isEmpty());
        stackList.push(new Integer(132));
        stackList.push(new Integer(13443));
        stackList.push(new Integer(3543));
        /*stackList.pop();
        stackList.pop();
        stackList.pop();
        stackList.pop();*/
        System.out.println(stackList.peek());
        System.out.println(stackList.getSize());
        StackListIterator listIterator = stackList.iterator();
        while (listIterator.hasNext()) {
            Object tmp = listIterator.next();
            System.out.print(tmp+" ");
        }
    }
}
