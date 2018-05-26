package ExDay13;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

public class Ex04 {
    private static class Node {
        int value;
        ArrayList<Node> nexts = new ArrayList<>();
    }

    @AllArgsConstructor
    private static class ReturnData {
        int present;
        int absent;
    }

    /**
     *  每个节点代表一个人,当前来,那么直接下属肯定不来.求最大活跃度.
     *  一个人下面可能有有多个下属.所以属于多叉树范畴.
     *
     *  其实这个看起来属于一个多阶段决策问题,但又不是线性的.对于这种问题如何解决?
     *
     *  对于头节点,我们需要知道他来或者不来时候的最大活跃度.
     *  如果头结点过来,那么最大活跃度转化为求以所有子节点为跟,且子节点不来的最大活跃度.
     *
     *  如果头结点不来,那么最大活跃度转化为求以所有跟节点所在子树,对于根节点来或者不来时候的最大活跃度.
     *
     *  这个地方如何定制返回值比较关键.
     *  我们需要根节点来的时候的最大活跃度,不来时候的最大活跃度.
     *  然后取两者中较大的那个即可.
     *  仔细揣摩问题.
     *
     *
     */
    public static ReturnData process(Node head) {
        int presentDegree = head.value;
        int absentDegree = 0;
        for (int i = 0; i < head.nexts.size(); i++) {
            ReturnData result = process(head.nexts.get(i));
            presentDegree += result.absent;
            absentDegree += Math.max(result.absent, result.present);
        }
        return new ReturnData(presentDegree, absentDegree);
    }

}
