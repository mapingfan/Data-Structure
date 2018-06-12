package SwordToOffer.Ex46;

public class Solution {
    public int StrToInt(String str) {
        //check是否合法.
        if (!check(str)) return 0;
        int result;
        if (str.charAt(0) == '+') {
            result = cal(str, 1);
        } else if (str.charAt(0) == '-') {
            result = cal(str, 1);
            result *= -1;
        } else {
            result = cal(str, 0);
        }
        return result;
    }

    private int cal(String str, int i) {
        int result = 0;
        for (int j = i; j < str.length(); j++) {
            result = result * 10 + (str.charAt(j) - '0');
        }
        return result;
    }

    private boolean check(String str) {
        //检查str是否合法.
        if (str == null || str.length() == 0 || str.trim().isEmpty()) return false; //空不合法,返回false.
        if (str.charAt(0) == '+') {
            //检查后面的位.
            if (!checkLeftString(str)) return false;
        } else if (str.charAt(0) == '-') {
            if (!checkLeftString(str)) return false;
        } else if (Character.isDigit(str.charAt(0))) {
            if (!checkLeftString(str)) return false;
        } else {  //首位字母不是加号或者减号或者数字.
            return false;
        }
        return true;
    }

    private boolean checkLeftString(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().StrToInt(""));
//        System.out.println('3' - '0');
    }
}