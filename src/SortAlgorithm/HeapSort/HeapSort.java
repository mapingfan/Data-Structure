package SortAlgorithm.HeapSort;

import java.util.ArrayList;

/**
 * 最后一个常见排序算法,堆排序.基于堆这种弱序数据结构实现.
 */
public class HeapSort {

    private static void heapify(ArrayList<Integer> arr, int root) {
        if (root > arr.size() / 2 - 1) { //没有孩子,递归出口.
            return;
        } else {
            heapify(arr, root * 2 + 2); //调整左堆.
            heapify(arr, root * 2 + 1); //调整右堆.
            //把root节点向下调整到合适的位置.
            trickleDown(arr, root);
        }
    }

    public static void heapSort(ArrayList<Integer> arr, int size) {
        heapify(arr, 0);
        System.out.println(arr);
        for (int i = 0; i < size; i++) {
            System.out.print(remove(arr) + " ");
        }
    }

    private static int remove(ArrayList<Integer> arr) {
        int value = arr.get(0);
        arr.set(0, arr.get(arr.size() - 1));
        trickleDown(arr, 0);
        arr.remove(arr.size() - 1);
        return value;
    }

    //把root调整到堆中合适的位置.
    private static boolean trickleDown(ArrayList<Integer> arr, int index) {

        int largeChild;
        int top = arr.get(index);
        while (index < arr.size() / 2) {
            int left = 2 * index + 1;
            int right = left + 1;
            if (right < arr.size() && arr.get(left) < arr.get(right)) {
                largeChild = right;
            } else if (right < arr.size() && arr.get(left) >= arr.get(right)) {
                largeChild = left;
            } else {
                break;
            }

            if (top >= arr.get(largeChild)) {
                break;
            }
            arr.set(index, arr.get(largeChild));
            index = largeChild;
        }
        arr.set(index, top);


        return true;
    }

    public static void main(String[] args) {
        int[] arr = {33, 23241, 4444512, 12457, 44, 38, 5, 47, 98, 3, 212, 23, 31212, 435, 7523, 12, 564, 233421};

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(arr[i]);
        }
        heapSort(arrayList, arr.length);

    }
}
