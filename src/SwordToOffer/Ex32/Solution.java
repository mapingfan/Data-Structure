package SwordToOffer.Ex32;

import java.util.HashSet;

//把所有重复出现的字符筛选出来.
public class Solution {
    public int FirstNotRepeatingChar(String str) {
        HashSet<Character> help = new HashSet<>();
        HashSet<Character> filter = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (!filter.contains(str.charAt(i))) {
                filter.add(str.charAt(i));
            } else {
                help.add(str.charAt(i)); //重复出现的字符.
            }
        }
        //help中包含了所有出现的字符.现在只要再次扫描,找到第一个不在help中的字符即可.
        for (int i = 0; i < str.length(); i++) {
            if (!help.contains(str.charAt(i))) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().FirstNotRepeatingChar("aaaa"));
    }
}