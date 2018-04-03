package Recursion;

public class InverseString {
    /**
     * @param str 输入的字符串
     * @param size 字符串的长度.
     */
    private static void inverseString(String str, int size) {
        if (size > 0) {
            System.out.print(str.substring(size - 1, size));
            inverseString(str, size - 1);
        }
    }

    /**
     * @param str 待逆转的字符串
     * @param begin 字符串的起始下表 0
     * @param end   字符串末尾下标 str.length()-1;
     */
    private static void inverseStringV2(String str, int begin, int end) {
        int center = begin + (end - begin) / 2;
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i <= center; i++) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(end - i));
            sb.setCharAt((end - i), temp);
        }
        System.out.println(sb);
    }


    public static void main(String[] args) {
        String str = "catf";
        inverseString(str, str.length());
        inverseStringV2(str, 0, str.length() - 1);

    }
}
