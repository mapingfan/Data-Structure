package SwordToOffer.Ex39;

import java.util.ArrayList;

/**
 * 分析:要求至少两个数字组成的连续序列和为sum. 那么考虑 1-sum/2.即可
 * 如果需要用到超过sum/2的序列,那么sum/2也包含了.肯定和超过了sum,不成立.
 * <p>
 * 暴力求解法:一共有n^2个序列,暴力判断每一个即可.
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= sum / 2 + 1; i++) {
            //以i位置开发探测.
            int res = search(i, sum);
            if (res != -1) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int j = i; j <= res; j++) {
                    list.add(j);
                }
                result.add(list);
            }
        }
        return result;
    }

    private int search(int i, int sum) {
        int curSum = i;
        int res = -1;
        for (int j = i + 1; j <= sum / 2+1; j++) {
            curSum += j;
            if (curSum == sum) {
                res = j;
                break;
            } else if (curSum > sum) {
                return -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> lists = new Solution().FindContinuousSequence(3);
        for (ArrayList<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}