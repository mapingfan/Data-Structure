package Queue.QueueList;

import Queue.Exception.QueueEmptyException;
import Queue.Interface.Queue;

public class QueueList implements Queue {
    private QueueNode head;
    private QueueNode front;
    private QueueNode rear;
    private int size;

    public QueueList() {
        head = new QueueNode();
        front = rear = head;
        size = 0;
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
    public void enqueue(Object object) {
        QueueNode tmp_node = new QueueNode(object);
        tmp_node.setNext(null);
        rear.setNext(tmp_node);
        rear = tmp_node;
        if (isEmpty()) { //mind the situation when first element enter the queue. make sure the front = rear;
            front = rear;
        }
        size++;
    }

    @Override
    public Object dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("The queue is empty.");
        }
        head.setNext(front.getNext());
        QueueNode tmp = front;
        front = head.getNext();
        size--;
        return tmp.getElement();
    }

    @Override
    public Object peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("The queue is empty.");
        }
        return front.getElement();
    }

    public QueueNode getHead() {
        return head;
    }

    public QueueListIterator iterator() {
        return new QueueListIterator(this);
    }

    public static void main(String[] args) {
        QueueList queueList = new QueueList();
        System.out.println(queueList.getSize());
        System.out.println(queueList.isEmpty());
        queueList.enqueue(new Integer(19));
        queueList.enqueue(new Integer(18));
        queueList.enqueue(new Integer(120));
        //System.out.println(queueList.getSize());
       QueueListIterator iterator = queueList.iterator();
       while (iterator.hasNext()) {
           Object tmp = iterator.next();
           System.out.print(tmp+" ");
       }
    }
}
