package Queue.QueueArray;

import Queue.Interface.Iterator;

public class QueueArrayIterator implements Iterator {
    private QueueArray queueArray;
    private int current;
    private boolean flag = true;
    private int cnt = 1;

    public QueueArrayIterator(QueueArray queueArray) {
        this.queueArray = queueArray;
        current = queueArray.getFront() - 1;
    }

    @Override
    public Object next() {
            current = (current + 1) % queueArray.getDefaultSize();
            return queueArray.getElements()[current];
    }

    @Override
    public boolean hasNext() {
        if (queueArray.isFull()) {
            if ((current + 1) % queueArray.getDefaultSize() >= queueArray.getRear() && flag) {
                if (cnt++ == queueArray.getDefaultSize()) {
                    flag = false;
                }
                return true;
            } else {
                return false;
            }

        } else {
            if ((current + 1) % queueArray.getDefaultSize() != queueArray.getRear()) {
                return true;
            }
            return false;
        }

    }
}
