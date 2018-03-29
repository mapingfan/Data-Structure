package SortAlgorithm.BubbleSort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 实现冒泡排序算法.
 * 尽可能的进行优化.
 */
public class BubbleSort {


    /**
     * @param arr 待排序的数组.
     */
    private static void bubbleSortV1(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j <= len - 2 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else {
                    continue;
                }
            }
        }

    }

    /**
     * 进行优化.排序过程中发现,如果一趟没有发生交换操作,则表明已经排序好了,可以不用进行下一趟.
     * 利用这个进行优化.
     * 如何知道已经排序好? ------>> 没有发生交换操作. 设置标志变量,判断是否发生交换操作.发生则flag=true,否则为flag=false;
     * flag为false时,外层循环不需要继续进行.直接结束排序,
     * flag为true时,内层循环就.
     * 现在出现一个问题,相较于V1版本(此版本明确知道外层循环多少趟,n个数字,n-1趟即可),这个版本是根据标志变量判断,没有明确的循环次数
     * 此时应该用死循环,结合标志变量结束循环操作.适合while.
     *
     */
    private static void bubbleSortV2(int[] arr) {
        boolean flag = true;
        int k = arr.length - 2;
        while (flag) {
            flag = false;
            for (int i = 0; i <= k; i++) {  //保证i+1<=arr.length-1 ,所以i<=arr.length-2;
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = true;  //只要发生交换操作,那么就设置为true,表明还需要进行下一趟操作.
                } else {          //问题是,何时设置为false??没有交换时设置为false,代码如何实现呢?
                    continue;     //这个地方的思路是假设已经排好了,如果进入循环体发生交换,那么假设失败.
                }                 //假设:数组本来有序,或者排完一轮后就有序了.所以我们进入while循环第一步就是flag=false;
            }
            k--;
        }
    }

    /**
     * 优化三,还是根据一个现象得出的启发式,从而进行优化排序.
     * 每一趟的所有交换中,我们用一个变量记录最后一次交换发生的文字时.比如第arr[i],arr[i+1],此处发生了最后一次交换操作.
     * 我们知道,i+1后面没有发生交换,说明后面肯定是有序的.i+1后面是一个有序的子序列,不需要参与交换.
     * 交换到i处即可,所以内层循环的每次循环次数动态产生的,记录每次内层循环最后一次交换的位置,设置为下次内层循环的边界,
     * 从而优化了比较的次数.设置一个flag变量记录最后交换的位置.如果flag=0,那么表明已经有序了.
     * 所以只要flag>0,那么内层循环还需要继续.
     * 仍然和V2方法一样,假设有序,然后进行验证.
     * @param arr 待排序数组.
     */
    private static void bubbleSortV3(int[] arr) {
            int flag = arr.length - 2;
        int k = flag + 1;
        while (flag > 0) {
            flag = 0;
            for (int i = 0; i < k; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = i;
                }
            }
            k = flag + 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,0,9,3,12,7,8,3,4,65,22};
        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));
        System.out.println("排序后:");
        bubbleSortV3(arr);
        System.out.println(Arrays.toString(arr));
    }
}
