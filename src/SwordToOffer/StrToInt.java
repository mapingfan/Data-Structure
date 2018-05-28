package SwordToOffer;

import java.util.ArrayList;

/**
 * 将一个字符串转变为为整数.
 * 比如字符'b',转换整形是50.
 */
public class StrToInt {

    public static int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = preProcess(str); //预处理字符串,过滤掉不符合题意的字符.
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 10 + str.charAt(i) - '0';
        }
        return res;
    }

    /**
     * 预处理字符串.
     *
     * @param str
     * @return
     */
    private static String preProcess(String str) {
        /*StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (index < sb.length()) {
            if (!Character.isDigit(sb.charAt(index))) {
                sb.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return String.valueOf(sb);*/
        ArrayList<Character> help = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                help.add(str.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character character : help) {
            sb.append(character);
        }
        return String.valueOf(sb);
    }

    public static void main(String[] args) {
        String word = "";
        System.out.println(strToInt(word));

    }
}
