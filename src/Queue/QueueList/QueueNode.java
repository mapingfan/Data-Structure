package Queue.QueueList;

public class QueueNode {
    QueueNode next;
    Object element;

    public QueueNode() {
        next = null;
        element = null;
    }

    public QueueNode(Object object) {
        element = object;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }

    public QueueNode getNext() {
        return next;
    }

    public Object getElement() {
        return element;
    }
}
