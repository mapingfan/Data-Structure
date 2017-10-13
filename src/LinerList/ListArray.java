package LinerList;

import java.util.Arrays;

public class ListArray implements List {
    private int LISTLENGTH = 8;
    private Strategy strategy = null; //use the strategy to compare the object;
    private int size; //represent the size of list;
    private Object[] elements; //use this to storage the object;

    public ListArray(Strategy strategy) {
        this.strategy = strategy;
        size = 0;
        elements = new Object[LISTLENGTH];
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
        for (int i = 0; i < size; i++) {
            if (strategy.equal(o,elements[i])) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (strategy.equal(o,elements[i])) {
                return i;
            } else {
                continue;
            }
        }
        return -1; // -1 represents the o is not in the list;
    }

    @Override
    public void insert(int pos, Object o) throws OutOfBoundaryException {
        if (pos < 0 || pos > size) {
            throw new OutOfBoundaryException("Out of bound");
        }
        if (size >= LISTLENGTH) {
            expandSpace();
        }
        if (size < LISTLENGTH) {
            for (int i = size; i > pos ; i--) { //19 17
                elements[i] = elements[i-1];
            }
            elements[pos] = o;
            size++;
        }
    }

    private void expandSpace() {
        Object[] temp = new Object[LISTLENGTH*2];
        LISTLENGTH = LISTLENGTH*2;
        for (int i = 0; i < elements.length; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    @Override
    public boolean insertBefore(Object o, Object e) throws ObjectNotFoundException {
        if (contains(o)) {
            insert(indexOf(o),e);
            return true;
        } else {
            throw new ObjectNotFoundException("object not found");
        }
    }

    @Override
    public boolean insertAfter(Object o, Object e) throws ObjectNotFoundException {
        if (contains(o)) {
            insert(indexOf(o)+1,e);
            return true;
        } else {
            throw new ObjectNotFoundException("Object not found");
        }
    }

    @Override
    public Object remove(int pos) throws OutOfBoundaryException {
        if (pos < 0 || pos >=size) {
            throw new OutOfBoundaryException("Out of bound");
        }
        else {
            for (int i = pos; i <size-1 ; i++) {
                elements[i] = elements[i+1];
            }
            elements[size-1] = null;
            size--;
            //after move, the last element points to null now.
            return elements[pos];
        }
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            remove(indexOf(o));
            size--;
            return true;
        }
        return false;
    }

    @Override
    public Object replace(int pos, Object e) throws OutOfBoundaryException {
        if (pos < 0 || pos >= size) {
            throw new OutOfBoundaryException("Out of bound");
        }
        Object temp = elements[pos];
        elements[pos] = e;
        return temp;
    }

    @Override
    public Object get(int pos) throws OutOfBoundaryException {
        if (pos < 0 || pos >= size) {
            throw new OutOfBoundaryException("Out of bound");
        }
        return  elements[pos];
    }

    @Override
    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i]+" ");
        }
        //System.out.println(Arrays.toString(elements));
    }

    public static void main(String[] args) throws ObjectNotFoundException {
        ListArray listArray = new ListArray(new IntegerStrategy());
        for (int i = 0; i < 20; i++) {
           listArray.insert(i,new Integer(i+1));
    }
        listArray.printList();
        listArray.remove(2);
        System.out.println();
        listArray.printList();
        System.out.println();
        System.out.println(listArray.getSize());
        System.out.println(listArray.indexOf(19));
        listArray.insertBefore(10,21);
        listArray.insert(17,21);
        listArray.insertAfter(20,25);
        System.out.println(listArray.contains(3));
        System.out.println(listArray.remove(new Integer(3)));
        listArray.printList();

    }
}
