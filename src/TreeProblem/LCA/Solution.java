package TreeProblem.LCA;

import TreeProblem.TreeNode;

/**
 * 两个节点的最近公共祖先.
 * 首先可以观察到这样一个现在,如果两个节点,一个在根的右边,一个左边,那么最近公共祖先一定是根了.
 * <p>
 * 其次是否可以递归解决这个问题?
 * <p>
 * 节点A,B,可能有三种出没情况.一个都在跟的左子树上,都在右子树上,两边都有(这个答案已经确定).
 * <p>
 * 所以可以看出,这个地方有递归模型.
 * 下面实现试试
 */
public class Solution {
    //寻找AB的最近公共祖先节点.AB为空先不考虑.
    private static TreeNode lca(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) return null;
        if (root == A || root == B) {
            return root;
        }
        TreeNode left = lca(root.getLeft(), A, B);
        TreeNode right = lca(root.getRight(), A, B);
        if (left != null && right != null) return root;
        else return left == null ? right : left;
    }

    //下面安装自己的思路实现下.
    private static TreeNode lcaV2(TreeNode root, TreeNode a, TreeNode b) {
        //判读a,b在根的哪边.如果a,b是根节点,直接返回根就好.
        if (root == a || root == b) {
            return root;
        }
        boolean r2 = isLeft(root, b);
        boolean r1 = isLeft(root, a);
        if (r1 && r2) {
            return lcaV2(root.getLeft(), a, b);
        } else if (!r1 && !r2) {
            return lcaV2(root.getRight(), a, b);
        } else return root;
    }

    private static boolean isLeft(TreeNode root, TreeNode node) {
        TreeNode result = findNode(root.getLeft(), node);
        if (result != null) {
            return true; //左
        }
        return false; //对于node根本不存在先不考虑.
    }

    private static TreeNode findNode(TreeNode root, TreeNode node) {
        if (root == null) return null;
        if (root == node) return root;
        TreeNode result;
        result = findNode(root.getLeft(), node);
        if (result != null) {
            return result;
        } else {
            return findNode(root.getRight(), node);
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode root = n1;

        n1.setLeft(n2);
        n1.setRight(n3);

        n2.setLeft(n4);
        n2.setRight(n5);

        n3.setLeft(n6);
        n3.setRight(n7);
        TreeNode node = lcaV2(root, n4, n3);
        System.out.println(node.getValue());

    }
}
