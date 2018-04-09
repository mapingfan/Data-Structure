package DynamicProgramming.Fib;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 通过这个问题引入动态规划.这个问题可以说是动态规划最简单的例子,但是很有助于理解内涵.
 * fib(n) = fib(n-1)+fib(n-2); n>1;
 * fib(1)=fib(0)=1;
 * <p>
 * 当我们计算fib(5) = fib(4)+fib(3); 然后还有递归计算fib(4),但是fib(4)=fib(3)+fib(2).fib(3)其实已经计算过了,重复的计算
 * 会让算法的效率十分低下.所以可不可以有一种办法,把已经计算出来的子问题结果保存下,也就是备忘录方法.然后当用到的时候直接
 * 查表取出相应的值.
 *
 * 通过这个例子可以引入动态规划,粗略的理解就是找递归方程,找边界条件(类似递归).但是对于递归过程中需要重复的计算方法,查表获得,
 * 而不是再次计算.通过查表获得性能替升.
 *
 * 动态规划要求具有重叠子问题,否则用备忘录方法,起不到任何加速效果,因为两个子问题独立,那么没办法利用查表提升性能.
 *
 * 动态规划核心:找递归方程,边界条件.
 * 步骤:
 * 1.观察是否具有重叠子问题
 * 2.观察是否具有最优子结构.意思就是说大问题的最优解包含子问题的最优解.这是一种反向策略.对比贪心,是子问题最优,构成大问题也最优.
 * 贪心的这种策略是需要证明的,即子问题最优,大问题也是最优嘛?
 *
 * 找递归方程,设计到如何定义问题,如何用函数定义问题,新手只能考多多接触此类题目来找感觉.
 *
 *
 */
public class Fib {


    private static long rec_fib(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return rec_fib(n - 1) + rec_fib(n - 2);
        }
    }

    //    上面是fib的典型解法,效率是O(2^n),可以说是十分的低效了.
//    考虑自底向上使用动态规划方法进行计算: 所谓自底向上,意思就是,数组的下标从小到大递增,利用小的计算大的.

    private static long dp_fib(int n) {
        ArrayList<Long> memory = new ArrayList<>(n + 1);
        memory.add(0, 1L);
        memory.add(1, 1L);
        for (int i = 2; i <= n; i++) {
            memory.add(i, memory.get(i - 1) + memory.get(i - 2));
        }
        return memory.get(memory.size() - 1);
    }


    static long[] dp = new long[1000]; //演示使用.

    static {
        Arrays.fill(dp, 0);
        dp[0] = dp[1] = 1;
    }
    //    自顶向下合并.
    // 这个时候,但我们计算fib(5)=fib(4)+fib(3)时候,fib(4)=fib(3)+fib(2).fib(3)一旦被计算处理,就会被放入数组中,
//  方便以后查表计算.效率是比最开始的递归要高的,但是不如第二个版本用数组.
    private static long dp_fibV2(int n) {
        if (n == 0 || n == 1) {
            return dp[n];
        } else {
            if (dp[n] != 0) {
                return dp[n];
            } else {
                return dp[n] = dp_fibV2(n - 1) + dp_fibV2(n - 2);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        System.out.println(rec_fib(100));
//        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        System.out.println(dp_fibV2(660));
        System.out.println(System.currentTimeMillis() - start);
    }

}
