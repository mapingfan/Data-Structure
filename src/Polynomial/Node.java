package Polynomial;

class Node {
    int exponential; //每一项的指数；
    int coefficient; //每一项的指数；
    Node next; //指向下一项；

    public Node(int exponential, int coefficent) {
        this.exponential = exponential;
        this.coefficient = coefficent;
        next = null;
    }

    public Node() {
        exponential = coefficient = -1;
        next = null;
    }
}