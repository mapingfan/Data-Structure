package SortAlgorithm.InsertionSort;

import java.util.Arrays;

public class InsertionSort {
    /*
    插入排入算法,起先不是很理解这个算法的意思.我理解为直接拷贝一个数组,要排排序的元素往里插就可以.
    假设第一个有序,然后后面剩余的元素依次往里插入.这个地方插入有两个意思,一是直接找插入位置,然后元素往后
    挪动;另一种就是元素一路交换到合适的位置即可.这两种都实现了.
    这个算法比较简单,对已经排序的元素比较优,但是优化过的快排同样可以实现达到这个效果.
    这个算法用起来就有点鸡肋了.
     */
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
    /*
    插入排序的另一种实现思路:每次取出新元素,拿新元素同前一个元素比较,如果新元素大于前一个元素停止
    比较.比前面的元素小,则挪出一个空位置.最后留出的位置就是新元素插入的位置.
     */
    private static void insertionSortV3(int[] arr) {
        int len = arr.length;
        int j, i;
        for (i = 1; i < len; i++) {
            int temp = arr[i];  //取新元素
            for (j = i; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }


    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        insertionSortV3(arr);
        System.out.println(Arrays.toString(arr));

    }
}
