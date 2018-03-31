package SortAlgorithm.RadixSort;

import java.util.Arrays;
import java.util.LinkedList;

public class RadixSort {
    static LinkedList<Integer>[] lists = new LinkedList[10];

    static {
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new LinkedList<>();
        }
    }

    private static int findMaxElement(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            } else {
                continue;
            }
        }
        return max;
    }

    private static int getDigits(int num) {
        int count = 0;
        if (num <= 9) {
            count = 1;
        } else {
            while (num != 0) {
                num /= 10;
                count++;
            }
        }
        return count;
    }

    private static int getDigitsV2(int num) {
        if (num < 10) {
            return 1;
        } else {
            return getDigitsV2(num / 10) + 1;
        }
    }

    /**
     * @param index 要获取第index位
     * @param num   待处理的数字.
     *              比如123,获取第2位,返回2.
     */
    private static int getDigitByIndex(int index, int num) {
        int digits = getDigitsV2(num);
        int result = 0;
        if (index > digits) {
            result = 0;
        } else {
            for (int i = 0; i < index; i++) {
                result = num % 10;
                num = num / 10;
            }
        }
        return result;
    }

    //    把list队列里的元素收集到数组中,收集完清空.
    private static void collect(LinkedList<Integer>[] lists, int[] arr) {
        int index = 0;
        for (int i = 0; i < lists.length; i++) {
            for (int i1 = 0; i1 < lists[i].size(); i1++) {
                arr[index++] = lists[i].get(i1);
            }
            lists[i].clear();
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr   待分配数组;
     * @param lists 队列数组;
     * @param index 把第index位分配到各个队列中.
     */
    private static void allocate(int[] arr, LinkedList<Integer>[] lists, int index) {
        for (int i = 0; i < lists.length; i++) {
            lists[i].clear();
        }
        for (int i : arr) {
            lists[getDigitByIndex(index, i)].add(i);
        }

    }

    private static void radixSort(int[] arr) {
        int maxNum = findMaxElement(arr);
        int maxDigits = getDigitsV2(maxNum);
        System.out.println(maxDigits);
        for (int i = 1; i <= maxDigits; i++) {
            allocate(arr, lists, i);
            collect(lists, arr);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
