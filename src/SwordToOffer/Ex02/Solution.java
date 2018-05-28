package SwordToOffer.Ex02;

import java.util.ArrayList;

/**
 * 将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Solution {
   /* public String replaceSpace(StringBuffer str) {
        if (str == null || str.length() == 0) return String.valueOf(str);
        ArrayList<String> help = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isSpaceChar(str.charAt(i))) {
                help.add("%20");
            } else {
                help.add(str.charAt(i) + "");
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String tmp : help) {
            sb.append(tmp);
        }
        return String.valueOf(sb);
    }*/
   public String replaceSpace(StringBuffer str) {
       return str.toString().replaceAll(" ", "%20");
   }
}
