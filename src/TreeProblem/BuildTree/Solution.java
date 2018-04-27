package TreeProblem.BuildTree;

import TreeProblem.TreeNode;

/**
 * 根据前序和中序序列构建二叉树.
 * <p>
 * 根据前序和中序,可以确定根节点,然后得到两个子树的序列.
 * 然后递归生成子树.
 * 根据前序序列的第一个下标preIndex = 0,得到一个值,计算该值在中序序列的下标inIndex = ?
 *
 *
 * 稍微总结下根据遍历序列创建二叉树的问题:
 * 1.什么样的序列可以唯一确定一颗二叉树?
 * 对于前中后三个顺序,我们需要找到一个节点,能把左右两边的序列划分,然后递归生产.举个例子:
 * 前序(根左右)
 * 中序(左根右)
 * 根据前序,可以很简单的把中午切割为三部分: 左子树+根+右子树.
 * 凡是能做一部的,那么就可以根据序列生成唯一二叉树.因为对于左子树的遍历序列我们也有,可以递归这样进行.
 *
 * 举个反例:
 * 前序(根左右)
 * 后序(左右根)
 * 有了两个序列,我们可以确定跟是什么位置,但是无法划分出左右子树.所以无法唯一确定二叉树.
 *
 * 另外,中序+层次遍历也可以唯一确定一颗树.
 * 中午(左根右),层次第一个是根,可以划分出左右子树.
 * 然后就可以递归生成树.
 *
 *
 *
 * 其实这个问题最初让我有疑惑的地方是,每次把中序序列进行划分的时候,前序序列不需要进行划分?
 * 答案是不需要的.前序序列只需要挨个进行扫描即可.
 * 也就是说,根据中序生成树的时候,都是先生成左子树,最后才是右子树(因为扫描前序序列时就是这么扫描的).
 * 当前序序列缓冲层次时,那么可以能就是交叉进行了,先生成左子树,然后又转到右子树去了.
 *
 * 所以只需要设置两个变量,用于切割中午序列即可.对于前序,设置一个静态变量,每次递归时自增.
 */
public class Solution {

    static int preIndex = 0;


    private static TreeNode build(String preSequence, String inSequence,
                                  int inBegin, int inEnd) {
        if (inBegin > inBegin) {
            return null;
        }
        char c = preSequence.charAt(preIndex++);
        TreeNode newNode = new TreeNode(null, null, c);
        int inIndex = inSequence.indexOf(c);
        if (inBegin == inEnd) {
            return newNode;
        }
        newNode.setLeft(build(preSequence, inSequence, inBegin, inIndex - 1));
        newNode.setRight(build(preSequence, inSequence, inIndex + 1, inEnd));
        return newNode;
    }

    public static void main(String[] args) {
        String preSequence = "ABDECF";
        String inSequence = "DBEAFC";
        char c = preSequence.charAt(0);
        System.out.println(inSequence.indexOf(c));

    }
}
