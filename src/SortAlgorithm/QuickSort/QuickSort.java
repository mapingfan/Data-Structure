package SortAlgorithm.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    static Random random = new Random();

    private static int adjustArray(int[] arr, int i, int j) {
        boolean flag = true;
        int base = arr[i];
        while (i < j) {
            if (flag) {
                if (arr[j] <= base) {
                    arr[i] = arr[j];
                    i++;
                    flag = false;
                } else {
                    j--;
                }
            } else {
                if (arr[i] >= base) {
                    arr[j] = arr[i];
                    j--;
                    flag = true;
                } else {
                    i++;
                }
            }
        }
        if (i == j) {
            arr[i] = base;
        }
        return i;
    }

    private static int adjustArrayV2(int[] arr, int i, int j, int k) {
        boolean flag = true;
        int base = arr[k];
        while (i < j) {
            if (flag) {
                if (arr[j] <= base) {
                    int temp = arr[j];
                    arr[j] = base;
                    arr[k] = temp;
                    k = j;
                    j--;
                    flag = false;
                } else {
                    j--;
                }
            } else {
                if (arr[i] >= base) {
                    int temp = arr[i];
                    arr[i] = base;
                    arr[k] = temp;
                    k = i;
                    i++;
                    flag = true;
                } else {
                    i++;
                }
            }
        }
        return k;
    }

    private static void qSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int index = adjustArray(arr, begin, end);
            qSort(arr, begin, index);
            qSort(arr, index + 1, end);
        }
    }

    private static void qSortV2(int[] arr, int begin, int end) {
        int i = random.nextInt(end) % (end - begin + 1) + begin;
        if (begin < end) {
            int index = adjustArrayV2(arr, begin, end, i);
            qSort(arr, begin, index);
            qSort(arr, index + 1, end);
        }
    }

    private static void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    private static void quickSortV2(int[] arr) {
        qSortV2(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
//        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int[] arr = {4, 2, 3, 5, 6, 1};
        System.out.println(Arrays.toString(arr));
        quickSortV2(arr);
        System.out.println(Arrays.toString(arr));
    }

}
