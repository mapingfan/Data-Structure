package SwordToOffer.Ex12;

public class Solution {
    public double Power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    //快速幂算法.
    public double Power_2(double base, int exponent) {
        int exp = Math.abs(exponent);
        double result = 1;
        if (exp == 0) {
            return 1;
        }
        if (exp == 1) {
            if (exponent < 0) {
                return 1.0D / base;
            }
            return base;
        }
        if (exp % 2 == 0) {
            result = Power_2(base, exp / 2);
            if (exponent < 0) return 1.0D / (result * result);
            return result * result;
        } else {
            result = Power_2(base, exp / 2);
            if (exponent < 0) return 1.0D / (result * result * base);
            return result * result * base;
        }
    }

    public double Power_3(double base, int exponent) {
        int exp = Math.abs(exponent);
        double result = 1;
        while (exp != 0) {
            if ((exp & 1) != 0) {
                result *= base;
            }
            exp = exp >> 1;
            base = base * base;
        }
        if (exponent < 0) {
            return 1 / result;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().Power_3(2, -3));
    }
//    90249.20303514441
}