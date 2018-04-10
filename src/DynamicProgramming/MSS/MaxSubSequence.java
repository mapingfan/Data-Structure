package DynamicProgramming.MSS;

import java.util.Arrays;

/**
 * 最大连续子列和问题.
 * 分析问题: 子列的起始下标为s,结束下标为e,数列为A,函数为f(s,e)
 * if A[s] < 0&&A[e]>0 > ----> f(s,e) = f(s+1,e)
 * if A[s] > 0&&A[e]<0 > ----> f(s,e) = f(s,e-1)
 * if s==e, 那么 return A[s];
 * <p>
 * 若包含i项,不包含j项 ->  f(i,j) = A[i]+f(i+1,j-1);  //分析错误.
 * 若包含i项,也包含j项 ->  f(i,j) = sum(arr,i,j);
 * 若不包含i项,但包含j项 -> f(i,j) = f(i+1,j-1)+A[j]  //分析错误.
 * 若不包含i项,不包含j项 -> (i,j)　＝　f(i+1,j-1).
 * <p>
 * <p>
 * 若包含i项,不包含j项 ->  f(i,j) = f(i,j-1);  -->
 * 若包含i项,也包含j项 ->  f(i,j) = sum(arr,i,j);
 * 若不包含i项,但包含j项 -> f(i,j) = f(i+1,j)
 * 若不包含i项,不包含j项 -> (i,j)　＝　f(i+1,j-1).
 * <p>
 * f(i,j)=max(A[i]+f(i+1,j-1),sum(arr,i,j),f(i+1,j-1)+A[j],f(i+1,j-1)).
 * if( i==j)
 * return A[i].
 * sum(i,j,arr) = .
 * max(a,b,c,d).
 */
public class MaxSubSequence {
    //f(i,j)=max(A[i]+f(i+1,j-1),sum(arr,i,j),f(i+1,j-1)+A[j],f(i+1,j-1)).
    private static int maxSubSequence(int s, int e, int[] array) {
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

    public static void main(String[] args) {
//        int[] array = {1, -5, 8, 3, -4, 15, -8};
//        int[] array = {-2, 11, -4, 13, -5, 2};
        int[] array = {1, -3, 4, -2, -1, 6};
        System.out.println(maxSubSequence(0, array.length - 1, array));

    }


}
