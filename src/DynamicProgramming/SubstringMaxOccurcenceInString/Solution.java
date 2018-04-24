package DynamicProgramming.SubstringMaxOccurcenceInString;

/**
 * 问题:
 * 两个字符串,T,S ,求T在S中出现的次数,不要求连续出现.
 * <p>
 * 分析:
 * 考虑降这个问题拆解为子问题考虑,
 * 假设,T中有m个字幕,S中有n个字符.
 * 那么如果T[m-1]!=S[n-1]那么问题会转化吗?
 * 设计算出现次数的函数为f(m,n) = f(m,n-1)
 * 即,S的前n-1个字符中,T的m个字符出现的次数,此时很简单.
 * 如果T[m-1]==S[n-1]呢?
 * 此时f(m,n) = f(m-1,n-1),意思就是T的前m-1个字符在S的前n-1个字符出现的次数嘛,这个很好解释?
 * 但是这个是唯一的情况嘛?
 * 考虑出现这种情况,可能会有T的m个字符直接出现在S的n-1个字符中嘛?
 * 这个是有可能的.必须考虑这种情况.
 * 所以最后的函数可以列成这样:
 * f(m,n) = f(m,n-1) 最后一个不等.
 * f(m,n) = f(m-1,n-1)+f(m,n-1),最后一个相等.
 * <p>
 * 下面退到下边界条件.
 * 大前提,m<=n
 * n = 1时,即S中只有一个字符.
 * f(m,n) ->可以推倒出常熟值.
 * 如果m=1,那么问题也简单.
 * 此处也不考虑空串,空串算不算出现,依赖于题目的定义.
 * 即S,T中至少都有一个字符.
 * 下面编码实现.
 */
public class Solution {

    //统计T在S中出现的次数,S,T都不能为空.
    //且T的长度不能大于Ｓ的长度．

    /**
     * @param S
     * @param T
     * @param m S的长度.
     * @param n T的长度.
     * @return
     */
    private static int count(String S, String T, int m, int n) {
        if (n > m || m == 0 || n == 0) {
            return 0;
        } else {
            if (m == 1 && n == 1) {
                if (S.charAt(m - 1) == T.charAt(n - 1)) {
                    return 1;
                } else {
                    return 0;
                }
            }
            if (n == 1 && m > 1) {
                int count = 0;
                for (int i = 0; i < m; i++) {
                    if (S.charAt(i) == T.charAt(n - 1)) {
                        count++;
                    }
                }
                return count;
            }
            if (S.charAt(m - 1) == T.charAt(n - 1)) {
                return count(S, T, m - 1, n - 1) + count(S, T, m - 1, n);
            } else {
                return count(S, T, m - 1, n);
            }
        }
    }

    private static int dp_count(String S, String T) {
        int[][] dp = new int[S.length()][T.length()];//一维代表S的长度,二维代表T的长度. S是大串,T是小串.
        //        //        //考虑dp[0][i] /dp[i][0].边界值处理.
        if (S.charAt(0) == T.charAt(0)) {
            dp[0][0] = 1;
        }
        for (int i = 0; i < S.length(); i++) {
            dp[i][0] = calc(S, T, i);
        }
        for (int i = 1; i < S.length(); i++) {
            for (int j = 1; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[S.length() - 1][T.length() - 1];
    }

    private static int calc(String s, String t, int i) {
        int count = 0;
        for (int j = 0; j <= i; j++) {
            if (s.charAt(j) == t.charAt(0)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String S = "abadcb";
        String T = "ab";
        System.out.println(count(S, T, S.length(), T.length()));
        System.out.println(dp_count(S, T));
    }


}