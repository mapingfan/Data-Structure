package SmallSum;

/**
 * 小和问题:寻找给定数字左边比它小的数字的和(不包括该数字本身).
 * 给定序列: 1 ,3 ,2, 4, 6, 5
 * 对于1的小和: 不存在
 * 对于3的小和:  1<3,存在一个1.
 * 对于2的小和:  1<2,存在一个1.
 * <p>
 * 思路很简单O(n^2)可以解决.
 * <p>
 * 上课的时候,左神讲了一种小和问题的快速解法,nlogn就可以解决,利用的是归并排序.
 * 下面就顺便复习下归并排序,学习下左神的代码.
 */
public class Solution {
    /**
     * @param array
     * @param l     数组左边界
     * @param r     数组右边界
     */
    private static void mergeSort(int[] array, int l, int r) {
        if (array == null || l > r) throw new IllegalStateException("数组异常");
        if (l == r) return;
        int mid = l + (r - l) / 2;
        mergeSort(array, l, mid);
        mergeSort(array, mid + 1, r);
        merge(array, l, mid, r);
    }
    static int res = 0;
    private static void merge(int[] array, int l, int mid, int r) {
        int[] assist = new int[r - l + 1];
        int index = 0, p1 = l, p2 = mid + 1;

        while (p1 <= mid && p2 <= r) {
            res += array[p1] < array[p2] ? (r - p2 + 1) * array[p1] : 0;
            assist[index++] = array[p1] > array[p2] ? array[p2++] : array[p1++];
        }
        while (p1 <= mid) assist[index++] = array[p1++];
        while (p2 <= r) assist[index++] = array[p2++];
        //拷贝回去
        for (int i = 0; i < assist.length; i++) {
            array[l + i] = assist[i];
        }
    }


    /**
     * 下面利用归并排序来解决小和问题.
     * 对于序列 1,3,4,2,5 . 4+6+4+2=16.
     * 对于数字三,我们考虑3的左边右几个比三大的数字,4,5,那么最后小和计算中,3要被加上两次.
     * 所以,对于每个数字,考虑左边右几个数字比它大,同样可以计算.
     * 但是不用遍历,用归并解决.
     * 对于1,3,4,2,5 ->拆成如下
     * 1,3,4 | 2,5 .左右都有序.
     */

    private static int solutionV2(int[] array) {
        mergeSort(array, 0, array.length - 1);
//        System.out.println(res);
        return res;
    }

    public static int solution(int[] array) {
        if (array == null || array.length == 1) return 0; //一个元素左边没有,所以返回0.
        int sum = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) sum += array[j];
            }
        }
        return sum;
    }

    //小和衍生问题,打印逆序对. 即1 ,3 ,2, 4, 6, 5,
    //对于数组4,输出<1,4>,<3,4>,<2,4>

    private static void print(int[] arr) {
        if (arr == null || arr.length == 1) return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) System.out.println("<" + arr[j] + " , " + arr[i] + ">");
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
//        System.out.println(solutionV2(arr));
//        print(arr);
//        mergeSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
        solutionV2(arr);

    }

}
