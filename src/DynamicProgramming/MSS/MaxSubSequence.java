package DynamicProgramming.MSS;

import java.util.Arrays;

/**
 * 最大连续子列和问题.
 *
 * 若包含i项,不包含j项 ->  f(i,j) = A[i]+f(i+1,j-1);  //分析错误.
 * 若包含i项,也包含j项 ->  f(i,j) = sum(arr,i,j);
 * 若不包含i项,但包含j项 -> f(i,j) = f(i+1,j-1)+A[j]  //分析错误.
 * 若不包含i项,不包含j项 -> (i,j)　＝　f(i+1,j-1).
 * <p>
 * 若包含i项,不包含j项 ->  f(i,j) = f(i,j-1);  -->
 * 若包含i项,也包含j项 ->  f(i,j) = sum(arr,i,j);
 * 若不包含i项,但包含j项 -> f(i,j) = f(i+1,j)
 * 若不包含i项,不包含j项 -> (i,j)　＝　f(i+1,j-1).
 */
public class MaxSubSequence {
    //f(i,j)=max(A[i]+f(i+1,j-1),sum(arr,i,j),f(i+1,j-1)+A[j],f(i+1,j-1)).
    private static int maxSubSequence(int s, int e, int[] array) {
        if (isAllNegative(array)) {
            Arrays.sort(array);
            return array[array.length - 1];
        }
        if (s == e) {
            return array[s];
        } else if (s > e) {
            return 0;
        } else {
            int sum = sum(array, s, e);
            return max(
                    sum,
                    calc_1(array, s, e - 1),
                    calc_2(array, s + 1, e),
                    maxSubSequence(s + 1, e - 1, array)
            );
        }
    }

    private static int sum(int[] array, int s, int e) {
        int sum = 0;
        for (int i = s; i <= e; i++) {
            sum += array[i];
        }
        return sum;
    }

    private static int calc_1(int[] arr, int begin, int end) {
        int sum = arr[begin];
        int max = sum;
        for (int i = begin + 1; i <= end; i++) {
            sum += arr[i];
            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    private static int calc_2(int[] arr, int begin, int end) {
        int sum = arr[end];
        int max = sum;
        for (int i = end - 1; i >= begin; i--) {
            sum += arr[i];
            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    //求四个数的最大值.
    private static int max(int a, int b, int c, int d) {
        int[] array = {a, b, c, d};
        Arrays.sort(array);
        return array[array.length - 1];
    }

    private static int maxSubSequenceV2(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum += arr[j];
                if (max < currentSum) {
                    max = currentSum;
                }
            }
        }
        return max;
    }

    /**
     * O(n)解决问题:
     * 用到一个小技巧,下面总结下.
     * 找到数列中所有连续和>0的段.用sumEndingHere记录.sumEndingHere可能是递减的,
     * 但是我们用sumSoFar记录了迄今为止的最大值.
     * 当sumEndingHere小于0时候,设置为0,寻找下一连续段>0的.
     * 全负数无法处理,需要加额外坚持.
     */
    private static int maxSubSequenceV3(int[] arr) {
        if (isAllNegative(arr)) {
            Arrays.sort(arr);
            return arr[arr.length - 1];
        }
        int sumSoFar = 0, sumEndingHere = 0;
        for (int i = 0; i < arr.length; i++) {
            sumEndingHere += arr[i];
            if (sumEndingHere < 0) {
                sumEndingHere = 0;
                continue;
            }
            if (sumSoFar < sumEndingHere) {
                sumSoFar = sumEndingHere;
            }
        }
        return sumSoFar;
    }

    private static boolean isAllNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        int[] array = {1, -5, 8, 3, -4, 15, -8};
//        int[] array = {-2, 11, -4, 13, -5, 2};
        int[] array = {-2, -3, -4, -2, -3, -6};
        System.out.println(maxSubSequence(0, array.length - 1, array));
        System.out.println(maxSubSequenceV3(array));

    }


}
