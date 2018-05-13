package ExDay5;

/**
 * 输出一个序列的所有子序列.包括空序列.
 * 例如abc,
 * 输出应该有
 * 空,a,b,c ,ab,ac,bc,abc .一共八种.即所有子集.
 * 这个题目有很多种解法.
 * 两个指针+gap.
 * 第一个指针一开始指向序列起始位置;
 * 第二层循环是控制gap,每次加1.
 * 第三个指针也是,每次移动gap个位置.
 * 就在个三层循环就可以解决问题.
 * <p>
 * 但是这个思路不是重点,重点的是递归的思路.
 * <p>
 * 这个思路对我很重要,开启了一种全新的思路.
 * <p>
 * 对于abc,三个,从a,开始,有两种决策,选或者不选.会产生两种结果.这种结果对后面会有影响.
 */
public class Ex06 {

    /**
     * @param word
     * @param stage  阶段
     * @param result 这个阶段之前的后果.
     *
     *        这种阶段转移,并把上一阶段之前的结果传到下一阶段的思想太棒了.
     *        突然感觉有点茅塞顿开.
     *        对于以后这种分阶段的决策问题,是否可以借鉴这种思路呢?
     *        比如打印全排列.
     */
    public static void print_rec(String word, int stage, String result) {
        if (word == null) return;
        if (stage == word.length()) {
            System.out.println(result);
            return;
        }
        print_rec(word, stage + 1, result);
        print_rec(word, stage + 1, result += word.charAt(stage));
    }

    public static void print_iter(String word) {
        if (word == null) return;
        for (int i = 0; i < word.length(); i++) {
            for (int gap = 1; gap < word.length(); gap++) {
                for (int j = i; j < word.length(); j += gap) {
                    print(word, i, j, gap); //从i位置开始,安装gap输出.
                }
            }
        }
    }

    private static void print(String word, int i, int j, int gap) {
        if (gap == 1) {
            for (int k = i; k <= j; k += gap) {
                System.out.print(word.charAt(k));
            }
            System.out.println();
        } else {
            if (i != j) {
                for (int k = i; k <= j; k += gap) {
                    System.out.print(word.charAt(k));
                }
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
//        print_iter("abcd");
        print_rec("abc", 0, "");

    }

}
