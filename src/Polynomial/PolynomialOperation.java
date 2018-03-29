package Polynomial;

import Polynomial.Polynomial;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * p1 = 3x^5+4x^2+12;
 * p2 = 4x^3+12x+9;
 * p1+p2 = 3 5 4 3 4 2 12 1 21 0
 */
public class PolynomialOperation {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        /*p1,p2添加项*/
        p1.addNode(3,5);
        p1.addNode(4,2);
        p1.addNode(12,0);

        p2 .addNode(4,3);
        p2.addNode(12,1);
        p2.addNode(9,0);
        Polynomial p = addPolynomial(p1,p2);
        p.printList();
        Polynomial p3 = multiplyPolynomial(p1,p2);
        p3.printList();

    }

    public static Polynomial addPolynomial(Polynomial p1, Polynomial p2) {
        Node p1_work_next = p1.head.next;
        Node p2_work_next = p2.head.next;
        Polynomial p3 = new Polynomial(); //用于存放相加的结果；
        while (p1_work_next != null && p2_work_next != null) {
            if (p1_work_next.exponential > p2_work_next.exponential) {
                p3.addNode(p1_work_next.coefficient,p1_work_next.exponential);
                p1_work_next = p1_work_next.next;
            } else if (p1_work_next.exponential < p2_work_next.exponential) {
                p3.addNode(p2_work_next.coefficient, p2_work_next.exponential);
                p2_work_next = p2_work_next.next;
            } else {
                p3.addNode(p1_work_next.coefficient+p2_work_next.coefficient,p1_work_next.exponential);
                p1_work_next = p1_work_next.next;
                p2_work_next = p2_work_next.next;
            }
        }
        if (p1_work_next == null) {
            while (p2_work_next != null) {
                p3.addNode(p2_work_next.coefficient,p2_work_next.exponential);
                p2_work_next = p2_work_next.next;
            }
        }
        if (p2_work_next == null) {
            while (p1_work_next != null) {
                p3.addNode(p1_work_next.coefficient,p1_work_next.exponential);
                p1_work_next = p1_work_next.next;
            }
        }
        p1 = p3;
        return p1;
    }

    public static Polynomial multiplyPolynomial(Polynomial p1, Polynomial p2) {
        Node p1_work_next = p1.head.next;
        Node p2_work_next = p2.head.next;
        Polynomial polynomial;
        if (p1_work_next == null || p2_work_next == null) {
            polynomial = new Polynomial();
            polynomial.addNode(0,0);
            return polynomial;
        }
        int p1_size = p1.size; //p1中结点的个数；
        Polynomial[] polynomials = new Polynomial[p1_size];
        for (int i = 0; i < p1_size; i++) {
            polynomials[i] = new Polynomial();
            while (p2_work_next != null) {
                polynomials[i].addNode(p1_work_next.coefficient*p2_work_next.coefficient,p1_work_next.exponential+p2_work_next.exponential);
                p2_work_next = p2_work_next.next;
            }
            p1_work_next = p1_work_next.next;
            p2_work_next = p2.head.next;

        }
        //合并polynomials数组；
        for (int i = 1; i < p1_size; i++) {
            polynomials[0] = addPolynomial(polynomials[0],polynomials[i]);
        }
        polynomial = polynomials[0];
        return polynomial;
    }

}
