package TreeProblem.AreMirrors;

import TreeProblem.TreeNode;

/**
 * 判断两棵树是否互为镜像.
 */
public class Solution {

    private static boolean judge(TreeNode rootA, TreeNode rootB) {
        if (rootA == null && rootB == null) return true;
        if (rootA == null || rootB == null) return false;
//        if (rootA.getValue() != rootB.getValue()) return false;
        return rootA.getValue()==rootB.getValue()&&
                judge(rootA.getLeft(), rootB.getRight()) &&
                judge(rootA.getRight(), rootB.getLeft());
    }

}
