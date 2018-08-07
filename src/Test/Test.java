package Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Test {

    public static void selection_sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j); //交换i,j.
                }
            }
        }
    }

    public static void insertion_sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    public static int result = 0;

    public static int small_sum(int[] arr) {
        merge_sort(arr);
        return result;
    }

    public static void merge_sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        merge_sort(arr, 0, arr.length - 1);
    }

    public static void merge_sort(int[] arr, int begin, int end) {
        if (begin >= end) return; //重点关注递归出口条件,一定是>=,而不是>.
        int mid = begin + (end - begin) / 2;
        merge_sort(arr, begin, mid);
        merge_sort(arr, mid + 1, end);
        merge(arr, begin, mid, end);
    }

    public static void merge(int[] arr, int left, int mid, int end) {
        int start = left, right = mid + 1, index = 0;
        int[] tmp = new int[end - left + 1];
        while (left < mid + 1 && right < end + 1) {
//            result += arr[left] < arr[right] ? arr[left]*(end-right+1):0;
            result += arr[left] > arr[right] ? (mid - left + 1) : 0;
            tmp[index++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
        }
        while (right < end + 1) {
            tmp[index++] = arr[right++];
        }
        while (left < mid + 1) {
            tmp[index++] = arr[left++];
        }
        index = 0;
        while (start <= end && index < tmp.length) {
            arr[start++] = tmp[index++];
        }
    }

    public static void quick_sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quick_sort(arr, 0, arr.length - 1);
    }

    public static void quick_sort(int[] arr, int begin, int end) {
        if (begin >= end) return;
        int[] result = part(arr, begin, end);
        quick_sort(arr, begin, result[0] - 1);
        quick_sort(arr, result[1] + 1, end);
    }

    public static int[] part(int[] arr, int begin, int end) {
        int value = arr[begin];
        int leftRegion = begin - 1;
        int rightRegion = end + 1;
        int current = begin;
        while (current < rightRegion) {
            if (arr[current] < value) {
                swap(arr, ++leftRegion, current++);
            } else if (arr[current] > value) {
                swap(arr, --rightRegion, current);
            } else {
                current++;
            }
        }
        return new int[]{leftRegion + 1, rightRegion - 1};
    }

    public static void heap_sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0, end = arr.length - 1; i < arr.length; i++, end--) {
            heapify(arr, end);
            swap(arr, 0, end);
        }

    }

    //把堆顶元素调整到合适的位置.在交换之前已经满足堆的特效,即0-end是一个堆,现在 arr[0]与arr[end]交换了,
    public static void down(int[] arr, int end) {

    }

    //将数组中0-end位置的所有元素调整成一个大顶堆.
    public static void heapify(int[] arr, int end) {
        for (int i = 1; i <= end; i++) {
            int parent = (i - 1) / 2;
            int current = i;
            while (arr[current] > arr[parent] && current > 0) {
                swap(arr, current, parent);
                current = parent;
                parent = (current - 1) / 2;
            }
        }
    }


    public static void main(String[] args) {
        int[] array = {1, 3, 4, 9, 121, 02, -4, 1122, 1};
//        System.out.println(small_sum(array));
        heap_sort(array);
        System.out.println(Arrays.toString(array));

    }
}
