package SwordToOffer.Ex13;

import java.util.ArrayList;
public class Solution {
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0) return;
        ArrayList<Integer> odd = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) even.add(array[i]);
            else odd.add(array[i]);
        }
        for (int i = 0; i < odd.size(); i++) {
            array[i] = odd.get(i);
        }
        for (int i = odd.size(); i < odd.size() + even.size(); i++) {
            array[i] = even.get(i - odd.size());
        }
    }
}