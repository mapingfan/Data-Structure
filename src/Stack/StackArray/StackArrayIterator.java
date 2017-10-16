package Stack.StackArray;

import Stack.Interface.Iterator;

/**
 * iterate the stack from top to end ;
 */
public class StackArrayIterator implements Iterator {
    private StackArray stackArray;
    private int current;

    public StackArrayIterator(StackArray stackArray) {
        this.stackArray = stackArray;
        current = stackArray.getSize()-1;
    }

    @Override
    public Object next() {
        if (hasNext()) {
           return stackArray.elements[current--];
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (current - 1 >= -1) {
            return true;
        }
        return false;
    }
}
