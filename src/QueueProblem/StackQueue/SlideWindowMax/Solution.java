package QueueProblem.StackQueue.SlideWindowMax;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 滑动窗口最大值问题.
 */
public class Solution {
    /**
     * @param array 存放数字.
     * @param w     滑动窗口大小.
     * @return 返回最大值.
     */
    private static int solution(LinkedList<Integer> array, int w) {
        int[] value = new int[array.size() - w + 1];
        for (int i = 0; i < array.size() - w + 1; i++) {
            value[i] = max(array, i, w);
        }
        Arrays.sort(value);
        return value[value.length - 1];
    }

    private static int max(LinkedList<Integer> array, int i, int w) {
        int max = array.get(i);
        for (int j = i + 1; j < i + w; j++) {
            if (max < array.get(j)) {
                max = array.get(j);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        LinkedList<Integer> array = new LinkedList<>();
        array.add(1);
        array.add(3);
        array.add(-1);
        array.add(-3);
        array.add(5);
        array.add(3);
        array.add(6);
        array.add(7);
        System.out.println(solution(array, 3));
    }
}
