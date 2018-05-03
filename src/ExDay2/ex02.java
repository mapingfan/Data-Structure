package ExDay2;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;

/**
 * 题目描述:
 * 对于一个给定数组,求排序后相邻两个元素的最大差值.
 * 时间复杂度O(n),并且不能用基于非比较的排序算法(即桶排序).
 * 分析:
 * 比较类的排序算法肯定是都不能用了,直接pass.
 * 不能用桶排序,但是可以借用桶排序的思想.这个地方就完美展示桶排序的改进使用.
 * 思路:
 * 对于n个数,创建n+1个桶.第一个桶放置n个数中最小值,最后一个桶放置最大值.
 * 然后把最小值smallest-largest之间的数划分到n+1个桶里.
 * <p>
 * 根据鸽巢原理,最小最大之间一定有一个桶是空的.所以可以肯定最大差值不会出现一个桶内.
 * 因为肯定存在隔着一个空桶的最大差值,肯定是比桶内最大差值大.
 * <p>
 * 所以可以排除最大差值出现在桶内.只有可能出现在桶之间.
 *
 * 今天舍友还给我提供了一种思路,对于小数据规模有效.
 * 假设数组最大值为k,那么分配大小为k的桶.每个数一次落到桶中.桶里需要设置标记是否为空桶.并且还要假设数字为正数,局限性比较大
 *
 * 其实我感觉这个方法其实相当于把数字分段,断段的两端是最大最小值.然后求段之间的最值即可.
 *
 * <p>
 * 下面代码实现,具体实现可能需要抠很多细节.
 */
public class ex02 {

    /**
     * @param array
     * @return 数组有序后最大相邻插值.
     */
    private static int maxGap(int[] array) {
        if (array == null || array.length < 2) return 0;
        int smallest = Integer.MAX_VALUE; //防止奇怪bug发生.不要随便设置一个值.
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            smallest = Math.min(array[i], smallest);
            largest = Math.max(array[i], largest);
        }
        if (smallest == largest) return 0; //最大值最小值相等,那么差值一定是0.
        //准备桶.
        Bucket[] buckets = new Bucket[array.length + 1];
        for (int i = 0; i < buckets.length; i++) {  //注意这个地方对桶进行初始化操作,否则空指针异常.
            buckets[i] = new Bucket();
        }
        int step = (largest - smallest) / (array.length + 1); //桶中步长.
        for (int i = 0; i < array.length; i++) {
            //判断每个数应该在哪个桶中.
            int bucketIndex = judge(smallest, array[i], step, buckets.length);
            /*if (buckets[bucketIndex].isEmpty) {
                buckets[bucketIndex].min = array[i];
                buckets[bucketIndex].max = array[i];
                buckets[bucketIndex].isEmpty = false;
            } else {
                buckets[bucketIndex].min = Math.min(buckets[bucketIndex].min, array[i]);
                buckets[bucketIndex].max = Math.max(buckets[bucketIndex].max, array[i]);
            }*/
            //优化写法.把上面的七行优化成三行写法.
            buckets[bucketIndex].min = !buckets[bucketIndex].isEmpty ? Math.min(buckets[bucketIndex].min, array[i]) : array[i];
            buckets[bucketIndex].max = !buckets[bucketIndex].isEmpty ? Math.max(buckets[bucketIndex].max, array[i]) : array[i];
            buckets[bucketIndex].isEmpty = false;

        }
        //遍历桶,取得结果.从第一个桶开始.
        int result = 0, pre = buckets[0].max;
        for (int i = 1; i < buckets.length; i++) {
            if (!buckets[i].isEmpty) {
               /* int currentDivide = buckets[i].min - pre;
                if (result < currentDivide) result = currentDivide;*/
               //优化写法.这种找最值的情况用库函数直接优化掉.
                result = Math.max(buckets[i].min - pre, result);
                pre = buckets[i].max;
            }
        }
        return result;
    }

    /**
     *
     * @param smallest 桶中最小值.
     * @param value 待判断的值
     * @param step 桶的步长.
     * @param bucketSize 桶大小
     * @return 返回给定值应该在哪个桶中.
     */
    private static int judge(int smallest, int value, int step, int bucketSize) {
        for (int i = 0; i < bucketSize; i++) {
            if (value >= smallest + i * step && value < smallest + (i + 1) * step) return i;
        }
        return bucketSize - 1;  //如果上面都不满足,那么一定在最后一个桶中.
    }

    private static class Bucket {
        int min;
        int max;
        boolean isEmpty = true;


    }

    public static int comparator(int[] array) {
        Arrays.sort(array);
        int result = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int currentDivide = array[i + 1] - array[i];
            result = result < currentDivide ? currentDivide : result;
        }
        return result;
    }

    private static int[] generateArray(int size, int maxValue) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * maxValue);
        }
        return array;
    }

    private static int[] copyArray(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        return copy;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 10000;
        int testTimes = 5000;
        for (int i = 0; i < testTimes; i++) {
            int[] array = generateArray(maxSize, maxValue);
            int[] copyArray = copyArray(array);
            if (comparator(copyArray) != maxGap(array)) {
                System.out.println(Arrays.toString(array));
                System.out.println("fucked method");
                return;
            } else System.out.println(comparator(copyArray));
        }
        System.out.println("nice");
    }

}
