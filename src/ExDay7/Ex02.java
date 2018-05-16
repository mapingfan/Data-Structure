package ExDay7;

import java.util.LinkedList;
import java.util.Stack;

/**
 *  * 求最长回文子串.
 *  * 暴力法.
 */
public class Ex02 {


    //写一下动态规划版本得了,动态规划就不写了

    /**
     * 对于给定字符串,考虑其最后一个字符:
     * 如果最长回文子串包含最后一个字符,那么问题转换为,以最后一个字符开始,往前找最长的回文子串.
     * 如果不包含最后一个字符,那么转换为前n-1个字符的最长回文子串问题.
     * 最后取两者最大值.即可.
     * 所以定义函数
     */

    private static int lqs_dp_wrap(String string) {
        return lqs_dp(string, string.length());
    }

    private static int lqs_dp(String string, int n) {
        if (n == 1) return 1;
        return Math.max(fun(string, n), lqs_dp(string, n - 1));
    }

    private static int lqs_array(String string) {
        if (string == null || string.length() < 1) return -1;
        if (string.length() < 2) return 1;
        int[] dp = new int[string.length()];
        dp[0] = 1; //第一个字符的最长回文串是1.
        int max = 1;
        for (int i = 1; i < string.length(); i++) {
            dp[i] = Math.max(dp[i - 1], help(string, i));
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //以end结尾的最长回文子串长度.
    private static int help(String string, int end) {
        for (int i = 0; i <= end; i++) {
            if (isPadromic(string, i, end)) return end - i + 1;
        }
        return 1;
    }

    //求以string第n个字符结尾的最长回文串.
    private static int fun(String string, int n) {
        for (int i = 0; i < string.length(); i++) {
            if (isPadromic(string, i, n - 1)) return n - i;
        }
        return 1;
    }


    private static int lps(String string) {
        int length = string.length();
        int max = 1;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, process(string, i));
        }
        return max;
    }

    //求在给定string中,以index位置为起始位置的最长回文子串,没有返回-1.
    private static int process(String string, int index) {
        //string的判断工作交给上层.index越界问题交给上层.
        for (int i = string.length() - 1; i >= index; i--) {
            if (isPadromic(string, index, i)) {
                return i - index + 1;
            }
        }
        return 1;
    }

    /**
     * 判断string中从index-end是不是回文.
     *
     * @param string
     * @param index
     * @param end
     * @return
     */
    private static boolean isPadromic(String string, int index, int end) {
        if (index == end) return true;
        if (string.charAt(index) != string.charAt(end)) return false;
        if (end - index == 1) return true;
        return isPadromic(string, index + 1, end - 1);
    }

    public static void main(String[] args) {
        String str = "ccbcabaabba";
        System.out.println(lps(str));
        System.out.println(lqs_dp_wrap(str));
        System.out.println(lqs_array(str));
    }
}
