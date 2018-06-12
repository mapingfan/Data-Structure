package SwordToOffer.Ex43;
//numbers数组中的数字是否连续.0可以当作万能牌.
//先筛选出有几张万能牌.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 * 如果有相等的牌,一定不可以.
 * 万能牌有4张.一定可以.
 * 万能牌有3张.剩余的2张普通牌.看他们的差值<=4,那么一定可以.
 * 万能牌有2张.剩余的3张普通牌.
 * 万能牌有1张.剩余的4张普通牌.
 * 万能牌有0张.剩余的5张普通牌.直接得出结果.
 */

public class Solution {
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length < 5) return false;
        int countZero = 0;
        ArrayList<Integer> nonZero = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) countZero++;
            else nonZero.add(numbers[i]);
        }
        nonZero.sort(Comparator.comparingInt(o -> o));
        if (existEqualInNoZero(nonZero)) return false; //有相等的牌一定凑不出.
        boolean result = false;
        switch (countZero) {
            case 4:
                result = true;
                break;
            case 3:
                if (Math.abs(nonZero.get(0) - nonZero.get(1)) < 5) result = true;
                break;
            case 2:
                //考虑有两张万能牌.还是考虑相邻两个数的最大差值.和3出现的次数.最大差值为2出现的次数.
                Pair[] temp = findAdjacentMaxGapAndOccurrence(nonZero);
                if (temp == null) return false; //最大差值已经超过3了.不可能.后期优化代码.
                int gap3 = temp[0].occurrence;
                int gap2 = temp[1].occurrence;
                //最大差值3,2,1,出现的次数.

                if (gap3 >= 2) return false; //两个3gap.
                if (gap3 == 1) {
                    if (gap2 == 0) return true;
                    else return false;
                }
                if (gap3 == 0) {
                    return true;
                }
                break;
            case 1:
                //只有一张万能牌.求出相邻两个数的最大差值.并求出最大差值出现的次数.
                int[] tmp = findAdjacentMaxGap(nonZero);
                if ((tmp[0] == 2 && tmp[1] < 2) || tmp[0] == 1) result = true;
                break;
            case 0:
                result = process_0(nonZero);
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 最大差值3,3出现的次数,
     * 最大差值2,2出现的次数,
     * 最大差值1,1出现的次数,
     */
    private static class Pair {
        int maxGap;
        int occurrence;
    }

    private Pair[] findAdjacentMaxGapAndOccurrence(ArrayList<Integer> nonZero) {
        Pair[] pairs = new Pair[3];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair();
        }
        pairs[0].maxGap = 3;
        pairs[1].maxGap = 2;
        pairs[2].maxGap = 1;

        int[] result = findAdjacentMaxGap(nonZero);
        if (result[0] == 3) {
            pairs[0].occurrence = result[1];
            for (int i = 0; i < nonZero.size() - 1; i++) {
                if (Math.abs(nonZero.get(i) - nonZero.get(i + 1)) == 2) {
                    pairs[1].occurrence++;
                } else if (Math.abs(nonZero.get(i) - nonZero.get(i + 1)) == 1) {
                    pairs[2].occurrence++;
                }
            }
        } else if (result[0] == 2) {
            pairs[0].occurrence = 0;
            pairs[1].occurrence = result[1];
            for (int i = 0; i < nonZero.size() - 1; i++) {
                if (Math.abs(nonZero.get(i) - nonZero.get(i + 1)) == 1) {
                    pairs[2].occurrence++;
                }
            }

        } else if (result[0] == 1) {
            pairs[0].occurrence = 0;
            pairs[1].occurrence = 0;
            pairs[2].occurrence = result[1];
        } else {
            return null;
        }
        return pairs;
    }

    private int[] findAdjacentMaxGap(ArrayList<Integer> nonZero) {
        int maxGap = Integer.MIN_VALUE;
        int occur = 0;
        for (int i = 0; i < nonZero.size() - 1; i++) {
            maxGap = Math.max(maxGap, Math.abs(nonZero.get(i) - nonZero.get(i + 1)));
        }
        for (int i = 0; i < nonZero.size() - 1; i++) {
            if (Math.abs(nonZero.get(i) - nonZero.get(i + 1)) == maxGap) {
                occur++;
            }
        }
        return new int[]{maxGap, occur};
    }

    //存在相等的数字返回true,否则返回false.
    private boolean existEqualInNoZero(ArrayList<Integer> nonZero) {
        HashSet<Integer> help = new HashSet<>();
        for (Integer integer : nonZero) {
            if (!help.contains(integer)) help.add(integer);
            else return true;
        }
        return false;
    }

    //判断五个数字是不是连续的.
    private boolean process_0(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) + 1 != numbers.get(i + 1)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 2, 4, 5};
        System.out.println(new Solution().isContinuous(numbers));
    }
}