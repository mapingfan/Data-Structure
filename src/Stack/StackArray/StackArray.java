package Stack.StackArray;

import Stack.Exception.StackEmptyException;
import Stack.Interface.Stack;

public class StackArray implements Stack  {
    private int size;
    private int top;
    private static int DEFAULTLENGTH = 2;
    Object[] elements;

    public StackArray() {
        size = 0;
        top = -1;
        elements = new Object[DEFAULTLENGTH];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void push(Object object) {
        if (size == DEFAULTLENGTH) {
            expandSpace();
        }
        top++;
        elements[top] = object;
        size++;
    }

    private void expandSpace() {
        Object[] tmp = new Object[DEFAULTLENGTH*2];
        for (int i = 0; i <size ; i++) {
            tmp[i] = elements[i];
        }
        DEFAULTLENGTH *= 2;
        elements = tmp;
    }

    @Override
    public Object pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("The stack is empty.");
        }
        size--;
        return elements[top--];
    }

    @Override
    public Object peek() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("The stack is empty.");
        }
        return elements[top];
    }

    /**
     *
     * @return return the iterator of the stack array;
     */
    public StackArrayIterator iterator() {
        return new StackArrayIterator(this);
    }

    public static void main(String[] args) {
        StackArray stackArray = new StackArray();
        System.out.println(stackArray.isEmpty());
        System.out.println(stackArray.getSize());
        stackArray.push(new Integer(12));
        stackArray.push(new Integer(99));
        System.out.println(stackArray.peek());
        System.out.println(stackArray.getSize());
        stackArray.push(12);
        stackArray.push(43);
        System.out.println(stackArray.size);
        StackArrayIterator stackArrayIterator = stackArray.iterator();
        while (stackArrayIterator.hasNext()) {
            Object tmp = stackArrayIterator.next();
            System.out.print(tmp+" ");
        }
    }
}
