package Stack.StackList;

import Stack.Interface.Iterator;

public class StackListIterator implements Iterator {
    private StackList stackList;
    private StackNode current;

    public StackListIterator(StackList stackList) {
        this.stackList = stackList;
        current = stackList.getHead();
    }

    @Override
    public Object next() {
        if (hasNext()) {
            current = current.getNext();
            return current.getElemet();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (current.getNext() != null) {
            return true;
        }
        return false;
    }
}
