package TreeProblem.PrintPaths;

import TreeProblem.TreeNode;

/**
 * 输出所有从根节点到叶子节点的路径.
 * <p>
 * 分析下:
 * 刷出跟到子节点的路径 = 输出跟+输出子树到叶子节点的路径.
 * 大致思路是如此,下面代码实现下.
 */
public class Solution {

    private static void solution(TreeNode root) {
        if (root == null) return;
        if (root.getLeft() == null && root.getRight() == null) {
            System.out.print(root.getValue() + " ");
            System.out.println();
            return;
        }

        //跟到左子树的叶子节点路径.
        System.out.print(root.getValue() + " ");
        solution(root.getLeft());

        //跟到右子树的叶子节点路径.
        System.out.print(root.getValue() + " ");
        solution(root.getRight());

    }

    /**
     * 上面的版本是我安装自己的思路实现的,但是有点缺胳膊少腿.会缺失一些.
     * 我知道大体思路是对的.后来看了下答案,用记录代替输出的效果更好.
     *
     * 下面简单总结下思路:
     * 输出根到叶子的路径 = 根+子树到叶子的路径
     * 这个地方不要单纯的输出根.而是要用数组保存根.
     *
     * 输出条件,当前节点的左右子树都为空,这个时候可以输出了.
     *
     * 这个地方,因为用的数组提前保存了根节点,其后的输出中都带上了根节点.从而解决了问题.
     * 这个思路就是很好了,佩服前人.
     *
     */

    static int[] path = new int[1024];

    private static void solutionV2(TreeNode root, int index) {
        if (root == null) return;
        path[index++] = root.getValue();
            if (root.getLeft() == null && root.getRight() == null) {
                //输出数据.
                printPath(path, index);
            } else {
            solutionV2(root.getLeft(), index);
            solutionV2(root.getRight(), index);
        }
    }

    private static void solutionV2_wrap(TreeNode root) {
        solutionV2(root, 0);
    }

    private static void printPath(int[] path, int index) {
        for (int i = 0; i < index; i++) {
            System.out.print(path[i] + " ");
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

//        n6.setRight(n7);
        solution(root);

        solutionV2_wrap(root);

    }
}
