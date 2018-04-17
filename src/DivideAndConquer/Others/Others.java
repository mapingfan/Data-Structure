package DivideAndConquer.Others;

/**
 * 判断某个数是否是某个整数的平方.如16是,15不是.
 */
public class Others {

    private static boolean judge(int n) {
        int i = 2;
        while (true) {
            int temp = i * i;
            if (temp == n) {
                return true;
            } else {
                i +=1;
            }
            if (i > (int) Math.sqrt(n)) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judge(16));

    }
}
