package ExDay10;

import java.util.LinkedList;

/**
 * 窗口内最大值问题.
 * 给定一个大小为w的窗口,每次向右边移动一位.求这个过程中每个窗口的最大值.
 * <p>
 * 假设一共1,2,3,4,5,6,7 七个数字,窗口大小为3.那么应该产生5个最大值.即n-w+1.
        */
public class Ex01 {

    public static int[] solution(int[] array, int w) {
        if (array == null || w < 1 || array.length < w) return null;
        int[] result = new int[array.length - w + 1]; //结果数组.
        //一种策略是直接特判result[0].然后后面依次处理.
        //第二种策略就是判断下什么时候窗口达到3了,因为初始窗口大小为0,经历三次才能达到完整窗口.
        int index = 0;
        LinkedList<Integer> help = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            while (!help.isEmpty() && array[help.peekLast()] < array[i]) {
                help.pollLast();
            }
            help.addLast(i);
            if (help.peekFirst() == i - w) {
                help.pollFirst();
            }
            if (i >= w - 1) {
                result[index++] = array[help.peekFirst()];
            }
        }
        return result;
    }

}
