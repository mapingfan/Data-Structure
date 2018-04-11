package DynamicProgramming.Jump;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * 问题描述:
 * 已经知道一个数组,从第一个元素经过若干跳到大最后一个元素,一跳的最大长度是数组当前位置的值.
 * 最优的结果是以最少的跳数到达目标.
 * 分析:
 * 如果数组的第一个元素是0的话,那么无法进行任何一跳,直接退出程序执行.
 * 设j(n)代表跳到第n个元素的最少跳数.
 * 那么
 * j(n) = j(n-1) + 1, A[n]>0,并且从0-n-1可达,即j(n-1)!=MAX.
 * j(n)　= j(n-2) +1 , A[n-2]>1,并且j(n-2)!=Max.
 * j(n) = j(n-3)+ 1,  A[n-3]>2 ,j(n-3)!=Max
 * ...
 * j(n) = j(n-i)+1,   A[n-i]>i-1,j(n-i)!=Max
 * <p>
 * j(n)的上一跳可能来自任何位置i,只要i到n可达.
 * j(n) = min(j(i))+1 ,A[i]>n-i
 */
public class Jump {
    private static int jump(int[] arr, int n) { //n代表n个元素.
        if (n == 0) {
            System.out.println("不可达");
            return Integer.MAX_VALUE;
        }
        if (arr[0] == 0 || arr[n - 1] == 0) {
            System.out.println("不可达");
            return Integer.MAX_VALUE;
        }
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            if (arr[0] != 0) {
                return 1;
            } else {
                System.out.println("不可达");
                return Integer.MAX_VALUE;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= n - 1 - i) {
                int temp = jump(arr, i + 1);
                if (min > temp) {
                    min = temp;
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            System.out.println("不可达");
            return Integer.MAX_VALUE;
        } else {
            return min + 1;
        }

    }

    private static int dp_jump(int[] arr) {  //dp[i]跳到第i+1个元素的最小跳数.
        if (arr[0] == 0) {
            return Integer.MAX_VALUE;
        } else {
            int[] dp = new int[arr.length];
            dp[0] = 0;
            dp[1] = 1;

            for (int i = 2; i < arr.length; i++) {
                int min  = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (dp[j] != Integer.MAX_VALUE && arr[j] >= (i - j)) {
                        if (min > dp[j]) {
                            min = dp[j];
                        }
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    dp[i] = min + 1;
                } else{
                    dp[i] = Integer.MAX_VALUE;
                }
            }
            return dp[arr.length - 1];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 0, 4};
//        int[] arr = {1, 1, 1, 1, 1};
        System.out.println(dp_jump(arr));
    }


}
