package SortAlgorithm.InsertionSort;

import java.util.Arrays;

public class InsertionSort {

    private static void insertionSortV1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertionPosition = getInsertionPosition(arr, i - 1, arr[i]);
            if (insertionPosition == i) {
                continue;
            } else {
                int tmp = arr[i];
                for (int j = i; j >= insertionPosition + 1; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[insertionPosition] = tmp;
            }
        }
    }

    //寻找value在数组中的插入位置.
    private static int getInsertionPosition(int[] arr, int end, int value) {
        if (value <= arr[0]) {
            return 0;
        }
        if (value >= arr[end]) {
            return end + 1;
        }
        for (int i = 0; i <= end; i++) {
            if (value > arr[i] && value < arr[i + 1]) {
                return i + 1;
            } else {
                continue;
            }
        }
        return -1;
    }

    private static void insertionSortV2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] >= arr[j - 1]) {
                    break;
                } else {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    continue;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        insertionSortV2(arr);
        System.out.println(Arrays.toString(arr));

    }
}
