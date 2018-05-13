package ExDay5;

import java.util.Arrays;
import java.util.List;

/**
 * 字典序拼接最小.
 * 给一些字符串,要求拼接在一起,使得字典序最小.
 * <p>
 * 贪心策略如下:两个字符串,A,B. 如果AB的字典序小于BA的字典序,那么在排序时候,A在B前面.
 * 错误的贪心策略,只考虑每个字符串的贪心策略.
 * 只要把这些字符串按照正确的贪心策略排完一遍就得到最小字典序序列.
 * 关键是选择合适的贪心策略.
 */
public class Ex04 {

    public static String solution(List<String> strings) {
        if (strings == null) return null;
        strings.sort((str1, str2) -> (str1 + str2).compareTo(str2 + str1));
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return new String(sb);
    }

    public static void main(String[] args) {
//        List<String> strings = Arrays.asList("jibw", "ji", "jp", "bw", "jibw");
        List<String> strings = Arrays.asList("ba", "ab");
        System.out.println(solution(strings));
    }

}
