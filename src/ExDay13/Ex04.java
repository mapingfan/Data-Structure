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


    public static ReturnData process(Node head) {

        if (head == null) {
            return new ReturnData(0, 0);
        }
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
