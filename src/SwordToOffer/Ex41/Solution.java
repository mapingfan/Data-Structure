package SwordToOffer.Ex41;

public class Solution {
    public String LeftRotateString(String str, int n) {
        if (n == 0 || str == "") return str;
        if (n>str.length()) return "";
        StringBuffer sb = new StringBuffer(str);
        sb.delete(n, str.length()); //移位的结果.
        StringBuffer help = new StringBuffer(str);
        help.delete(0, n);
        return new String(help.append(sb));
    }

    public static void main(String[] args) {
        String str = "asdasd";
        System.out.println(new Solution().LeftRotateString(str, 10));
    }
}