package ExDay14;

import java.util.HashMap;

/**
 * 给定一个全是正数的数组,给一个整数aim,求累加和等于aim的最长子数组问题.
 */
public class Ex04 {

    /**
     * 思路一:暴力求解每个子数组.
     */

    public static boolean check(int[] array) {
        if (array == null || array.length < 1)
            return false;
        return true;
    }

    public static int solution_1(int[] array, int aim) {
        if (!check(array) || aim <= 0) return -1; //无法凑出.因为全是正数.
        int maxLen = 0;
        for (int i = 0; i < array.length; i++) {
            int tmpSum = 0;
            for (int j = i; j < array.length; j++) {
                tmpSum += array[j];
                if (tmpSum == aim) maxLen = Math.max(maxLen, j - i + 1);
                /*if (process(array, i, j) == aim) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }*///这个地方的处理逻辑有点不好.
            }
        }
        return maxLen;
    }

    /**
     * 上面的算法太暴力,复杂度是O(n^3).
     * 稍微改进下.
     * 求以i开头的子数组.并且倒着搜索,找到一个就进入下一次循环.
     */

    public static int solution_2(int[] array, int aim) {
        if (!check(array) || aim <= 0) return 0; //无法凑出.因为全是正数.
        int maxLen = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j >= i; j--) {
                if (process(array, i, j) == aim) {
                    maxLen = Math.max(maxLen, j - i + 1);
                    break;
                }
            }
        }
        return maxLen;
    }


    /**
     * 再来一个启发方法.对于任意位置,找以i结束的最长子串. 如果到i的和是k,那么在前面找到最早出现k-aim即可.
     */

    public static int solution_3(int[] array, int aim) {
        if (!check(array) || aim <= 0) return -1; //无法凑出.因为全是正数.
        HashMap<Integer, Integer> help = new HashMap<>();
        help.put(0, -1);
        int index = 0, maxLen = 0, tmpSum = 0;
        while (index < array.length) {
            tmpSum += array[index];
            if (help.containsKey(tmpSum - aim)) maxLen = Math.max(maxLen, index - help.get(tmpSum - aim));
            if (!help.containsKey(tmpSum)) help.put(tmpSum, index);
            index++;
        }
        return maxLen;
    }


    /**
     * 暴力递归解法.
     */

    public static int solution_4(int[] array, int aim, int n) {
        if (!check(array)) return 0;
        if (aim <= 0) return 0;
        if (n == 1) return array[n - 1] == aim ? 1 : 0;
        return Math.max(solution_4(array, aim, n - 1), help(array, aim, n));
    }

    /**
     * 求包含第n个字符,且和为aim的最长字符串,代价O(n).
     *
     * @param array
     * @param aim
     * @param n
     * @return
     */
    private static int help(int[] array, int aim, int n) {
        int len = 0;
        if (array[n - 1] > aim) return 0;
        int tmpSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            tmpSum += array[i];
            if (tmpSum == aim) {
                len = Math.max(len, n - 1 - i + 1);
                break;
            }
        }
        return len;
    }

    private static int process(int[] array, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += array[k];
        }
        return sum;
    }


    /**
     * 上面已经给出了4种解决,但是现在想要一种时间复杂度是O(n),空间复杂度是O(1)的算法.
     * 因为就是联机算法了,一遍扫描即可.
     * 下面分析思路.
     */

    public static int solution_5(int[] array, int aim) {
        if (!check(array) || aim <= 0) return 0;
        int tmpSum = 0;
        int begin = 0;
        int maxLen = 0;
        for (int i = 0; i < array.length; i++) {
            tmpSum += array[i];
            if (tmpSum > aim) {
                tmpSum -= array[begin++];
            }
            if (tmpSum == aim) {
                maxLen = Math.max(maxLen, i - begin + 1);
                tmpSum -= array[begin++];
            }
        }
        for (int i = begin; i < array.length && tmpSum > aim; i++) {
            tmpSum -= array[i];
            if (tmpSum == aim) {
                maxLen = Math.max(maxLen, array.length - 1 - i);
                break;
            }
        }
        return maxLen;
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 5};
        System.out.println(solution_1(array, 10));
        System.out.println(solution_2(array, 10));
        System.out.println(solution_3(array, 10));
        System.out.println(solution_4(array, 10, array.length));
        System.out.println(solution_5(array, 10));
    }
}
