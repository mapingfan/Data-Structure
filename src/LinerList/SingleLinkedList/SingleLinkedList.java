package LinerList.SingleLinkedList;

import LinerList.Exception.ObjectNotFoundException;
import LinerList.Exception.OutOfBoundaryException;
import LinerList.Interface.List;
import LinerList.Strategy.IntegerStrategy;
import LinerList.Strategy.Strategy;

/**
 * implement the liner list by linked list ;
 * implement the interface of List;
 * The interface Node defines the action of the SingleLinkedListNode;
 * The class SingleLinkedListNode represents the node in the LinkedList;
 * The SingleLinkedList represents the LinkList;
 * @author 马平凡
 */

public class SingleLinkedList implements List {
    private SingleLinkedListNode head;
    private int size;
    private Strategy strategy;

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

    public static void main(String[] args) throws ObjectNotFoundException {
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
    }
}
