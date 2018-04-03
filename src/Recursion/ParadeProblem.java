package Recursion;

/**
 * 对于游行问题,队伍有两种角色组成,单车和乐队,共n组.
 * 要求两组乐队不能排列在相邻位置,会发生吵架.
 * 现在要求有多少种队伍排列方式.
 * 假设有P(n)种排列方式.
 * 如下考虑问题:
 * 排列队伍的最后一组是单车,共有B(n)种,
 * 排列队伍的最后一组是乐队,共有F(n)种,
 * 故P(n)=B(n)+F(n);
 * 分别考虑B(n);B(n)=B(n-1)+F(n-1) ,B(n-1)表示倒数第二个也是单车,F(n-1)表示倒数第二个是乐队.
 * 考虑F(n)=B(n-1);
 * 所以P(n)=P(n-1)+P(n-2)
 * 考虑n=1时,P(1)=2;
 * n=2时,P(2)=3;单车,单车/单车,乐队/乐队,单车
 */

public class ParadeProblem {

    /**
     * @param n n个队伍的排列.
     * @return 总的排列方式和
     */
    private static int solution(int n) {
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            return 3;
        }
        return solution(n - 1) + solution(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(solution(3));
    }

}
