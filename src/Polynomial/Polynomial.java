package Polynomial;

/**
 *  设计一个类，计算两个多项式的加法和乘法；
 *  第一步，如何用计算机表示多项式；多项式对于人来说是具体的，但是对于电脑来说就是抽象的 ；
 *  我们需要找到一种方式，把多项式存储在电脑中；
 *  观察多项式P=3x^10+4x^5+10;每一项由系数和指数构成；我们把多项式的每一项看成一个结点；
 *  为此我们设计一个Node类表示每一项；
 */

public class Polynomial {
    Node head; //头指针;
    Node tail; //尾指针
    int size;

    public Polynomial() {
        head = new Node();
        tail = head;
        size = 0;
    }

    public void addNode(int coef, int exp) {
        Node node = new Node(exp,coef);
        node.next = null;
        tail.next = node;
        tail = node;
        size++;
    }

    public void printList() {
        Node work_next = head.next;
        while (work_next != null) {
            System.out.println(work_next.coefficient+" "+work_next.exponential);
            work_next = work_next.next;
        }
    }
}


