package SwordToOffer.Ex07;

public class Solution {
    public int Fibonacci(int n) {
        if (n < 0) return -1;
        int[] fib = new int[40];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i < 40; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}