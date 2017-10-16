package Stack.StackList;

public class StackNode {
    StackNode next;
    Object elemet;

    public StackNode() {
        next = null;
        elemet = null;
    }

    public StackNode(Object elemet) {
        next = null;
        this.elemet = elemet;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    public StackNode getNext() {
        return next;
    }

    public Object getElemet() {
        return elemet;
    }
}
