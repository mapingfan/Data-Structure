package SwordToOffer.Ex59;

import java.util.ArrayList;
import java.util.Comparator;

public class Solution {
    ArrayList<Integer> data = new ArrayList<>();

    public void Insert(Integer num) {
        data.add(num);
        data.sort(Comparator.comparingInt(o -> o));
    }

    public Double GetMedian() {
        int size = data.size();
        if (size % 2 == 0) {
            return Double.valueOf((double) (data.get(size / 2) + (double) data.get(size / 2 - 1)) / 2);
        } else {
            return Double.valueOf(data.get(size / 2));
        }
    }


}