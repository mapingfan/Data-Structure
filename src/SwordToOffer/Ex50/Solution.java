package SwordToOffer.Ex50;

import java.util.HashSet;

public class Solution {
    StringBuffer sb = new StringBuffer();

    //Insert one char from stringstream
    public void Insert(char ch) {
        sb.append(ch);
    }


    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        //找出sb中第一个只出现一次的字符.
        HashSet<Character> help = new HashSet<>();
        HashSet<Character> filter = new HashSet<>();
        for (int i = 0; i < sb.length(); i++) {
            if (!help.contains(sb.charAt(i))) {
                help.add(sb.charAt(i));
            } else {
                filter.add(sb.charAt(i));
            }
        }
        for (int i = 0; i < sb.length(); i++) {
            if (!filter.contains(sb.charAt(i))) {
                return sb.charAt(i);
            }
        }
        return '#';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.Insert('g');
        solution.Insert('o');
        solution.Insert('o');
        solution.Insert('g');
        solution.Insert('l');
        solution.Insert('l');
        solution.Insert('e');
        System.out.println(solution.FirstAppearingOnce());
    }
}