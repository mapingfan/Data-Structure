package DynamicProgramming.布尔表达式括号问题;
//给布尔表达式加括号,使得最后的结果为T(True)

/**
 * 问题描述:可以输入n个T或者F,由And/Or/XOr连接,加括号改变运算顺序,使得最后结果为T.
 *
 * 现在取一个区间[i,j],[1,n]的子集.分析[i,j]的加括号问题.
 *
 * 括号K可以加在[i,j]的之间,也就是说k是移动的,对于每种k,都有一种结果,最后要合并结果.
 * 现在T(i,k)表示括号加在k处,使得此表达式为T.
 * 那么下面有三种策略:
 * 加And ,T(i,j) = Sum(T(i,k)*T(k+1,j)) ,k从i取到j-1.不能取到j,取到j,k+1越界.
 * 加Or,  T(i,j) = Sum(T(i,k)*Total(k+1,j))+Sum(F(i,k)*T(k+1,j));
 * 加Xor, T(i,j) = Sum(T(i,k)*F(k+1,j)+Sum(F(i,k)*T(k+1,j));
 *
 * Total(i,j)表示T+F.加括号使得成为T/F的所有情况.
 * Total(i,j) = T(i,j)+F(i,j).
 * 至此分析完毕.现在考虑边界条件,T(1,1) = 0|1.
 *
 *
 *
 */

public class Solution {
}
