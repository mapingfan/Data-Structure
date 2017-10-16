package Queue.QueueArray;

import Queue.Interface.Iterator;

public class QueueArrayIterator implements Iterator {
    private QueueArray queueArray;
    private int current;
    private boolean flag = true;

    public QueueArrayIterator(QueueArray queueArray) {
        this.queueArray = queueArray;
        current = queueArray.getFront()-1;
    }

    @Override
    public Object next() {
            current = (current+1)%queueArray.getDefaultSize();
            return queueArray.getElements()[current];
    }

    @Override
    public boolean hasNext() {
            if ((current + 1) % queueArray.getDefaultSize() >= queueArray.getRear()&&flag) {
                if ((current + 1)==queueArray.getDefaultSize()&&(current + 1) % queueArray.getDefaultSize() == queueArray.getRear()){
                    flag = false;
                }
                return true;
            }
            return false;
    }
}
