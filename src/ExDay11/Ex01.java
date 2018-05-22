package ExDay11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 单调栈结构应用问题.
 */
public class Ex01 {
    //1.给定一个数组,数组里面的值代表相应矩形的大小.求最大的矩形面积的题目.

    /**
     * 思路很简单,暴力求解,一共有n^2个子数组.对于每个数组求一下面积.
     * 最后返回最大面积.
     */

    public static int maxRectangleArea_0(int[] array) {
        if (array == null) return -1; //异常情况.
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int height = Integer.MAX_VALUE;
            for (int j = i; j < array.length; j++) {
                height = Math.min(height, array[j]);
                max = Math.max(max, calculateArea(array, i, j, height));
            }
        }
        return max;
    }


    /**
     * 上面这个方法的时间复杂度是O(n^2).还有更好的办法嘛.
     * 新的思路:
     * 对于最后求出的最大矩形一定是以数组中某个值为高度的.
     * 所以尝试以每个下标向两边拓展是可以找到最大面积,不会丢失.
     * 以数组中任意一个下标i开始,向两边扩展.如果左边的高度大于当前高度,那么可以拓展,
     * 如果左边的高度比当前i位置小,那么不可以拓展.
     * 对于右边同样处理.
     * <p>
     * 这个问题马上可以抽象为单调栈问题,每个位置找离它最近的最小值.
     * 可以看出是单调栈问题.下面用这个方法进行求解.
     */
    public static int maxRectangleArea_1(int[] array) {
        if (array == null) return -1;
        //因为数组中可能存在重复元素,所以单调栈会存在压缩相同值的情况.
        //单调栈,从小到大.
        LinkedList<LinkedList<Integer>> help = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            while (!help.isEmpty() && array[help.peekFirst().get(0)] > array[i]) {
                //需要出栈.这个时候就要结算信息.因为谁出栈,说明当前i不能向右边拓展了.
                //当前要出栈元素的下一个位置就是我的左边界.所以这个时候可以计算出一个面积.
                LinkedList<Integer> tmp = help.pollFirst();
                max = Math.max(max, array[tmp.get(0)] * (i - 1 - (help.isEmpty() ? -1 : help.peekFirst().peekLast())));
            }
            if (help.isEmpty()) {
                LinkedList<Integer> list = new LinkedList<>();
                list.addLast(i);
                help.addFirst(list);
                continue;
            }
            if (array[help.peekFirst().get(0)] == array[i]) {
                help.peekFirst().add(i);
                continue;
            }
            LinkedList<Integer> list = new LinkedList<>();
            list.addLast(i); //统一加到末尾.然后统一取0号为.
            help.addFirst(list);
        }
        while (!help.isEmpty()) {
            //处理栈中剩余的元素.剩下的元素的右边界都是到数组末尾.因为没有元素比他们小使得他们出栈.
            //左边界可能是栈中的下一个元素.
            LinkedList<Integer> tmp = help.pollFirst(); //先出来.
            //这个地方的tmp可能有多个相同值被压缩在一起.但是不影响.因为左边界不由这个决定.
            //此外help.peekLast().get(0).此处help.peekLast()也可能有压缩的情况.但是我们去0,保证最宽即可.
            max = Math.max(max, array[tmp.get(0)] * (array.length - 1 - (help.isEmpty() ? -1 : help.peekFirst().peekLast())));
        }
        return max;
    }


    //矩形面积的衍生问题.比如求一个二维数组中,最大矩形区域1的数量.
    //对数组进行压缩,然后转换为求面积的问题.

    /**
     * 1 0 1 1
     * 1 1 1 1
     * 1 1 1 0
     * 最大区域为6.
     */

    public static int maxArea(int[][] matrix) {
        if (matrix == null) return -1;
        int max = Integer.MIN_VALUE;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                height[j] = matrix[i][j] == 0 ? 0 : height[j] + 1;  //这个地方注意对出现0特殊考虑下,不能单纯的加.
            }
            max = Math.max(max, maxRectangleArea_1(height));
        }
        return max;
    }


    //计算数组中i-j的矩形面积.其中宽为j-i+1,长为array[i...j]中的最小值.
    private static int calculateArea(int[] array, int i, int j, int height) {
        //异常情况交给调用着判断.
        return (j - i + 1) * height;
    }

    private static final Random random = new Random();

    public static int[] generateArray(int seed, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(seed) + 10;
        }
        return array;
    }

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    public static void main(String[] args) {
        /*for (int i = 500; i > 10; i--) {
            int[] array = generateArray(i, i);
            int[] copy = copyArray(array);
            int r0 = maxRectangleArea_0(array);
            int r1 = maxRectangleArea_1(copy);
            if (r0 == r1) System.out.println("nice");
            else {
                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.toString(copy));
                System.out.println(maxRectangleArea_0(array));
                System.out.println("r0 result :" + r0);
                System.out.println("r1 result :" + r1);
                System.out.println("fucked");
                break;
            }
        }*/
/*        int[] array = {14, 16, 16, 39, 16, 24};
        System.out.println(maxRectangleArea_0(array));
        System.out.println(maxRectangleArea_1(array));*/
        int[][] matrix = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(maxArea(matrix));

    }

}
