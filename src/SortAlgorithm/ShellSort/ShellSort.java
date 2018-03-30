package SortAlgorithm.ShellSort;

import java.util.Arrays;

public class ShellSort {

    private static void shellSort(int[] arr) {
        int len = arr.length;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < len; i++) {
                for (int j = i; j < len; j += gap) {
                    for (int k = j + gap; k > i && k < len; k -= gap) {
                        if (arr[k] > arr[k - gap]) {
                            break;
                        } else {
                            int temp = arr[k];
                            arr[k] = arr[k - gap];
                            arr[k - gap] = temp;
                        }
                    }
                }
            }
        }
    }

    private static void shellSortV2(int[] arr) {
        int len = arr.length;
        int j;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int temp = arr[i];
                for (j = i; j >= gap && temp < arr[j - gap]; j -=gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }


    public static void main(String[] args) {
        //int[] arr = {49, 38, 65, 97, 26, 13, 27, 49, 55, 4};
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        shellSortV2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
