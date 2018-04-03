package Recursion;

/**
 * 查找第K个最大值.实现思路参考第K个最小.
 * 调整分区算法,使得比pivot的在左边,比其小的在右边.
 */
public class KthBigProblem {

    private static int getPivotIndex(int[] arr, int begin, int end) {
        int base = arr[begin];
        boolean flag = true;
        while (begin < end) {
            if (flag) {
                if (arr[end] > base) {
                    arr[begin++] = arr[end];
                    flag = false;
                } else {
                    end--;
                }
            } else {
                if (arr[begin] < base) {
                    arr[end--] = arr[begin];
                    flag = true;
                } else {
                    begin++;
                }
            }
        }
        arr[begin] = base;
        return begin;
    }

    private static int solution(int[] arr, int k, int begin, int end) {
        int pivotIndex = getPivotIndex(arr, begin, end);
        if (k - 1 == pivotIndex) {
            return arr[pivotIndex];
        } else if (k - 1 > pivotIndex) {
            return solution(arr, k, pivotIndex + 1, end);
        } else {
            return solution(arr, k, begin, pivotIndex - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        System.out.println(solution(arr, arr.length, 0, arr.length - 1));
    }


}
