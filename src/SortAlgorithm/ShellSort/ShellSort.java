package SortAlgorithm.ShellSort;

import java.util.Arrays;

import static java.lang.Math.pow;

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
        int len = arr.length, j;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) { //类比插排从第一个元素开始,希尔从第gap个开始.
                int temp = arr[i];
                for (j = i; j >= gap && temp < arr[j - gap]; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    /*
    换一个增量序列的实现,采用塞得克维奇的序列实现下.
     */
    private static void shellSortV3(int[] arr) {
        int i = 0, j, k;
        while (arr.length > (9 * (int) Math.pow(4, i) - 9 * (int) pow(2, i) + 1)) {
            i++;
        }
        int index = i - 1;
        int gap = 9 * (int) Math.pow(4, index) - 9 * (int) pow(2, index) + 1;
        while (gap > 0 && index >= 0) {
            for (j = gap; j < arr.length; j++) {
                int temp = arr[j];
                for (k = j; k >= gap && temp < arr[k - gap]; k -= gap) {
                    arr[k] = arr[k - gap];
                }
                arr[k] = temp;
            }
            index--;
            gap = 9 * (int) Math.pow(4, index) - 9 * (int) pow(2, index) + 1; //gap最后一定为1
        }
    }


    public static void main(String[] args) {
        //int[] arr = {49, 38, 65, 97, 26, 13, 27, 49, 55, 4};
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        shellSortV3(arr);
        System.out.println(Arrays.toString(arr));
    }
}
