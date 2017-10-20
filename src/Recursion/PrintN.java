package Recursion;

public class PrintN {
    public static void main(String[] args) {
        printN(100);
    }

    public static void printN(int num) {
        if (num == 1) {
            System.out.println(num);
        } else {
            System.out.println(num);
            printN(num-1);
        }
    }
}
