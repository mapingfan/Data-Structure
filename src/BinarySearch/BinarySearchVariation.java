package BinarySearch;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchVariation {

    //等于查找key值的元素有好多个，返回这些元素最左边的元素下标
    public static int findFirstEqual(int[] arr, int value) {
        if (arr == null || arr.length < 1) return -1;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= value) right = mid - 1; //因为要返回最左边,所以这个地方符号填 >=
            else left = mid + 1;
        }
        if (left < arr.length && arr[left] == value) return left;
        return -1;
    }

    //查找最后一个与key相等的元素
    public static int findLastEqual(int[] arr, int value) {
        if (arr == null || arr.length < 1) return -1;
        int left = 0, right = arr.lenght - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= value) left = mid + 1;  //查找最后一个.也就是在右边.
            else right = mid - 1;
        }
        if (right < arr.length && arr[right] == value) return right;
        return -1;

    }

    private static final Random random = new Random();

    //生成最大值是maxValue,大小为size的随机数组.
    private static int[] generate(int maxValue, int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = random.nextInt(maxValue + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 1, 2, 4, 5, 7, 8, 9, 10, 10, 11, 11, 13, 14, 15, 16, 16, 16, 16, 17, 17, 18, 19, 19, 20, 20, 20, 20};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(findFirstEqual(arr, 20));
    }
}
