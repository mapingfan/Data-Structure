package Others;

/**
 * 补充一些小技巧,主要是移位操作.
 */
public class Others {

    //检测第K位是否为1.
    private static boolean kthBit(int n, int k) {
        return (n & (1 << k - 1)) == (int) (Math.pow(2, (k - 1)));
    }

    //将k位置为1.
    private static int setKthBit(int n, int k) {
        return n = n | (1 << k - 1);
    }

    //k位置为0.
    private static int setKthZero(int n, int k) {
        return n = n & (~(1 << k - 1));
    }

    //切换值为1的最右位:
    private static int flip(int n) {
        return n & n - 1;
    }

    //统计1的个数.
    private static int count(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
//        System.out.println(count(10));
        System.out.println(kthBit(10, 1));

    }
}
