package SwordToOffer.Ex31;

public class Solution {
    public int GetUglyNumber_Solution(int index) {
        /*int i = 1;
        while (index > 0) {
            if (isUglyNum(i)) {
                index--;
                i++;
            } else {
                i++;
            }
        }
        return --i;*/

        if (index <= 0) return 0;
        int number = 0;

        int uglyFound = 0;

        while (uglyFound < index) {
            ++number;
            if (isUglyNum(number)) {
                ++uglyFound;
            }
        }
        return number;
    }

    private boolean isUglyNum(int i) {
        while (i % 2 == 0) i /= 2;
        while (i % 3 == 0) i /= 3;
        while (i % 5 == 0) i /= 5;
        return i == 1 ? true : false;

    }

    public static void main(String[] args) {
        System.out.println(new Solution().GetUglyNumber_Solution(14));
    }
}