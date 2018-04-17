package DivideAndConquer.ShuffleArray;

import java.util.Arrays;

/**
 * 对于2n个元素的数组,a1a2...anb1b2...bn搞成
 * a1b1a2b2a3b3...anbn.不能开辟内存,原地交换.\
 * 分析:
 * 观察 a1 a2 a3 a4 b1 b2 b3 b4 .交换8个元素的中间四个元素.
 * 即   a1 a2 b1 b2 | a3 a4 b3 b4
 * 然后递归交换左右半边.
 * a1 b1 a2 b2   | a2 b3 a4 b4.
 * <p>
 * 思路:
 * 递归交换数组的中间n个元素.即左边的n/2通右边的n/2进行交换.
 * 然后递归交换左右两个子数组.
 */
public class ShuffleArray {

    private static void solution(int[] array, int left, int right) {
        //计算左边的n/2个元素,
        if (right - left == 1) {
            return;
        }
        int mid = left + (right - left) / 2;  //注意算中值时,要加上左边的下标.
        int leftHalfStart = left + (mid - left) / 2 + 1;
        int rightHalfEnd = mid + 1 + (right - (mid + 1)) / 2;
        for (int i = leftHalfStart; i < leftHalfStart + (rightHalfEnd - leftHalfStart + 1) / 2; i++) {
            int temp = array[i];
            array[i] = array[rightHalfEnd + leftHalfStart - i];
            array[rightHalfEnd + leftHalfStart - i] = temp;
        }
        solution(array, left, mid);
        solution(array, mid + 1, right);

    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2};
        solution(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }
}
