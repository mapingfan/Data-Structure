package FastPower;

/**
 * 今天好好总结下快速幂算法.
 * 最初接触到快速幂,是听同学说计算斐波那契问题时,时间复杂度被卡住了.
 * 需要O(lgn).但是常见的做法就是O(n),后来了解到有个矩阵快速幂算法
 * 进而知道了快速幂算法.对于这个算法,网上有不少资料,但都是列举一些代码,
 * 没有一些分析,看的云山雾绕.
 * <p>
 * 自己花了点功夫,进行一个详细总结.
 * <p>
 * 对于一个数字n,如果他是奇数,那么二进制表示的最后一位一定是1.
 * 因为从倒数第二位开始,都是2的幂,(至少你2^1).这肯定是个偶数了,
 * 根据我浅薄的数学知识,偶数+偶数还是偶数.而乘法可以用加法表示,
 * 所以如果最后一位不是1,那么肯定不会出现奇数的.
 * <p>
 * 上面只是一个简单的铺垫.
 * <p>
 * 下面举个例子: 计算3^10, 10的二进制表示为1010.
 */
public class Solution {
    //计算a^b.
    private static long fastPower(int a, int b) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) != 0) {
                res *= a;
            }
            b = b >> 1;
            a *= a;
        }
        return res;
    }

    /**
     * 下面对上面的代码进行逐行解释:
     * 这个地方计算大家熟知的数字:2^10 = 1024.  10 = 1010;
     * 进入循环,判断当前指数的最后一位不是1,不进入if语句;
     * b右移,相当于b/=2.然后 得出a^2;
     * <p>
     * 进入下一轮循环,进入if,res更新,res = a^2;
     * 然后移位,b>>1,a =a^4.
     * <p>
     * 下一轮,if不成立, b>>1 , 此时a = a^8.
     * <p>
     * 下一轮,if成立,res = a^2*a^8, b>>1后,b=0,a=a^16.
     * <p>
     * 循环退出,得出结果.
     * <p>
     * 其实2^10 也可以这样分析:
     * <p>
     * 1 --  0 --  1 --  0
     * <p>
     * 2^16(10000)  2^8(1000)   2^4(100)   2^2(10)
     * <p>
     * 可以看出,所以1对应右斜下方,乘起来就是 2^10 .
     * <p>
     * 也就是没遇到二进制表示中有1的时,res的值就要更新. 乘的值时上次的值.
     * 基于这个思路,就可以理解快速求幂算法了.
     * <p>
     * 也就是遇到二进制中的1,就把res结果更新下.
     * 第二位有1,也就是乘上2^2,第四位有1,也就是2^8.
     * <p>
     * <p>
     * 基于这个问题,可以类比下矩阵的快速乘法.
     * <p>
     * 计算A^10 (不拆成分块矩阵)
     * <p>
     * A^(1010).
     * <p>
     * 首先定义下矩阵的乘法运算规则.没有这个什么都不存在.
     * 那么遇到第一个1时,res = A^2.
     * 遇到最后一个1时, 再次更新res = A^10.
     * 说了 这么多,下面实战下.
     */
    //定义最基础的乘法操作.
    private static long[][] multiply(long[][] matA, long[][] matB) {
        long[][] result = new long[matA.length][matB[0].length];
        //若A = p*q,B = q*r,则result = p*r .
        int p = matA.length;
        int q = matA[0].length;
        int r = matB[0].length;
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < q; k++) {
                    result[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }
        return result;
    }

    //计算A^k. A必须是方阵,否则无法进行计算.
    private static long[][] fastPower(long[][] mat, int k) {
        long[][] result = new long[mat.length][mat.length];
        //把result初始化为单位矩阵.
        for (int i = 0; i < mat.length; i++) {
            result[i][i] = 1;
        }


        while (k != 0) {
            if ((k & 1) != 0) {
                result = multiply(result, mat);
            }
            k = k >> 1;
            mat = multiply(mat, mat);
        }
        return result;
    }


    private static void print(long[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        long[][] matA = {
                {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}
        };

        long[][] result = fastPower(matA, 3);
//        long[][] result = multiply(matA, matA);
        print(matA);
        System.out.println();
        print(result);
    }

}
