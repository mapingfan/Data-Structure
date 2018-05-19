package ExDay9;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Top k问题.第k个最大或者最小问题.
 * <p>
 * case1: 排序后直接返回第k个.O(nlogn)
 * case2: 堆结构解决,小顶堆连续弹出堆顶元素k-1次后的堆顶元素就是所求值.O(k*logn)
 * case3: 荷兰国旗法改进,期望时间复杂度O(n)
 * case4: BFPRT算法,类似荷兰国旗算法,但是选取划分节点有一些技巧.
 */
public class Ex01 {

    public static int bfptrt_wrap(int[] array, int k) {
        return getKthSmall_bfprt(array, k, 0, array.length - 1);
    }

    public static int getKthSmall_bfprt(int[] array, int k, int begin, int end) {
        if (array == null || k > array.length) return -1;
        if (end - begin <= 4) {
            insertionSort(array, begin, end);
            return array[k - 1];
        }
        int offset = (end - begin + 1) % 5 == 0 ? 0 : 1;
        int[] medianArray = new int[(end - begin + 1) / 5 + offset]; //放置每个组中位数.
        for (int i = 0; i < medianArray.length; i++) {
            if (i == medianArray.length - 1) {
                medianArray[i] = fiveSort(array, 5 * i, end);
            } else medianArray[i] = fiveSort(array, i * 5, i * 5 + 4);
        }
        //获得中位数数组.获得中间那个数字,递归调用.
        int midValue = getKthSmall_bfprt(medianArray, medianArray.length / 2, 0, medianArray.length - 1);
        //根据这个mid值划分原数组.
        int[] result = fun(array, midValue, begin, end);
        if (k - 1 >= result[0] && k - 1 <= result[1]) return array[k - 1];
        else if (k - 1 < result[0]) return getKthSmall_bfprt(array, k, begin, result[0] - 1);
        else return getKthSmall_bfprt(array, k, result[1] + 1, end);
    }

    private static int[] fun(int[] array, int midValue, int begin, int end) {
        int num = midValue;
        int lessRegion = begin - 1, moreRegion = end + 1;
        int current = begin;
        while (current < moreRegion) { //边界是current没触碰到moreRegion区域.
            if (array[current] < num) {
                swap(array, current++, ++lessRegion);
            } else if (array[current] > num) {
                swap(array, current, --moreRegion);
            } else current++;
        }
        return new int[]{lessRegion + 1, moreRegion - 1}; //相等区域.
        //相等区域至少为1.因为是随机选择数组中的某个元素进行划分.
    }


    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }


    private static int fiveSort(int[] array, int begin, int end) {
//        System.out.println(begin + " " + end);
        insertionSort(array, begin, end);
        return array[begin + (end - begin) / 2];
    }

    public static int getKthSmall(int[] array, int k) {
        if (array == null || k > array.length) return -1; //表示不存在.
        PriorityQueue<Integer> help = new PriorityQueue<>(Comparator.comparingInt(i -> i));
        for (int i = 0; i < array.length; i++) {
            help.add(array[i]);
        }
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (i == k - 1) {
                result = help.poll();
                break;
            } else help.poll();
        }
        return result;
    }

    public static int getKthSmall_sort(int[] array, int k) {
        if (array == null || k > array.length) return -1;
        Arrays.sort(array);
        return array[k - 1];
    }

    //返回第k个小元素.k从1开始,对应到数组就是k-1个.

    public static int getKthSmall_partition(int[] array, int k) {
        return process(array, 0, array.length - 1, k);
    }

    public static int process(int[] array, int begin, int end, int k) {
        if (array == null || k > array.length || begin > end) return -1;
        int[] result = partition(array, begin, end);
        if (k - 1 >= result[0] && k - 1 <= result[1]) return array[k - 1];
        else if (k - 1 < result[0]) return process(array, begin, result[0] - 1, k);
        else return process(array, result[1] + 1, end, k);
    }

    /**
     * 对begin-end范围内(包括连个边界)进行一次荷兰国旗划分.
     * 返回相等区域的左右边界.
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    private static int[] partition(int[] array, int begin, int end) {
//        int num = RandomUtils.nextInt(begin, end);
        int num = array[random.nextInt(end) % (end - begin + 1) + begin];
        int lessRegion = begin - 1, moreRegion = end + 1;
        int current = begin;
        while (current < moreRegion) { //边界是current没触碰到moreRegion区域.
            if (array[current] < num) {
                swap(array, current++, ++lessRegion);
            } else if (array[current] > num) {
                swap(array, current, --moreRegion);
            } else current++;
        }
        return new int[]{lessRegion + 1, moreRegion - 1}; //相等区域.
        //相等区域至少为1.因为是随机选择数组中的某个元素进行划分.
    }

    /**
     * 将current与index位置的元素交换.
     *
     * @param array
     * @param current
     * @param index
     */
    private static void swap(int[] array, int current, int index) {
        int tmp = array[current];
        array[current] = array[index];
        array[index] = tmp;
    }


    private static final Random random = new Random();

    public static int[] generateArray(int seed, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(seed);
        }
        return array;
    }

    public static void main(String[] args) {
        for (int i = 15; i >= 2; i--) {
            int[] array = generateArray(i, random.nextInt(i));
            int[] copy = new int[array.length];
            System.arraycopy(array, 0, copy, 0, array.length);
            int r1 = getKthSmall(array, i / 2);
            int r2 = bfptrt_wrap(copy, i / 2);
            if (r1 == r2) {
                System.out.println("Nice");
            } else {
                System.out.println("fuck");
                System.out.println(r1+" "+r2);
                System.out.println(Arrays.toString(array));
                System.out.println(i / 2);
                break;
            }
        }

    }
}
