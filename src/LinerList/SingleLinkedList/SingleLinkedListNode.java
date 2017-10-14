package LinerList.SingleLinkedList;

import LinerList.Interface.Node;

public class SingleLinkedListNode implements Node {
    private SingleLinkedListNode next;
    private Object element;

    public SingleLinkedListNode() {
        this(null,null);
    }

    public SingleLinkedListNode(Object element) {
        this.element = element;
        next = null;
    }

    public SingleLinkedListNode(SingleLinkedListNode next, Object element) {
        this.next = next;
        this.element = element;
    }

    public SingleLinkedListNode getNext() {
        return next;
    }

    public void setNext(SingleLinkedListNode next) {
        this.next = next;
    }

    @Override
    public Object getData() {
        return element;
    }

    @Override
    public void setData(Object object) {
        this.element = object;
    }
}
