package SwordToOffer.Ex49;

public class Solution {
    public boolean isNumeric(char[] str) {
        str = normalize(str);
        //如首尾是小数点,特判.
        if (str[0] == '.') {
            String s = new String(str);
            s = s.substring(1, s.length());
            str = s.toCharArray();
        }
        boolean result = check(str);
        if (!result) return false;
        //通过预检查和规范化工作.下面开始正式检查工作.
        String tmp = new String(str);
        int ePos = tmp.indexOf('e');
        int eNeg = tmp.lastIndexOf('e');

        int dotPos = tmp.indexOf('.');
        int dotNeg = tmp.lastIndexOf('.');

        int divPos = tmp.indexOf('-');
        int divNeg = tmp.lastIndexOf('-');
        if (divNeg != -1 && divPos != -1 && divPos != divNeg) return false; //出现了多个减号.

        int addPos = tmp.indexOf('+');
        int addNeg = tmp.lastIndexOf('+');
        if (addNeg != -1 && addPos != -1 && addPos != addNeg) return false; //出现了多个减号.

        if (ePos == -1 && (divPos != -1 || addPos != -1)) {
            return false;
        }

        //e和小数点都出现了两次,直接排除掉.
        if (ePos != -1 && eNeg != -1 && ePos != eNeg) return false;
        if (dotPos != -1 && dotNeg != -1 && dotPos != dotNeg) return false;
        //e和小数点都出现一次.他们两不能相邻.
        if (ePos != -1 && ePos == eNeg) {
            if (dotPos != -1 && dotPos == dotNeg) {
                if (Math.abs(ePos - dotPos) == 1 || dotPos > ePos) return false;
                if (dotPos + 1 < tmp.length() && Character.isDigit(tmp.charAt(dotPos + 1)) && divPos == -1 &&
                        ePos + 1 < tmp.length() && Character.isDigit(tmp.charAt(ePos + 1))) {
                    return true;
                } else if (dotPos + 1 < tmp.length() && Character.isDigit(tmp.charAt(dotPos + 1)) && divPos != -1 && divNeg == divPos && (ePos + 1 < tmp.length() && tmp.charAt(ePos + 1) == '-') &&
                        (ePos + 2 < tmp.length() && Character.isDigit(tmp.charAt(ePos + 2)))) {
                    return true;
                } else if (dotPos + 1 < tmp.length() && Character.isDigit(tmp.charAt(dotPos + 1)) && addPos == -1 &&
                        ePos + 1 < tmp.length() && Character.isDigit(tmp.charAt(ePos + 1))) {
                    return true;
                } else if (dotPos + 1 < tmp.length() && Character.isDigit(tmp.charAt(dotPos + 1)) &&
                        addPos != -1 && addNeg == addPos && (ePos + 1 < tmp.length() && tmp.charAt(ePos + 1) == '+') &&
                        (ePos + 2 < tmp.length() && Character.isDigit(tmp.charAt(ePos + 2)))) {
                    return true;
                } else {
                    return false;
                }
            } else { //dot没出现.需要确保e后面是数字.
                if ((addPos == -1 || divPos == -1) && (ePos + 1 < tmp.length() && Character.isDigit(tmp.charAt(ePos + 1)))) {
                    return true;
                } else if (divPos != -1 && divNeg == divPos && (ePos + 1 < tmp.length() && tmp.charAt(ePos + 1) == '-') &&
                        (ePos + 2 < tmp.length() && Character.isDigit(tmp.charAt(ePos + 2)))) {
                    return true;
                } else if (addPos != -1 && addNeg == addPos && (ePos + 1 < tmp.length() && tmp.charAt(ePos + 1) == '+') &&
                        (ePos + 2 < tmp.length() && Character.isDigit(tmp.charAt(ePos + 2)))) {
                    return true;
                } else {
                    return false;
                }
            }
        } else { //e没出现,出现了小数点.确保小数点后面有数字.
            if (dotPos + 1 < tmp.length() && Character.isDigit(tmp.charAt(dotPos + 1))) {
                return true;
            } else {
                return false;
            }
        }
    }

    //将str规范化.如果第一个是+/-号,将其去除掉.大写E统一换成小写.
    private char[] normalize(char[] str) {
        String tmp = new String(str);
        tmp = tmp.toLowerCase();
        if (tmp.charAt(0) == '+' || tmp.charAt(0) == '-') {
            tmp = tmp.substring(1, tmp.length());
        }
        return tmp.toCharArray();
    }

    //检查str是否函数数字,小数点,E之外的字符. 含有返回false,没有返回true.
    private boolean check(char[] str) {
        for (int i = 0; i < str.length; i++) {
            if (i == 0) {
                if (!Character.isDigit(str[i])) return false;
            } else {
                if (Character.isDigit(str[i]) || str[i] == '.' || str[i] == 'e' || str[i] == '-' || str[i] == '+') {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "1+23";
        System.out.println(new Solution().isNumeric(str.toCharArray()));

    }
}