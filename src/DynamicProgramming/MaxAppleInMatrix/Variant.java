package DynamicProgramming.MaxAppleInMatrix;

/**
 *最多苹果变种.
 * 上一题只能往下或者往右走,现在可以往对角线走.
 * 分析:
 * M(i,j) = A[i][j] + M(i-1,j), i>=1 j>=1
 * M(i,j) = A[i][j] + M(i-1,j-1),
 * M(i,j) = A[i][j] + M(i,j-1),
 * 三个取最大那个.
 * 边界值.
 * i =0 时, M(i,j) = sum_row(数组第一行至j)
 * j =0 时, M(i,j) = sum_row(数组第一列,至i)
 * 具体代码就不实现了.
 */
public class Variant {
}
