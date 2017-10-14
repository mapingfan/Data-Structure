package LinerList.DoubleLinkedList;

import LinerList.Exception.InvalidNodeException;
import LinerList.Exception.OutOfBoundaryException;
import LinerList.Interface.Iterator;
import LinerList.Interface.Node;

public class DoubleLinkListIterator implements Iterator {
    DoubleLinkedList list;
    Node current;

    public DoubleLinkListIterator(DoubleLinkedList list) {
        this.list = list;
        if (this.list.isEmpty()) {
            current = null;
        } else {
            current = list.getHead();
        }
    }

    @Override
    public void first() throws OutOfBoundaryException {
        if (list.isEmpty()) {
            throw new OutOfBoundaryException("The list is empty.");
        }
        current = list.first();
    }

    @Override
    public Node next() {
        DoubleLinkedListNode tmp = (DoubleLinkedListNode)current;
        if (tmp.getNext() != null) {
            current = ((DoubleLinkedListNode) current).getNext();
            return current;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        DoubleLinkedListNode tmp = (DoubleLinkedListNode)current;
        if (tmp.getNext() != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object currentItem() {
        return current.getData();
    }
}
