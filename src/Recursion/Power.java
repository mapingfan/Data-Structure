package Recursion;

import java.awt.*;

/**
 * O(logn)实现求幂算法；
 */
public class Power {
    public static void main(String[] args) {
        System.out.println(casse(2, 5, 7));
    }

    public static int power(int x, int n) { //to simplify problem, assume x > 0;
        int result;
        if (n == 0) {
            result = 1;
        } else {
            result = power(x, n / 2);
            result = result * result;
            if (n % 2 == 1) {
                result = result * x;
            }
        }
        return result;
    }


    public static long powerV3(int x, int n) {
        long result = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                result = result * x;
            }
            n >>= 1;
            x = x * x;
        }
        return result;
    }


    /**
     * a^b mod c = (a mod c)^b mod c  快速取模算法.
     */

    public static long casse(int a, int b, int c) {
        if (a == 0) {
            return 0;
        }
        if (a > 0 && a < c) {
            return ((powerV3(a, b)) % c);
        }
        return casse(a % c, b, c);
    }

    public static int power_v2(int x, int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * x;

        }
        return result;
    }

}
