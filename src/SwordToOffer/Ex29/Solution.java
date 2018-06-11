package SwordToOffer.Ex29;


//求1-n的数字中1出现的次数.
//设计一个辅助函数计算任意一个数字中1出现的次数.
//
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            cnt += help(i);
        }
        return cnt;
    }

    private  int help(int i) {
        if (i==1) return 1;
        if (i<10) return 0;
        if (i % 10 == 1) return help(i / 10) + 1;
        else return help(i / 10);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().NumberOf1Between1AndN_Solution(13));
    }

}