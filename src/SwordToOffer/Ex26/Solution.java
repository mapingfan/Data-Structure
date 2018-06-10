package SwordToOffer.Ex26;


import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) return 0;
        if (array.length == 1) return array[0];
        TreeMap<Integer, Integer> help = new TreeMap<>();
        for (int i = 0; i < array.length; i++) {
            if (help.containsKey(array[i])) {
                Integer integer = help.get(array[i]) + 1;
                help.put(array[i], integer);
            } else {
                help.put(array[i], 1);
            }
        }
        int maxOccurrence = Integer.MIN_VALUE;
        int maxNum = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : help.entrySet()) {
            if (maxOccurrence < entry.getValue()) {
                maxOccurrence = entry.getValue();
                maxNum = entry.getKey();
            }
        }
//        System.out.print(maxOccurrence + " " + maxNum);
        return maxOccurrence > array.length / 2 ? maxNum : 0;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2, 1, 1, 1, 0, 3, 4};
        System.out.println(new Solution().MoreThanHalfNum_Solution(array));
    }
}