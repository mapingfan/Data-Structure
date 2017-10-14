package LinerList.SingleLinkedList;

import LinerList.DoubleLinkedList.DoubleLinkedListNode;
import LinerList.Exception.InvalidNodeException;
import LinerList.Exception.ObjectNotFoundException;
import LinerList.Exception.OutOfBoundaryException;
import LinerList.Interface.LinkedList;
import LinerList.Interface.List;
import LinerList.Interface.Node;
import LinerList.Strategy.IntegerStrategy;
import LinerList.Strategy.Strategy;

/**
 * implement the liner list by linked list ;
 * implement the interface of List;
 * The interface Node defines the action of the SingleLinkedListNode;
 * The class SingleLinkedListNode represents the node in the LinkedList;
 * The SingleLinkedList represents the LinkList;
 *-----update------
 * implement the LinkedList interface;
 * The action of LinkedList interface is mostly about node;
 * define SingleLinkListIterator class to iterate the list;
 *
 * @author 马平凡
 */

public class SingleLinkedList implements List, LinkedList {
    private SingleLinkedListNode head;
    private int size;
    private Strategy strategy;

    public SingleLinkedListNode getHead() {
        return head;
    }

    public SingleLinkedList(Strategy strategy) {
        //with head node, storage nothing;
        //the next points null, the element points to null;
        //just for convenience;
        head = new SingleLinkedListNode();
        size = 0;
        this.strategy = strategy;
    }

    @Override
    public void add(Object object) {
        insert(size,object);
    }

