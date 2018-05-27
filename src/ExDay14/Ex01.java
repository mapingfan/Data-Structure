package ExDay14;

/**
 * 求异或和最大的子数组.
 */
public class Ex01 {
    /**
     * 思路一:暴力求,一共n^2个子数组.求每个子数组的最大异或和即可.
     *
     * @param array
     * @return
     */
    public static int maxXorSum(int[] array) {
        if (array == null || array.length < 1) return -1;
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <= i; j++) {
                int res = 0;
                for (int k = j; k <= i; k++) {
                    res ^= array[k];
                }
                max = Math.max(max, res);
            }
        }
        return max;
    }


    public static int maxXorSum_3(int[] array) {
        if (array == null || array.length < 1) return -1;
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                max = Math.max(max, process(array,i, j));
            }
        }
        return max;
    }
    //返回i-j的异或和.
    private static int process(int[] array, int i, int j) {
        int res = 0;
        for (int k = i; k <= j; k++) {
            res ^= array[k];
        }
        return res;
    }


    /**
     * 上面的暴力方法时间复杂度是O(n^3).
     * 下面利用一个启发式规则来改进下算法.
     * 已知异或的一个性质:
     * 如果A^B=C,那么有
     * A^C=B/B^C=A.
     * <p>
     * 即可互推.
     * 如果知道[0-i]的异或和,那么[k-i]的异或和可以通过[0-k-1]^[0-i]得出.
     * 我们可以在这个过程中记录下[0-k]的异或和,然后可以后面加速求解.
     */

    public static int maxXorSum_2(int[] array) {
        if (array == null || array.length < 1) return -1;
        int[] dp = new int[array.length]; //dp[i]负责记录0-i的异或和.
        dp[0] = array[0];
        int max = 0;
        for (int i = 1; i < array.length; i++) {
            int res = 0;
            for (int j = 0; j <= i; j++) {
                res ^= array[j];
            }
            dp[i] = res;
        }
        //上面的for循环求出了0-i位置的任意异或和,下面利用这些信息求出任意两个位置的异或和.
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j <= i; j++) {
                max = Math.max(max, dp[i] ^ dp[j - 1]); //j-i的异或和= dp[j-1]^dp[i].
            }
        }
        return max;
    }

    //上面的代码虽然丑了点,但是复杂度是O(n^2)的.
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(maxXorSum(array));
        System.out.println(maxXorSum_2(array));
        System.out.println(maxXorSum_3(array));


    }
}
