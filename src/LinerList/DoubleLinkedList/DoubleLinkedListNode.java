package LinerList.DoubleLinkedList;

import LinerList.Interface.Node;

/**
 * Double linked list;
 */
public class DoubleLinkedListNode implements Node {
    private DoubleLinkedListNode pre;
    private DoubleLinkedListNode next;
    private Object element;

    public DoubleLinkedListNode() {
        pre = null;
        next = null;
        element = null;
    }



    public DoubleLinkedListNode(Object object) {
        pre = next = null;
        this.element = object;
    }

    public void setPre(DoubleLinkedListNode pre) {
        this.pre = pre;
    }

    public void setNext(DoubleLinkedListNode next) {
        this.next = next;
    }

    public DoubleLinkedListNode getNext() {
        return next;
    }

    public DoubleLinkedListNode getPre() {
        return pre;
    }

    public DoubleLinkedListNode(DoubleLinkedListNode pre, DoubleLinkedListNode next, Object element) {
        this.pre = pre;
        this.next = next;
        this.element = element;
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
