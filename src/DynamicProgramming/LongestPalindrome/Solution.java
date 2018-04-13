package DynamicProgramming.LongestPalindrome;

/**
 * 寻找给定字符串中的最长回文连续子串.
 * 分析问题:
 * 首先理解什么是回文串.比如aba.正着读,反着读都一样,这样的字符串具有回文性质.
 * 对于一个字符串,长度为n,那么它的连续子串有多少呢?
 * 从1-n开开始,一共有n+n-1+....1 = (1+n)*n/2个.子序列(子集)有2^n个(包括空集).
 * 所以我们完全可以求出所有的子集,然后对其进行判断就好.
 * <p>
 * 问题需要一个算法,判断的当前串是否为回文串.
 * <p>
 * 补充一个动态规划版本实现:
 * 设l(i,j)表示当前字符串i-j是不是回文串.
 * 那么l(i,j)=true |a[i]==a[j]&&l(i+1,j-1)==true).
 * 不然一定不是回文串,l(i,j)=false.
 * 推到边界值:
 * l(i,i)=true. 并且i<=j.所以数组只用到一个上三角即可.别的不参与运算.
 */
public class Solution {


    /**
     * 待检测字符串str的,begin-end部分是否是回文的.包括首尾部分.
     * 这个判断方法似乎可以用递归解决.
     * f(i,j) = f(i+1,j-1) if s[i]==s[j]
     * else
     * 不是.
     */

    private static boolean check(String str, int begin, int end) {
        if (begin == end) {
            return true;
        }
        if (end - begin == 1) {
            if (str.charAt(begin) == str.charAt(end)) {
                return true;
            } else {
                return false;
            }
        }

        if (str.charAt(begin) == str.charAt(end) && begin < end) {
            return check(str, begin + 1, end - 1);
        } else {
            return false;
        }
    }


    private static int solution(String string) {
        int maxSize = 0;
        int currentSize = 0;
        int max_i = 0, max_j = 0; //用于记录下标.
        for (int i = 0; i < string.length(); i++) {
            for (int j = i; j < string.length(); j++) {
                for (int k = 0; k < string.length(); k++) {
                    if (check(string, i, k)) {
                        currentSize = k - i + 1;
                        if (currentSize > maxSize) {
                            maxSize = currentSize;
                            max_i = i;
                            max_j = k;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        System.out.println(string.substring(max_i, max_j + 1));
        return maxSize;
    }

    public static void main(String[] args) {
        String str = "detartrated";
        System.out.println(solution(str));

    }
}
