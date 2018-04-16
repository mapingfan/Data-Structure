package DynamicProgramming.SubsetSumEqualK;

/**
 * 给定正数序列,是否存在子集,使得子集和等于k.
 * 分析:
 * 假设一共有n个数字,
 * 建立二维数组A[n][k],表示n个数字的自己和能否等于k.
 * 那么A[n][k] = A[n-1][k-a[n]]
 * 或者A[n][k] = A[n-1][k].
 * 只要有一个等于true,那么A[n,k]就等于true.
 * 考虑边界情况:A[0][i] = ? 取决a[0]==i,等就是T,不然就是F.
 * 对于A[i][0] = T.空集就好.
 * 这样边界清情况就处理完毕.
 * <p>
 * 其实递归式可能更好理解,但是殊途同归.
 * f(n,T)= f(n-1,T) or f(n-1,T-a[n]).
 * 边界值:n=1时,f(1,T) == T/F.具体情况判断.
 * T=0, return T.
 */
public class Solution {


    private static boolean solution(int k, int[] arr) {
        int len = arr.length;
        boolean[][] result = new boolean[len + 1][k + 1];
        //解释下数组为什么都要+1.那是为了方便表达.继续看.
        //result[0][0] = T
        //result[0][i] = F.
        //对于result[i][0] = T.
        result[0][0] = true; //空集和为0.
        for (int i = 0; i < len + 1; i++) {
            result[i][0] = true; //k=0,取空集就好.
        }
        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                if (j - arr[i - 1] >= 0) {  //注意这个地方数组取i-1.各自暗坑.
                    result[i][j] = result[i - 1][j] || result[i - 1][j - arr[i - 1]];
                }
            }
        }
        return result[len][k];
    }

    public static void main(String[] args) {
        int[] arr = {3, 25, 4, 19, 3, 7, 13, 10, 6, 11};
        System.out.println(solution(17, arr));
    }
}

