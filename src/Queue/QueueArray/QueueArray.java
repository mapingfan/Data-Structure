package Queue.QueueArray;

import Queue.Exception.QueueEmptyException;
import Queue.Exception.QueueFullException;
import Queue.Interface.Queue;

/**
 * This queue use the assistant variable size to decide whether the queue is full or empty.
 */
public class QueueArray implements Queue {
    private Object[] elements;
    private int defaultSize = 8;
    private int front;
    private int rear;
    private int size;

    public int getDefaultSize() {
        return defaultSize;
    }

    public Object[] getElements() {
        return elements;
    }

    public QueueArray() {
        elements = new Object[defaultSize];
        front = rear = 0;
        size = 0;
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

    public boolean isFull() {
        if (size == defaultSize) {
            return true;
        }
        return false;
    }

    @Override
    public void enqueue(Object object) throws QueueFullException {
        if (isFull()) {
            throw new QueueFullException("The queue is full.");
        }
        elements[rear] = object;
        rear = (rear+1)%defaultSize;
        size++;
    }

    //expand the space of the queue;
    /*private void expandSpace() {
        Object[] tmp = new Object[defaultSize*2];
        for (int i = 0; i < defaultSize; i++) {
            tmp[i] = elements[i];
        }
        defaultSize *= 2;
        elements = tmp;
    }*/

    @Override
    public Object dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("The queue is empty.");
        }
        int tmp_front = front;
        front = (front+1)%defaultSize;
        size--;
        return elements[tmp_front];
    }

    @Override
    public Object peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("The queue is empty.");
        }
        return elements[front];
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public QueueArrayIterator iterator() {
        return new QueueArrayIterator(this);
    }

    public static void main(String[] args) {
        QueueArray queueArray = new QueueArray();
        System.out.println(queueArray.getSize());
        System.out.println(queueArray.isEmpty());
        System.out.println(queueArray.isFull());
        queueArray.enqueue(new Integer(99));
        queueArray.enqueue(new Integer(123));
        queueArray.enqueue(new Integer(3434));
        queueArray.enqueue(new Integer(34345));
        queueArray.dequeue();
        queueArray.enqueue(new Integer(343433));
        queueArray.dequeue();
        queueArray.dequeue();

        System.out.println(queueArray.isFull());
        System.out.println(queueArray.getSize());
        System.out.println(queueArray.getSize());
        System.out.println(queueArray.getFront());
        System.out.println(queueArray.getRear());
        QueueArrayIterator iterator = queueArray.iterator();
        while (iterator.hasNext()) {
            Object tmp = iterator.next();
            System.out.print(tmp+" ");
        }
    }


}
