package ExDay13;

import lombok.AllArgsConstructor;

/**
 * 二叉树套路专题.
 */
public class Ex01 {

    /**
     * 1.求一颗二叉树中,最大的搜索子树的大小.
     * 给定的树是任意树,不要理解错误.
     * 搜索子树是一颗BST树.
     * <p>
     * 分析:
     * 1.二叉树的问题一般都能递归解决.
     * 2.分析可能性:
     * 1.最大搜索子树出现在左子树
     * 2.最大搜索子树出现在左子树
     * 3.整棵树都是二叉搜索子树.
     * <p>
     * 我们需要的信息来确定到底在哪里.
     * 1.最大搜索子树大小,需要;
     * 2.左子树的最大值
     * 3.左子树的最小值.
     * 4.最大左子树的头.
     * 5.最大右子树的头.
     * <p>
     * 2,3,4,5用于判定是否连起来.左子树的最大值小于根,并且根小于右子树的最小值.
     * 1信息用于确定最大左子树在那边出现.
     * 注意2,3时左子树的最大值,不是最大搜索子树的最大值.
     * <p>
     * <p>
     * 有了这些信息就可以确定在哪里出现.
     */

    @AllArgsConstructor
    public static class ReturnData {
        int max;
        int min;
        int size;
        Node head;
    }

    @AllArgsConstructor
    public static class Node {
        int value;
        Node left, right;
    }


    public static ReturnData process(Node head) {
        if (head == null) return new ReturnData(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, head);
        ReturnData leftResult = process(head.left);
        ReturnData rightResult = process(head.right);
        if (leftResult.head == head.left && rightResult.head == head.right
                && leftResult.max < head.value && rightResult.min > head.value) {
            return new ReturnData(Math.max(rightResult.max, head.value),
                    Math.min(leftResult.min, head.value), leftResult.size + rightResult.size + 1, head);
        }
        return leftResult.size > rightResult.size ?
                new ReturnData(Math.max(Math.max(leftResult.max, rightResult.max), head.value), Math.min(Math.min(leftResult.min, rightResult.min), head.value), leftResult.size, leftResult.head) :
                new ReturnData(Math.max(Math.max(leftResult.max, rightResult.max), head.value), Math.min(Math.min(leftResult.min, rightResult.min), head.value), rightResult.size, rightResult.head);
    }

    //上面写的有点丑.
    public static ReturnData process_v2(Node head) {
        if (head == null) return new ReturnData(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, head);
        ReturnData leftResult = process(head.left);
        ReturnData rightResult = process(head.right);
        if (leftResult.head == head.left && rightResult.head == head.right
                && leftResult.max < head.value && rightResult.min > head.value) {
            return new ReturnData(Math.max(rightResult.max, head.value),
                    Math.min(leftResult.min, head.value), leftResult.size + rightResult.size + 1, head);
        }
        Node maxHead = leftResult.size > rightResult.size ? leftResult.head : rightResult.head;
        int maxSize = leftResult.size > rightResult.size ? leftResult.size : rightResult.size;
        return new ReturnData(Math.max(Math.max(leftResult.max, rightResult.max), head.value),
                Math.min(Math.min(leftResult.min, rightResult.min), head.value),
                maxSize, maxHead);

    }

}

