package DivideAndConquer.IndexSearcher;

/**
 * 在一个给定的有序数组中,查找是否存在A[i]=i的存在,存在的返回下标,不存在返回-1.
 * 要求时间复杂度小于O(n).
 * 分析:
 * 因为要求复杂度特别低,所以基本可以猜到是二分查找算法,或者是其变形.
 * 只有这样才能达到这种复杂度.
 */
public class Solution {

    private static int indexSearcher(int[] array) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == mid) {
                return mid;
            } else {
                if (array[mid] > mid) { //简单分析下:如果>mid,假设mid是5,arr[mid]=10.那么数组右半部分(5-15)不会存在这种下标等于数组值的情况.
                    //即,左边会出现这种情况.右边不会出现arr[i]=i.如果mid=5,arr[mid]=6,那么i=6时,那么arr[i]至少是7(无重复数字)
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 3, 4};
        System.out.println(indexSearcher(arr));

    }

}
