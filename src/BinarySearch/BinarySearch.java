package BinarySearch;

public class BinarySearch {
    private static int binarySearch(int[] arr, int begin, int end, int value) {
        int center = begin + (end - begin) / 2;  //一定要这样写,两个int相加会溢出.
        if (begin > end) {
            return begin;
        }
        if (arr[center] == value) {
            return center + 1;
        } else {
            if (arr[center] > value) {
                return binarySearch(arr, begin, center - 1, value);
            } else {
                return binarySearch(arr, center + 1, end, value);
            }
        }
    }

    private static int binarySearchV2(int[] arr, int value) {
        int begin = 0;
        int end = arr.length - 1;
        while (begin < end) {
            int center = begin + (end - begin) / 2;
            if (arr[center] == value) {
                return center;
            } else {
                if (arr[center] > value) {
                    end = center - 1;
                } else {
                    begin = center + 1;
                }
            }
        }
        return begin;
    }

    public static void main(String[] args) {
        int[] arr = {3, 38, 44, 47, 47, 55, 55};
        System.out.println(binarySearchV2(arr, 55));
    }
}
