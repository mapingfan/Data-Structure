package TreeProblem.TreeToDLL;

import TreeProblem.TreeNode;

/**
 * 二叉树转化为循环双向链表.
 * 递归思路实现.首先二叉树有两个指针,这就有了成为双向链表的基础.
 * 递归转化左子树,右子树,然后把跟插入中间.
 * 先分析下特例:
 *      只有一个节点时: 直接设置该指针左右都指向自己.
 *      两个节点:左空,右不空.此时也简单.
 *      左右都不空,三个节点,也很简单.
 *
 */
public class Solution {

    private static TreeNode solution(TreeNode root,TreeNode tail) {
        return null;
    }

}
