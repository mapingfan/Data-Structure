package ExDay8;

/**
 * 马拉车算法继续
 */
public class Ex01 {
    /**
     * 求以index为中心的最长回文子串长度.长度定义为字符的个数.
     * word字符串已经经过#化处理.
     * 返回长度,最小值是1.
     * <p>
     * 对于每个字符,向外拓宽,但是必须先预处理下,否则对于1221这种偶回文,拓展时候会出现错误.
     * 复杂度O(n^2)
     */

    public static String preProcess(String word) {
        //预处理字符串.
        String[] strings = word.split("");
        for (int i = 0; i < strings.length; i++) {
            strings[i] += "#";
        }
        word = "";
        for (int i = 0; i < strings.length; i++) {
            word += strings[i];
        }
        word = "#" + word;
        return word;
    }

    public static int help(String word, int index) {
        int left = index - 1, right = index + 1; //初始都指向index位置.
        while (left >= 0 && right < word.length()) {
            if (word.charAt(left) == word.charAt(right)) {
                left--;
                right++;
            } else {
                return (right - left - 1);
            }
        }
        return (right - left - 1);
    }

    //求word中的最长回文子串长度
    public static int lqs(String word) {
        word = preProcess(word);
        int max = 1;
        for (int i = 0; i < word.length(); i++) {
            max = Math.max(max, help(word, i));
        }
        return max / 2;
    }

    //上面是一个O(n^2)的解法,需要预处理字符串.并且是求出预处理后得最大直径,然后除以2.
    //下面学习manacher算法.

    /**
     * 马拉车算法
     * 还是基于扩充算法.
     */

    public static int manacher(String result) {
        result = preProcess(result);
        int[] radius = new int[result.length()];
        int rBound = -1; //回文右边界.
        int center = -1; //初始时,没有右边界和中心点.设置为-1.
        int max = 1;
        //设置radius数组的每一位.
        for (int i = 0; i < result.length(); i++) {
            radius[i] = rBound > i ? Math.min(rBound - i, radius[2 * center - i]) : 1;
            while (i + radius[i] < result.length() && i - radius[i] > -1) { //向外拓宽试试.
                if (result.charAt(i + radius[i]) == result.charAt(i - radius[i])) {
                    radius[i]++;
                } else {
                    break;
                }
            }
            if (i + radius[i] > rBound) {
                rBound = i + radius[i]; //更新边界.
                //设置这个边界的中心为center
                center = i;
            }
            max = Math.max(max, radius[i]);
        }
        return (2 * max - 1) / 2;
    }


    public static void main(String[] args) {
        String word = "acbdcbbbdbdaaccbcacdacdccababcddabddaaaaaaabdbabcdddaacabacbacbbdabdacddbbadaacbbdcbccacacdabcabacacbbbdcccacdcdcdcbcbabdcdacdddbbabcaccddddddabdacaabccdcabcbcbabacaaaccaccaddabbdadcdacdcdbaadbcabdcdcaaacbcadccbbddbaddcaddcaadcbbcbbdcbdadcddabdddcdbddbbdabaaddcaadd";
        System.out.println(lqs(word));
        System.out.println(manacher(word));
    }

}
