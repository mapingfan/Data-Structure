package TreeProblem.JudgeTree;

import TreeProblem.TreeNode;

/**
 * 判断两棵树是否相同
 */
public class Solution {

    private static boolean judge(TreeNode rootA, TreeNode rootB) {
        if (rootA == rootB && rootA == null) return true;
//        if (rootA == null && rootB != null) return false;
//        if (rootA != null && rootB == null) return false;
        //优化写法,因为上面已经排序了两个都为null的情况.这样一行解决两行的问题.
        if (rootA == null || rootB == null) return false;
        /*if (rootA.getValue() == rootB.getValue()) {
            boolean judge = judge(rootA.getLeft(), rootB.getLeft());
            if (!judge) return !judge;
            else return judge(rootA.getRight(), rootB.getRight());
        } else return false;*/
        //优化写法

        return (rootA.getValue() == rootB.getValue())
                && judge(rootA.getLeft(), rootB.getLeft())
                && judge(rootA.getRight(), rootB.getRight());
    }

}