    public void add(SingleLinkedListNode singleLinkedListNode) {
        insert(size, singleLinkedListNode.getData());
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
    public Node first() throws OutOfBoundaryException {
        if (head.getNext() != null) {
            return head.getNext();
        }
        throw new OutOfBoundaryException("The list is empty");
    }

    @Override
    public Node last() throws OutOfBoundaryException {
        if (head.getNext() != null) {
            SingleLinkedListNode work_next = head.getNext();
            while (work_next.getNext()!=null) {
                work_next = work_next.getNext();
            }
            return work_next;
        }
        throw new OutOfBoundaryException("The list is empty");
    }

    @Override
    public Node getNext(Node p) throws OutOfBoundaryException, InvalidNodeException {
        SingleLinkedListNode tmp = (SingleLinkedListNode)p;
        if (tmp == null) {
            throw new InvalidNodeException("The parameter is null");
    }
        if (tmp == last()) {
        throw new OutOfBoundaryException("Already the last node, no next node exists.");
    }
        return tmp.getNext();
    }

    @Override
    public Node getPre(Node p) throws OutOfBoundaryException, InvalidNodeException {
        SingleLinkedListNode cast_node = (SingleLinkedListNode)p;
        if (cast_node == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (cast_node == first()) {
            throw new OutOfBoundaryException("No pre node exists.");
        }
        SingleLinkedListNode tmp = head;
        SingleLinkedListNode work_next = head.getNext();
        while (work_next != null) {

            if (strategy.equal(work_next.getData(),cast_node.getData())) {
               break;
            } else {
                tmp = work_next;
                work_next = work_next.getNext();
            }
        }
        return tmp;
    }

    @Override
    public Node insertFirst(Object object) {
        SingleLinkedListNode  warp_node = new SingleLinkedListNode(object);
        warp_node.setNext(head.getNext());
        head.setNext(warp_node);
        size++;
        return warp_node;
    }

    @Override
    public Node insertLast(Object object) {
        SingleLinkedListNode  warp_node = new SingleLinkedListNode(object);
        SingleLinkedListNode cast_node = (SingleLinkedListNode)last();
        warp_node.setNext(cast_node.getNext());
        cast_node.setNext(warp_node);
        size++;
        return warp_node;
    }

    @Override
    public Node insertAfter(Node node, Object object) throws InvalidNodeException {
        SingleLinkedListNode cast_node = (SingleLinkedListNode)node;
        if (cast_node == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (cast_node == head) {
            throw new InvalidNodeException("The parameter node equals to head node.");
        }
        int index = indexOf(cast_node.getData());
        if (contains(cast_node.getData())) {

            insert(index+1,object);
        }
        return getNode(index+1);
    }

    /**
     * A method to get Node by index value;
     * @param pos
     * @return
     * @throws OutOfBoundaryException
     */
    private Node getNode(int pos) throws OutOfBoundaryException {
        if (pos < 0 || pos >= size) {
            throw new OutOfBoundaryException("The pos value is not proper.");
        }
        int index = 0;
        SingleLinkedListNode work_next = head.getNext();
        while (work_next != null && index < pos) {
            index++;
            work_next = work_next.getNext();
        }
        return work_next;
    }

    @Override
    public Node insertBefore(Node node, Object object) throws InvalidNodeException {
        SingleLinkedListNode cast_node = (SingleLinkedListNode)node;
        if (cast_node == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (cast_node == head) {
            throw new InvalidNodeException("The parameter is illegal.");
        }

        int index = indexOf(cast_node.getData());
        if (contains(cast_node.getData())) {
            insert(index,object);
        }
        return getNode(index);
    }

    @Override
    public Object remove(Node node) throws InvalidNodeException {
        SingleLinkedListNode cast_node = (SingleLinkedListNode)node;
        if (cast_node == null) {
            throw new InvalidNodeException("The parameter is null.");
        }
        if (cast_node == head) {
            throw new InvalidNodeException("The parameter node equals to head.");
        }
        if (!contains(cast_node.getData())) {
            throw new InvalidNodeException("The node is not in the list.");
        } else {
            int index = indexOf(cast_node.getData());
            return remove(index);
        }
    }

    @Override
    public Object removeFirst() throws OutOfBoundaryException {
        if (head.getNext() == null) {
            throw new OutOfBoundaryException("The list is empty now.");
        } else {
            return remove(0);
        }
    }

    @Override
    public Object removeLast() throws OutOfBoundaryException {
        if (head.getNext() == null) {
            throw new OutOfBoundaryException("The list is empty now.");
        } else {
            return remove(size-1);
        }
    }

    @Override
    public Object replace(Node node, Object object) throws InvalidNodeException {
        SingleLinkedListNode cast_node = (SingleLinkedListNode)node;
        if (cast_node == null) {
            throw new InvalidNodeException("The parameter node is null.");
        }
        if (cast_node == head) {
            throw new InvalidNodeException("The node equals to the head node.");
        }
        int index = indexOf(cast_node.getData());
        return replace(index,object);
    }

    @Override
    public SingleLinkListIterator elements() {
        return new SingleLinkListIterator(this);
    }

    @Override
    public boolean contains(Object o) {
        SingleLinkedListNode work_next = head.getNext();
        while (work_next != null) {
            if (strategy.equal(work_next.getData(),o)) {
                return true;
            } else {
                work_next = work_next.getNext();
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        SingleLinkedListNode work_next = head.getNext();
        if (contains(o)) {
            while (work_next != null) {
                if (strategy.equal(work_next.getData(),o)) {
                    return index;
                } else {
                    index++;
                    work_next = work_next.getNext();
                }
            }
        }
        return -1; //-1 represents the object is not in the list;
    }

    @Override
    public void insert(int pos, Object o) throws OutOfBoundaryException {
        if (pos < 0 || pos > size) {
            throw new OutOfBoundaryException("The pos value is not proper.");
        }
        int index = 0;
        SingleLinkedListNode temp = head;
        SingleLinkedListNode work_next = head.getNext();
        while (work_next != null && index < pos) {
            temp = work_next;
            work_next = work_next.getNext();
            index++;
        }
        SingleLinkedListNode tmp_node = new SingleLinkedListNode(temp.getNext(),o);
        temp.setNext(tmp_node);
        size++;
    }

    @Override
    public boolean insertBefore(Object o, Object e) throws ObjectNotFoundException {
       if (contains(o)) {
           int index = indexOf(o);
           insert(index, e);
           return true;
       }
       throw new ObjectNotFoundException("Object not found");
    }

    @Override
    public boolean insertAfter(Object o, Object e) throws ObjectNotFoundException {
        if (contains(o)) {
            int index = indexOf(o);
            insert(index+1,e);
            return true;
        }
        throw new ObjectNotFoundException("Object not found");
    }

    /*
     * before removing element, make sure the list is not empty ;
     */
    @Override
    public Object remove(int pos) throws OutOfBoundaryException {
        if (pos < 0 || pos >= size) {
            throw new OutOfBoundaryException("The pos value is not proper.");
        }
        if (size <= 0) {
            throw new OutOfBoundaryException("The list is empty.");
        }
        int index = 0;
        SingleLinkedListNode tmp = head; //!!!
        SingleLinkedListNode work_next = head.getNext();
        while (work_next != null && index < pos) {
            tmp = work_next;
            work_next = work_next.getNext();
            index++;
        }
        Object temp_object = work_next.getData();
        tmp.setNext(work_next.getNext());
        size--;
        return temp_object;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            if (remove(indexOf(o)) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object replace(int pos, Object e) throws OutOfBoundaryException {
        if (pos < 0 || pos >= size) {
            throw new OutOfBoundaryException("The pos value is not proper.");
        }
        insert(pos,e);
        return remove(pos+1);
    }

    @Override
    public Object get(int pos) throws OutOfBoundaryException {
        if (pos < 0 || pos >= size) {
            throw new OutOfBoundaryException("The pos value is not proper.");
        }
        int index = 0;
        SingleLinkedListNode work_next = head.getNext();
        while (work_next != null && index < pos) {
            work_next = work_next.getNext();
            index++;
        }
        return work_next.getData();
    }

    @Override
    public void printList() {
        SingleLinkedListNode work_next = head.getNext();
        while (work_next != null) {
            System.out.print(work_next.getData()+" ");
            work_next = work_next.getNext();
        }
    }

    public static void main(String[] args) throws ObjectNotFoundException, InvalidNodeException {
        SingleLinkedList linkedList = new SingleLinkedList(new IntegerStrategy());
        System.out.println("Now, the size is :"+linkedList.getSize());
        System.out.println(linkedList.isEmpty());
        linkedList.insert(0,new Integer(12));
        linkedList.insert(0,new Integer(13));
        linkedList.insert(0,new Integer(16));
        System.out.println("Now, the size is :"+linkedList.getSize());
        System.out.println(linkedList.contains(13));
        System.out.println(linkedList.contains(18));
        System.out.println(linkedList.insertBefore(13,25));
        System.out.println(linkedList.insertBefore(16,99));
        System.out.println(linkedList.insertAfter(99,102));
        linkedList.printList();
        System.out.println();
        linkedList.insert(linkedList.size,444);
        linkedList.add(new Integer(678));
        linkedList.add(new SingleLinkedListNode(8888));
        linkedList.add(new SingleLinkedListNode(888899));
        linkedList.printList();
        System.out.println();
        System.out.println(linkedList.first().getData());
        System.out.println(linkedList.last().getData());
        //System.out.println(linkedList.getNext(linkedList.last()));
        System.out.println(linkedList.getPre(linkedList.getNode(7)).getData());
        linkedList.insertFirst(798);
        linkedList.printList();
        System.out.println();
        linkedList.insertLast(799);
        linkedList.printList();
        System.out.println();
        linkedList.insertAfter(linkedList.getNode(0),333);
        linkedList.printList();
        System.out.println();
        linkedList.insertBefore(linkedList.getNode(1),333);
        linkedList.printList();
        System.out.println();
        System.out.println(linkedList.remove(linkedList.getNode(7)));
        linkedList.printList();
        System.out.println();
        System.out.println(linkedList.removeLast());
        linkedList.printList();
        System.out.println(linkedList.getNode(3));
        System.out.println(linkedList.getNode(3));
        System.out.println(linkedList.size);
        linkedList.replace(linkedList.getNode(3),13232);
        linkedList.printList();
        System.out.println();
        SingleLinkListIterator iterator = (SingleLinkListIterator) linkedList.elements();
        while (iterator.hasNext()) {
            SingleLinkedListNode tmp = (SingleLinkedListNode) iterator.next();
            System.out.print(tmp.getData()+" ");
            //System.out.print(iterator.getCurrent().getData()+ " ");

        }


    }
}
