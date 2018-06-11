package SwordToOffer.Ex30;

import java.util.ArrayList;

//数字拼接,拼出最小数字.
//寻找合适的贪心策略.
public class Solution {
    public static String PrintMinNumber(int[] numbers) {
        StringBuffer sb = new StringBuffer();
        //对数组进行排序,按照这样的策略进行.
        ArrayList<String> help = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            help.add(String.valueOf(numbers[i]));
        }
        help.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        for (String str : help) {
            sb.append(str);
        }
        return new String(sb);
    }

    public static void main(String[] args) {
        int[] array = {3, 32, 321};
        System.out.println(PrintMinNumber(array));
    }
}