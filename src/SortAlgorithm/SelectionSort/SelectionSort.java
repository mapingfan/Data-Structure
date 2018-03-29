package SortAlgorithm.SelectionSort;

import java.util.Arrays;

public class SelectionSort {
    /**
     * 选择排序算法:每次从从未排序的数组中选出最小值.
     * 最朴素的选择,每次选出最小值即可.但是这个地方可以稍微优化下,不要进行多余的交换操作,
     * 不要每次找到arr[i] > arr[j]就进行交换.而且只记录最小的位置,只交换一次即可.
     * 如何记录最新的位置成为核心所在.参加V2,V3版本.
     */
    private static void selectionSortV1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                } else {
                    continue;
                }
            }
        }
    }

    /**
     * 小优化操作,上面的V1版会进多余的交换操作.其实只需要记录最小值的位置即可.
     *  考虑arr[0]的后面有数个比arr[0]小的数,如何机智的选择出最最小的那个呢?
     *  这个地方是提供了两种思路,一是用value=arr[0]的值,每次找到小的就更新value,
     *  那么最后的value一定是最小的,更新value的时候同时记下最小值的位置即可.
     *  最后可能不用交换,所以判断下min和i值,然后操作.
     */
    private static void selectionSortV2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            int value = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (value > arr[j]) {
                    min = j;
                    value = arr[j];
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    /**
     * 另一个更好的思路,只需要设置一个变量记录最小值的位置即可.切记不能进行多余的交换.
     * 找到比arr[min]小的,就把min=j;这样,最后找到的min一定是最小值的位置.
     */
    private static void selectionSortV3(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 0, 9, 3, 12, 7, 8, 3, 4, 65, 22};
        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));
        System.out.println("排序后:");
        selectionSortV3(arr);
        System.out.println(Arrays.toString(arr));
    }
}
