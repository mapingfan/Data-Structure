package SwordToOffer.Ex25;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Solution {
    public ArrayList<String> Permutation(String str) {
        if (str == null) return null;
        if (str.length() == 0) return new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        Permutation(str.toCharArray(), 0, result);
        Collections.sort(result);
        return result;
    }

    //去重合全排列.
    private void Permutation(char[] str, int start, ArrayList<String> result) {
        HashSet<Character> help = new HashSet<>();
        if (start == str.length) {
            result.add(new String(str));
            return;
        }
        for (int i = start; i < str.length; i++) {
            if (!help.contains(str[i])) {
                help.add(str[i]);
                swap(str, start, i);
                Permutation(str, start + 1, result);
                swap(str, start, i);
            }
        }
    }

    //
    private void swap(char[] str, int start, int i) {
        char tmp = str[start];
        str[start] = str[i];
        str[i] = tmp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<String> result = solution.Permutation("abc");
        for (String str : result) {
            System.out.print(str + " ");
        }
    }
}