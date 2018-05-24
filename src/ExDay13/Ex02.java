package ExDay13;

import lombok.AllArgsConstructor;

/**
 * 判断一棵树是否是平衡二叉树.
 * 分析:
 * 如果左右子树不平衡,那么就不平衡.
 * 如果左右平衡,需要看左右的高度,然后知道是否平衡.
 * <p>
 * 所以问题转化为求左右子树是否平衡和他们的高度.
 * <p>
 * 定义返回结构.
 */
public class Ex02 {

    @AllArgsConstructor
    private static class Node {
        Node left, right;
        int value;
    }


    @AllArgsConstructor
    private static class ReturnData {
        boolean isBalance;
        int height;
    }


    public static ReturnData process(Node head) {
        if (head == null) {
            return new ReturnData(true, 0); //空树是平衡树.
        }
        ReturnData lResult = process(head.left);
        ReturnData rResult = process(head.right);
        if (!lResult.isBalance || !rResult.isBalance) return new ReturnData(false, 0);
        if (Math.abs(lResult.height - rResult.height) <= 1) {
            return new ReturnData(true, Math.max(lResult.height, rResult.height) + 1);
        } else return new ReturnData(false, 0);
    }

}
