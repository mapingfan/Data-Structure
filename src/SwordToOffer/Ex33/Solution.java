package SwordToOffer.Ex33;

import java.util.Arrays;

/**
 * 逆序问题:找出左边右多少比右边当前元素大的个数
 * 小和问题:找出右边多少比当前左边值大的元素个数,然后乘以左边元素值(因为右边有几个比左边当前大,那么计算小和就要乘几次,所以可以看见乘法)
 * 不要把小和问题和逆序问题搞混.
 * 好好思考如何不重复加和漏加问题.
 *
 * 比如逆序问题: 当左边>右边当前时,可以确定一点,左边剩余的部分一定都比右边当前大,可以凑出一对.但是需要考虑右边当前的前面部分嘛?
 * 不需要,因为前面已经加过了.
 *
 * 不管是小和还是逆序问题,永远关注的都是未访问的元素,对于已经访问的元素,不需要考虑.一旦考虑就会重复加.
 */

//利用归并排序合并过程解决小和问题.
public class Solution {
    public int res = 0;

    public int InversePairs(int[] array) {
        if (array == null || array.length <= 1) return 0;
        mergeSort(array, 0, array.length - 1);
        return res ;
    }

    public void mergeSort(int[] array, int begin, int end) {
        if (array == null || array.length <= 1 || begin >= end) return;
        int mid = begin + (end - begin) / 2;
        mergeSort(array, begin, mid);
        mergeSort(array, mid + 1, end);
        merge(array, begin, mid, end);
    }

    //合并
    private void merge(int[] array, int begin, int mid, int end) {
        int[] assist = new int[end - begin + 1]; //一共这么多个元素.
        int index = 0, lPtr = begin, rPtr = mid + 1;
        while (lPtr <= mid && rPtr <= end) {
            res += array[lPtr] > array[rPtr] ? mid - lPtr + 1 : 0;
            res %= 1000000007;
            assist[index++] = array[lPtr] > array[rPtr] ? array[rPtr++] : array[lPtr++];
        }
        //剩余的合并上.
        while (rPtr <= end) assist[index++] = array[rPtr++];
        while (lPtr <= mid) assist[index++] = array[lPtr++];
        //合并完后拷贝回原数组.begin-end位置处.
        for (int i = 0; i < assist.length; i++) {
            array[begin + i] = assist[i];
        }

    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 0};
        System.out.println(new Solution().InversePairs(array));

    }


}

