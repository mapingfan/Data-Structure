package LinerList.DoubleLinkedList;

import LinerList.Exception.InvalidNodeException;
import LinerList.Exception.OutOfBoundaryException;
import LinerList.Interface.LinkedList;
import LinerList.Interface.Node;
import LinerList.SingleLinkedList.SingleLinkListIterator;
import LinerList.Strategy.IntegerStrategy;
import LinerList.Strategy.Strategy;

public class DoubleLinkedList implements LinkedList {
    private DoubleLinkedListNode head;
    private DoubleLinkedListNode tail;
    private Strategy strategy;
    int size;

    public DoubleLinkedListNode getHead() {
        return head;
    }

    public DoubleLinkedList(Strategy strategy) {
        size = 0;
        head = new DoubleLinkedListNode(null,null,null);
        tail = head;
        this.strategy = strategy;

    }

    public void addAtBeginning(Object object) {
        insertFirst(object);
    }

    public void addAtEnding(Object object) {
        insertLast(object);
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
    public Node first() throws OutOfBoundaryException {
        if (head.getNext() == null) {
            throw new OutOfBoundaryException("The list is empty.");
        }
        return head.getNext();
    }

    @Override
    public Node last() throws OutOfBoundaryException {
        if (head.getNext() == null) {
            throw new OutOfBoundaryException("The list is empty.");
        }
        return tail;

    }

    @Override
    public Node getNext(Node p) throws OutOfBoundaryException, InvalidNodeException {
        if (p == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (p == last()) {
            throw new OutOfBoundaryException("The parameter is wrong.");
        }
        DoubleLinkedListNode cast_node = (DoubleLinkedListNode)p;
        return cast_node.getNext();
    }

    @Override
    public Node getPre(Node p) throws OutOfBoundaryException, InvalidNodeException {
        if (p == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (p == head) {
            throw new OutOfBoundaryException("The parameter is wrong.");
        }
        DoubleLinkedListNode cast_node = (DoubleLinkedListNode)p;
        return cast_node.getPre();
    }

    @Override
    public Node insertFirst(Object object) {
        DoubleLinkedListNode wrap_node = new DoubleLinkedListNode(object);
        /*if (isEmpty()) {
            wrap_node.setNext(head.getNext());
            wrap_node.setPre(head);
            head.setNext(wrap_node);
            tail = wrap_node;
        } else {
            DoubleLinkedListNode tmp = head.getNext();
            wrap_node.setNext(tmp);
            wrap_node.setPre(head);
            head.setNext(wrap_node);
            tmp.setPre(wrap_node);
        }
        size++;
        return wrap_node;*/
        wrap_node.setNext(head.getNext());
        wrap_node.setPre(head);
        head.setNext(wrap_node);
        if (isEmpty()) { // if the list is empty before insertion, move the tail pointer;
                tail = wrap_node;
        } else {
            wrap_node.getNext().setPre(wrap_node);
        }
        size++;
        return wrap_node;
    }

    @Override
    public Node insertLast(Object object) {
        DoubleLinkedListNode wrap_node = new DoubleLinkedListNode(object);
        wrap_node.setNext(tail.getNext());
        wrap_node.setPre(tail);
        tail.setNext(wrap_node);
        tail = wrap_node;
        size++;
        return wrap_node;
    }

    @Override
    public Node insertAfter(Node node, Object object) throws InvalidNodeException {
        if (node == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (node == head) {
            return insertFirst(object);
        }
        if (node == tail) {
            return insertLast(object);
        }
        DoubleLinkedListNode wrap_node = new DoubleLinkedListNode(object);
        DoubleLinkedListNode cast_node = (DoubleLinkedListNode)node;
        DoubleLinkedListNode work_next = head.getNext();
        while (work_next != null) {
            if (strategy.equal(cast_node.getData(),work_next.getData())) {
                DoubleLinkedListNode next_node = work_next.getNext();
                wrap_node.setNext(next_node);
                wrap_node.setPre(work_next);
                next_node.setPre(wrap_node);
                work_next.setNext(wrap_node);
                size++;
                return wrap_node;
            } else {
                work_next = work_next.getNext();
            }
        }
        return null; //represent the node is not in the list;
    }

    @Override
    public Node insertBefore(Node node, Object object) throws InvalidNodeException {
        if (node == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (node == head) {
            throw new InvalidNodeException("The parameter is wrong.");
        }
        DoubleLinkedListNode cast_node = (DoubleLinkedListNode)node;
        return insertAfter(getPre(cast_node),object);
    }

    @Override
    public Object remove(Node node) throws InvalidNodeException {
        if (node == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (node == head) {
            throw new InvalidNodeException("The parameter is wrong.");
        }
        DoubleLinkedListNode work_next = head.getNext();
        DoubleLinkedListNode cast_node = (DoubleLinkedListNode)node;
        while (work_next != null) {
            if (strategy.equal(cast_node.getData(), work_next.getData())) {
                DoubleLinkedListNode pre_node = work_next.getPre();
                DoubleLinkedListNode next_node = work_next.getNext();
                pre_node.setNext(next_node);
                if (work_next == last()) {
                    tail = pre_node;
                } else {
                    next_node.setPre(pre_node);
                }
                size--;
                return work_next.getData();
            } else {
                work_next = work_next.getNext();
            }
        }
        return null; //null represent the node is not in the list;
    }

    @Override
    public Object removeFirst() throws OutOfBoundaryException, InvalidNodeException {
        if (isEmpty()) {
            throw new OutOfBoundaryException("The list is empty.");
        }
        return remove(first());

    }

    @Override
    public Object removeLast() throws OutOfBoundaryException, InvalidNodeException {
        if (isEmpty()) {
            throw new OutOfBoundaryException("The list is empty.");
        }
        return remove(last());
    }

    @Override
    public Object replace(Node node, Object object) throws InvalidNodeException {
        if (node == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (node == head) {
            throw new InvalidNodeException("The parameter is wrong.");
        }
        insertAfter(node,object);
        return remove(node);
    }

    @Override
    public SingleLinkListIterator elements() {
        return null;
    }

    public DoubleLinkListIterator iterate() {
        return new DoubleLinkListIterator(this);
    }

    public void printList() {
        if (!isEmpty()) {
            DoubleLinkedListNode work_next = head.getNext();
            while (work_next != null) {
                if (work_next == last()) {
                    System.out.print(work_next.getData());
                } else {
                    System.out.print(work_next.getData() + "-->");
                }
                work_next = work_next.getNext();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws InvalidNodeException {
        DoubleLinkedList linkedList = new DoubleLinkedList(new IntegerStrategy());
        System.out.println(linkedList.isEmpty());
        /*linkedList.addAtBeginning(new Integer(20));
        linkedList.addAtBeginning(new Integer(15));*/
        /*linkedList.insertFirst(new Integer(20));
        linkedList.insertFirst(new Integer(15));*/
        linkedList.insertLast(new Integer(20));
        linkedList.insertLast(new Integer(15));
        linkedList.insertLast(new Integer(18));
        linkedList.insertLast(new Integer(23));
        linkedList.insertBefore(linkedList.first(),new Integer(199));
        linkedList.insertAfter(linkedList.last(),new Integer(2000));
        linkedList.insertFirst(new Integer(888));
        linkedList.printList();
       linkedList.remove(linkedList.first());
       linkedList.remove(linkedList.last());
       linkedList.printList();
       linkedList.removeFirst();
       linkedList.removeLast();
       linkedList.printList();
       linkedList.replace(linkedList.first(),7899);
       linkedList.printList();
       DoubleLinkListIterator doubleLinkListIterator = linkedList.iterate();
       while (doubleLinkListIterator.hasNext()) {
           DoubleLinkedListNode tmp = (DoubleLinkedListNode) doubleLinkListIterator.next();
           System.out.print(tmp.getData()+" ");
       }
        System.out.println();
        linkedList.printList();

    }
}
