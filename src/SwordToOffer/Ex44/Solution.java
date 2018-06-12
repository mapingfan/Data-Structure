package SwordToOffer.Ex44;

import java.util.LinkedList;

/**
 * 约瑟夫问题,这次用数据模拟解决.
 * <p>
 * 0-n-1个人.
 * 从0开始报数,报到m-1出去.
 */


public class Solution {
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0) return -1;
        int s = 0;
        for (int i = 2; i <= n; i++) {
            s = (s + m) % i;
        }
        return s;
    }
}
  /*public int LastRemaining_Solution(int n, int m) {
        boolean[] flag = new boolean[n];
        int left = n;  //left表示剩余的人.
        int count = 0, i;
        while (left != 1) {
            for (i = 0; i < n; i++) {
                if (!flag[i]) {
                    if (count == m - 1) {
                        flag[i] = true;
                        count = -1;
                        left--;
                        if (left == 1) break;
                    }
                    count++;
                }
            }
        }
        for (i = 0; i < flag.length; i++) {
            if (!flag[i]) break;
        }
        return i;
    }*/
