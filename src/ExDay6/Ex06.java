package ExDay6;

import java.util.HashSet;

/**
 * 输出一个字符串的全排列.
 */
public class Ex06 {

    private static void print(char[] word, int i) {
        if (i == word.length) {
            System.out.println(String.valueOf(word));
        }
        HashSet<Character> set = new HashSet<>();
        for (int j = i; j < word.length; j++) {
            if (set.contains(word[j])) {
                continue;
            }
            set.add(word[j]);
            swap(word, i, j);
            print(word, i + 1);
            swap(word, i, j);
        }
    }

    private static void swap(char[] word, int i, int j) {
        char tmp = word[i];
        word[i] = word[j];
        word[j] = tmp;
    }

    /**
     * 定义一个函数,这个函数全排列好word数组中,i位置开始到最后一个位置的元素.
     * 那么p(word,i) = p(word,i+1) ,全排列好i+1到最后一个位置的元素.
     * 如果i到了最后一个元素,说明所有元素都安排好了,可以输出.
     * 这个递归和我以前学习的思路完全不同.
     * 仔细剖析下这个思路,这个题目很简单,如果只是死背下来,可能只要五分钟,但是我感觉始终有点膈应,
     * 所以花了很久来理解为什么这么写.虽然时间花的有点多,但真的是有了进步.
     * <p>
     * 这个题目是打印全排列.
     * 有一系列数字,需要把它安排在和是的位置.假设数字1-n.
     * 要输出这些数字的全排列,数学中午我们已经学过了.
     * 这也是一个决策过程,只有决策完成才需要输出.
     * 我们有一个思路模型,安排好第一个元素位置,递归安排好剩余n-1个元素.
     * 这个地方我的误区是,输出第一个数字,然后递归全排列剩余n-1个数字的元素.
     * 这样其实就错了,安排好第一个就输出,然后剩余n-1个数字的全排列就要共用第一个元素.
     * <p>
     * 这个思路有明显的缺陷.下面的思考就比较适合.
     * 排列n个元素=排列第一个元素+排列n-1个剩余的元素.
     * 递归何时终止,第n个元素也排列好了,递归就终止,
     * 这个地方,因为第一个元素是要变换的,有n个数字,就要变换n次.变换策略就是交换.
     * 所以递归函数很简单.
     * <p>
     * 之所以这个问题一直卡住我,主要是因为我第一次课上接触这个的时候就走入误区了.我一直想的是输出第一个元素,然后递归排雷n-1个元素.
     * 其实这样的话n-1个元素的全排列是公用第一个元素的.
     * <p>
     * f(word,i) = f(word,i+1)
     * 或者
     * f(word,n) = f(word,n-1).
     */
    private static void print_2(char[] word, int i) {
        if (i == word.length) {
            System.out.println(String.valueOf(word));
            return;
        }
        for (int j = 0; j < word.length; j++) {
            swap(word, i, j); //安排好第i个位置.
            print_2(word, i + 1); //递归安排i+1之后的位置.
            swap(word, i, j); //第一个元素安排好后还原.
        }
    }



    private static void print_3(char[] word, int i) {
        if (i == word.length) {
//            sb.append(word[i - 1]);

            System.out.print(word[i - 1]);
            return;
        }
        for (int k = 0; k < word.length; k++) {
            swap(word, i, k);
            System.out.print(word[i]);
//            sb.append(word[i]);

            print_3(word, i + 1);
            swap(word, i, k);
        }
    }


    public static void main(String[] args) {
        String word = "ab";
        print_3(word.toCharArray(), 0);
        System.out.println();

    }
}
