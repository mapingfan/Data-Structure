package SwordToOffer.Ex11;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class Solution {
    public int NumberOf1(int n) {
        String tmp = Integer.toBinaryString(n);
        int cnt = 0;
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == '1') {
                cnt++;
            }
        }
        return cnt;
    }

    public int NumberOf1_2(int n) {
        int cnt = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                cnt++;
            }
            flag <<= 1;
        }
        return cnt;
    }

    public int NumberOf1_3(int n) {
        int cnt = 0;
        while (n != 0) {
            if ((n & 1) != 0) {
                cnt++;
            }
            n >>>= 1;
        }
        return cnt;
    }


    public int NumberOf1_4(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n = n & (n - 1); //每次消去右边一个1.
        }
        return cnt;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.NumberOf1_4(-10));
        System.out.println(Integer.toBinaryString(-9));
        System.out.println(Integer.toBinaryString(-10));

        System.out.println(Integer.toBinaryString(-9 & -10));
    }
}