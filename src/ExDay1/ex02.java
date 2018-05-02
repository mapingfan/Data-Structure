package ExDay1;

import java.util.Arrays;
import java.util.Random;

/**
 * 划分问题,通过一个给定的数字,把数组划分开.
 * 快排划分算法基础.
 * ex01中其实已经给出了一个划分算法,只不它有诸多限制,而且写起来也比较冗长.
 * 下面实现下左神课上讲的思路.
 */
public class ex02 {
    /**
     *
     * @param array
     * @param givenNum
     *
     * 这个方法只讲数组划分成两部分,<=givenNum,>givenNum两个部分.
     * 对于相等元素的处理不是很好.
     * 看下一个算法,荷兰国旗问题.
     */
    private static void partition(int[] array, int givenNum) {
        if (array==null||array.length<2) return;
        int lessRegion = -1;
        int currentPtr = 0;
            while (currentPtr < array.length) {
            if (array[currentPtr]>givenNum) currentPtr++;
            else swap(array, ++lessRegion, currentPtr++);
        }
    }

    /**
     *
     * @param array
     * @param left 数组左边界,从0开始.
     * @param right 右边界,最后一个元素下标.
     * @param givenNum 给定值
     * @return 相等元素起始下标.
     * 把数组划分成三部分,< = > .对于存在多个相等元素的时候,找出相等元素的个数,以及第一个下标.
     *
     * 算法维护两个区域,lessRegion,moreRegion.存在小于和大于给定值.注意此处不包含相等.
     * lessRegion一开始指向-1位置,moreRegion指向arr.length位置.
     * 然后从第一个元素开始,如果当前元素
     *  大于给定值:
     *      moreRegion需要扩增,即moreRegion--.此时发生交换操作,把当前元素交换到moreRegion区域外的第一个元素,
     *  即此处看见的swap(array, --moreRegion, currentPtr).注意先--moreRegion,即先找到合适的位置.并且可以看见交换完了,
     *  currentPtr并没有动,因为交换后,currentPtr指向的元素可能应该在lessRegion区域,也可能不在,所以要进入下一次循环,再次判断.
     *
     *  等于给定值:less,more区域都不需要扩充,直接看下一个元素即可.
     *
     *  小于给定值:
     *      less需要扩充了,所以swap(array, ++leftRegion, currentPtr++).此时交换后的元素可以不用考虑,因为一定不会在小于
     *  区域里,也不可能在大于区域里,因为在大于区域里,早就交换过去了.
     *
     *  相当于小于区域推着等于区域往期走,结束条件是碰到了大于区域的边界.即current<moreRegion.
     *
     *
     */
    private static int[] netherlandsFlag(int[] array, int left, int right, int givenNum) {

        int leftRegion = left - 1, moreRegion = right + 1, currentPtr = left;
        while (currentPtr < moreRegion) { //注意边界.以及此处为什么是moreRegion.
            if (array[currentPtr]<givenNum) swap(array, ++leftRegion, currentPtr++);
            else if (array[currentPtr] > givenNum) swap(array, --moreRegion, currentPtr); //注意此处为什么没有currentPtr++.
            else currentPtr++;
        }
        return new int[]{leftRegion + 1, moreRegion - 1};
    }




    private static void swap(int[] array, int i, int i1) {
        int tmp = array[i];
        array[i] = array[i1];
        array[i1] = tmp;
    }

    /**
     * 有了荷兰国旗算法,可以改进快速排序.
     * 经典快排,每次确定一个元素的位置.通过使用新的划分算法,每次可以确定多个元素位置.
     * 下面实现下:
     */
    static Random random = new Random();
    private static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int[] result = netherlandsFlag(array, left, right, array[left+random.nextInt(right-left)]);
            quickSort(array, left, result[0] - 1);
            quickSort(array, result[1] + 1, right);
        }
    }
    public static void main(String[] args) {
        int[] arr = {322, 1, 2, 5132, 21, 53, 2, 12};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

//
    }

}
