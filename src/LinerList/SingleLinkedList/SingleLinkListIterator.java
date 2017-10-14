package LinerList.SingleLinkedList;

import LinerList.Exception.InvalidNodeException;
import LinerList.Interface.Iterator;
import LinerList.Interface.Node;

public class SingleLinkListIterator implements Iterator {
    private SingleLinkedList list;
    private Node current = null;

    public Node getCurrent() {
        return current;
    }

    public SingleLinkListIterator(SingleLinkedList list) {
        this.list = list;
        if (list == null) {
            current = null;
        } else {
            current = list.getHead();
        }
    }

    @Override
    public void first() {
        current = list.first();
    }

    @Override
    public Node next() {
        if (hasNext()) {
            current = ((SingleLinkedListNode) current).getNext();
            return current;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        SingleLinkedListNode cast_node = (SingleLinkedListNode)current;
        if (cast_node.getNext() != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object currentItem() {
        SingleLinkedListNode cast_node = (SingleLinkedListNode)current;
        return cast_node.getData();
    }
}
