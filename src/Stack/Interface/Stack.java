package Stack.Interface;

import Stack.Exception.StackEmptyException;

public interface Stack {
    int getSize();

    boolean isEmpty();

    void push(Object object);

    Object pop() throws StackEmptyException;

    /*
     * get the first element in the stack;
     */
    Object peek() throws StackEmptyException;
}
