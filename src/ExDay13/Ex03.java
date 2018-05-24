package ExDay13;

import lombok.AllArgsConstructor;

/**
 * 求一颗二叉树上两个节点的最远距离.
 * <p>
 * 分析:
 * 最远节点出现在三个位置:
 * 在左子树上
 * 在右子树上
 * 横跨两颗子树.
 * <p>
 * 需要哪些数据来确定这些信息?
 * <p>
 * 1.最大深度.左子树的
 * 1.最大深度.右子树的
 * <p>
 * height,distance
 */
public class Ex03 {

    @AllArgsConstructor
    public static class ReturnData {
        int height;
        int distance;
    }


    public static ReturnData process(Node head) {
        if (head == null) {
            return new ReturnData(0, 0);
        }
        ReturnData leftResult = process(head.left);
        ReturnData rightResult = process(head.right);
        int maxDistance = leftResult.distance > rightResult.distance ? leftResult.distance : rightResult.distance;
        maxDistance = Math.max(maxDistance, leftResult.height + rightResult.height + 1);
        return new ReturnData(Math.max(leftResult.height, rightResult.height) + 1, maxDistance);
    }

    @AllArgsConstructor
    private static class Node {
        Node left, right;
        int value;
    }

}
