package DynamicProgramming.Floyd;

/**
 * 今天总结一下佛洛依德算法.
 * 用途:计算图中任意两个点的最短路径,时间复杂度O(n^3).
 * 分析:
 * 图中共有[1-n]个节点,要计算任意两个节点(i,j)之间的最短距离,那么
 * 用A[i,j]存储i-j的最短距离.A[i][j]初始为原图的邻接矩阵.
 * 根据算法的描述,求解A[i][j]是一个迭代过程.
 * A0为初始化过程.
 * 假设第k-1次迭代,得到表Ak-1[i][j].
 * 那么第k次迭代,如何计算Ak[i][j]?
 * Ak[i][j] = min(Ak-1[i][j],Ak-1[i][k]+Ak-1[k][j]).
 * 因为每次迭代过程中,我们都会计算每个顶点的到所有其它顶点的最短距离.
 * 所以计算Ak[i][j]时,如果路过这个k点,  那么就可以用上一步的到k的最小距离来计算,不用重新计算.
 * 有了上面的计算公式,就可以实现了.
 * <p>
 * 需要对A[i][j]迭代n次,然能才能知道最终结果是不是最小的.
 * 这也就是用k,j,j的原因,而不是i,j,k.
 * <p>
 * 因为k是迭代过程,每次都是状态转移.
 */
public class Floyd {
    /**
     * @param src
     * @param m   m行
     * @param n   n列
     *            自己都自己距离是0.
     */
    private static void floyd(int[][] src, int m, int n) {
        int result[][] = new int[m][n];
        //堆result进行初始化工作.
        for (int i = 0; i < m; i++) {
            System.arraycopy(src[i], 0, result[i], 0, src[i].length);
        }
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (result[i][k] + result[k][j] < result[i][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }
    }
}
