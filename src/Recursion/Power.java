package Recursion;

import java.awt.*;

/**
 * O(logn)实现求幂算法；
 */
public class Power {
    public static void main(String[] args) {
        System.out.println(Power.power(2,5));
        System.out.println(Power.power_v2(2,5));
        System.out.println(Math.pow(2,5));
    }

    public static int power(int x, int n) { //to simplify problem, assume x > 0;
        /*if (n == 0) {
            return 1;
        } else {
            int tmp = power(x, n / 2);
            if (n % 2 == 0) {
                return tmp*tmp;
            } else {
                return tmp*tmp*x;
            }
        }*/
        int result;
        if (n == 0) {
            result = 1;
        } else {
            result = power(x,n/2);
            result = result*result;
            if (n % 2 == 1) {
                result = result*x;
            }
        }
        return result;
    }

    public static int power_v2(int x, int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result*x;

        }
        return result;
    }
}
