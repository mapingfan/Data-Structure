package ExDay1;

import java.util.Arrays;

/**
 * 给定一个数组,和一个数,把数组划分开来,左边比给定的数小,右边大.
 * 要求O(n),并且不能利用额外空间.
 * 分析: 快排算法的枢纽选取算法变种.
 */
public class ex01 {

    private static void pivot(int[] array, int givenNum) {
        if (array == null || (array.length == 1 && array[0] == givenNum)) return;
        //判断给定num是否在数组中.
        boolean isInArray = false;
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == givenNum) {
                isInArray = true;
                index = i;
                break;
            }
        }
        if (isInArray) {
            //如果不是数组第一位,要负责交换到数组第一位.
            if (index != 0) swap(array, 0, index);
            //然后开始交换.
            int leftPtr = 0, rightPtr = array.length - 1;
            boolean flag = true;
            while (leftPtr < rightPtr) {
                if (flag) {
                    if (array[rightPtr] < givenNum) {
//                        swap(array,leftPtr,rightPtr);
                        array[leftPtr] = array[rightPtr];
                        leftPtr++;
                        flag = false;
                    } else {
                        flag = true;
                        rightPtr--;
                    }
                } else {
                    if (array[leftPtr] > givenNum) {
                        array[rightPtr] = array[leftPtr];
//                        swap(array, leftPtr, rightPtr);
                        rightPtr--;
                        flag = true;
                    } else {
                        flag = false;
                        leftPtr++;
                    }
                }
            }
            array[leftPtr] = givenNum;
            return;
        }
    }

    private static void swap(int[] array, int i, int index) {
        int tmp = array[i];
        array[i] = array[index];
        array[index] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {6, 4, 5, 1, 3, 1, 1};
        pivot(array, 1);
        System.out.println(Arrays.toString(array));
    }

}
