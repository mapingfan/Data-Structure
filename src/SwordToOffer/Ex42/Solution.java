package SwordToOffer.Ex42;

public class Solution {
    public String ReverseSentence(String str) {
        if (str == null||str==""||str.trim().isEmpty()) return str;
        String[] strings = str.split(" ");
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length-1) result += process(strings[i]);
            else result += process(strings[i]) + " ";
        }
        return process(result);
    }

    private String process(String string) {
        String tmp = "";
        for (int index = string.length()-1; index >= 0; index--) {
            tmp += string.charAt(index);
        }
        return tmp;
    }

    public static void main(String[] args) {
        String str = "    ";
        System.out.println(str.trim().isEmpty());
        System.out.print(new Solution().ReverseSentence(str));
        System.out.print("..");
    }
}