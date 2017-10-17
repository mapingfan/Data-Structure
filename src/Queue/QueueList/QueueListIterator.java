package Queue.QueueList;

import Queue.Interface.Iterator;

public class QueueListIterator implements Iterator {
    private QueueList queueList;
    private QueueNode current;

    public QueueListIterator(QueueList queueList) {
        this.queueList = queueList;
        current = queueList.getHead();
    }

    @Override
    public Object next() {
        if (hasNext()) {
            current = current.getNext();
            return current.getElement();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (!queueList.isEmpty()) {
            if (current.getNext() != null) {
                return true;
            }
            return false;
        }
        return false;
    }
}
