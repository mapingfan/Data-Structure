package SortAlgorithm.MergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    private static void merge(int[] arr, int[] tmp, int ls, int le, int rs, int re) {
        int index = ls;
        int copy_ls = ls;
        while (ls <= le && rs <= re) {
            if (arr[ls] > arr[rs]) {
                tmp[index++] = arr[rs++];
            } else {
                tmp[index++] = arr[ls++];
            }
        }
        while (ls <= le)
            tmp[index++] = arr[ls++];
        while (rs <= re) {
            tmp[index++] = arr[rs++];
        }
        System.arraycopy(tmp, copy_ls, arr, copy_ls, re - copy_ls + 1);
//        while (re >= 0) {
//            arr[re] = tmp[re];
//            re--;
//        }
    }

    private static void mSort(int[] arr, int[] tmp, int ls, int re) {
        int center = ls + (re - ls) / 2;
        if (ls < re) {
            mSort(arr, tmp, ls, center);
            mSort(arr, tmp, center + 1, re);
            merge(arr, tmp, ls, center, center + 1, re);
        }
    }

    private static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mSort(arr, tmp, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        long currentTimeMillis = System.currentTimeMillis();
        mergeSort(arr);
        System.out.println((System.currentTimeMillis() - currentTimeMillis) / 1000);
        for (int i = 0; i < arr.length; i++) {
            if (i % 10 == 0) {
                System.out.println();
            }
            System.out.print(" " + arr[i]);

        }
    }
}
