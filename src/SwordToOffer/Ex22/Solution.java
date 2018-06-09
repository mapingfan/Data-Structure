package SwordToOffer.Ex22;

public class Solution {
    public boolean VerifySquenceOfBST(int[] sequence) {
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    public boolean VerifySquenceOfBST(int[] sequence, int start, int end) {
        //在sequence中找到第一个比序列最后一个元素大的下标.
        if (sequence == null || end == start) return true;
        if (start > end) return false;
        int pos = finFirstGreatThanLast(sequence, start, end);
        if (pos == -1) { //没有右子树.
            if(!check(sequence,start,end,pos)) return false;
            return VerifySquenceOfBST(sequence, start, end - 1);
        } else if (pos == start) { //没有左子树.
            if (!check(sequence,start,end,pos)) return false;
            return VerifySquenceOfBST(sequence, pos, end - 1);
        } else {
            if(!check(sequence,start,end,pos)) return false;
            return VerifySquenceOfBST(sequence, start, pos - 1) && VerifySquenceOfBST(sequence, pos, end - 1);
        }
    }

    private boolean check(int[] sequence, int start, int end, int pos) {
        if (pos == -1) {
            return true;
        } else if (pos == start) {
            for (int i = start; i < end; i++) {
                if (sequence[i] < sequence[end]) return false;
            }
            return true;
        } else {
            for (int i = start; i <pos ; i++) {
                if (sequence[i]>sequence[end]) return false;
            }
            for (int i = pos; i <end ; i++) {
                if (sequence[i]<sequence[end]) return false;
            }
            return true;
        }
    }

    private int finFirstGreatThanLast(int[] sequence, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (sequence[i] > sequence[end]) {
                return i;
            } else continue;
        }
        return -1; //说明没有找到比最后一个元素大的,表明没有右子树.
    }
}