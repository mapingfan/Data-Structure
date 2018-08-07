package Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class CountingSort {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] array = generateArray(500, 100);
        System.out.println("Before Sorting -->" + Arrays.toString(array));
        counting_sort(array);
        System.out.println("After Sorting -->" + Arrays.toString(array));
    }


    private static int[] generateArray(int maxValue, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxValue + 1);
        }
        return array;
    }

    public static void counting_sort(int[] array) {
        int[] maxAndMin = getMaxAndMin(array);
        int max = maxAndMin[1];
        int[] buckets = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            buckets[array[i]]++;
        }
        //进行收集,为了简化,直接先用LinkedList中转下数据.
        LinkedList<Integer> help = new LinkedList<>();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != 0) { //往linkedList里面插入buckets[i]次i值.
                for (int j = 0; j < buckets[i]; j++)
                    help.add(i);
            }
        }
        //把链表的值插回数组中.
        int index = 0;
        for (Integer num : help)
            array[index++] = num;
    }

    private static int[] getMaxAndMin(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(array[i], max);
            min = Math.min(array[i], min);
        }
        return new int[]{min, max};
    }
}
