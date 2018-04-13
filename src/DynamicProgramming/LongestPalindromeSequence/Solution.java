package DynamicProgramming.LongestPalindromeSequence;

/**
 * 问题: 求最长回文序列长度,不要求连续,只要能回文就好.
 * 分析:
 * 设l(i,j)为当前i-j的最长回文序列长度.
 * 那么l(i,j) = l(i+1,j-1)+1,s[i]==s[j];
 * 两个否则取最大值.
 * 否则l(i,j) = l(i,j-1)
 * 否则l(i,j) = l(i+1,j)
 * if(i==j) - >1.
 * i<=j.
 */
public class Solution {
    //i,j能取到.
    private static int solution(String str, int i, int j) {
        if (i == j) {
            return 1;
        }
        if (j - i == 1) {
            if (str.charAt(i) != str.charAt(j)) {
                return 0;
            } else {
                return 1;
            }
        }
        if (str.charAt(i) == str.charAt(j)) {
            return solution(str, i + 1, j - 1) + 2;
        } else {
            return Math.max(solution(str, i, j - 1), solution(str, i + 1, j));
        }

    }

    public static void main(String[] args) {
        String str = "agctcbmaactggam";
        System.out.println(solution(str, 0, str.length() - 1));
    }
}