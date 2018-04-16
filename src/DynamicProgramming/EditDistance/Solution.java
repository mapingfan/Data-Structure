package DynamicProgramming.EditDistance;

/**
 * 求最小编辑距离:两个串S,T 最小代价讲S变为T.
 * 有三种操作,插入,删除,替换.
 * 插入代价 ci,
 * 删除代价 cd,
 * 替换代价 cr.
 *
 * 记S的长度为m,T的长度为n.
 *
 * 如何定义问题????
 * 定义l(i,j),讲S的前i个字符变成T的前j个字符的最小代价.
 * l(i,j)= cd+l(i-1,j).  i>j,即S中多了,需要删除.
 * l(i,j)= l(i,j-1)+ci;  i<j,需要插入,
 * l(i,j) = l(i-1,j-1) S[i]=T[j],
 * l(i,j)　= l(i-1,j-1)+cr
 *
 * 把S[1--i] -->T[1--j],转换的代价.
 * 可能需要插入,删除,替换.
 * 其中i起初不一定等于j.
 * 如果S[i]=T[j],那么问题转换为把S[1--i-1] -->T[1--j-1].
 * 如果S[i]!=T[j],那么问题转换为把S[1--i-1] -->T[1--j-1],外加一个替换操作.
 * 递归定义代价,不用钻进长度的死窟窿.
 *
 *
 *
 */
public class Solution {
}
