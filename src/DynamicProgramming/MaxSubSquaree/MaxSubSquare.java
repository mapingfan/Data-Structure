package DynamicProgramming.MaxSubSquaree;

/**
 * 在一个0,1构成的矩阵中,找一个最大子方阵,全由1构成.
 * <p>
 * 0 1 1 0 1
 * 1 1 0 1 0
 * 0 1 1 1 0
 * 1 1 1 1 0
 * 1 1 1 1 1
 * 0 0 0 0 0
 * <p>
 * 最大方阵肉眼可见,但是如何用代码求呢?
 * <p>
 * 这个地方先把答案抄写下来.
 * 1) 原矩阵为A[m][n],基于这个矩阵构造一个L[m][n], L[i][j]表示包括A[i][j]在内的值全为1的子方阵的大小.
 * 2) 把L[m][n]的第一行第一设置同A[m][n]一样.
 * 3) 对于L中的其他元素,按照下面公司计算:
 * if(A[i][j]!=0){
 * L[i][j] = min(L[i-1][j],L[i-1][j-1],L[i][j-1])+1;
 * } else{
 * L[i][j] = 0;
 * }
 * 4)找出L[m][n]中的最大值,并且记录最大值的两个下标.
 * 5)根据找出的最大值,和连个下标.去A中输出子阵.
 * for(int i = max_i; i> max_i - max_L; i--){
 *      for(int j = max_j; j>max_j - max_L; j--){
 *          System.out.print(A[i][j]+" ");
 *      }
 *      System.out.println();
 * }
 *
 * 时空复杂度都是O(mn).
 * 查阅了许多资料,终于把这个问题搞明白了.
 * 可以参考这个链接:http://hijob0533.blog.163.com/blog/static/189626274201152943040374
 * 具体实现明天写.
 *
 *
 */
public class MaxSubSquare {
}
