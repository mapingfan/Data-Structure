package ExDay14;

/**
 * 纸牌博弈问题.每次只能从两边拿牌.两个人都绝顶聪明,求最后能获胜的最大分数.
 * 假设A先拿.
 * 求最后获胜着的分数.
 * 分析:
 * 如果范围是i-j.
 * 如果A拿了i,那么下一次能拿的范围是(i+2,j)或者(i+1,j-1).
 * 最后求获得分数最多的人.
 *
 * 这个问题还是右一点小trick的.
 * 因为假设AB都是绝顶聪明.如果A先取,那么B会使得下一次取的收益最小化.
 * 所以当A进行下一次取的时候,要进行最小值匹配.
 *
 */
public class Ex03 {

    /**
     * @param array 放纸牌的数组
     * @param i     从i位置开始取.
     * @param j     边界是j,可以取到j.
     * @return 返回胜者的分数.
     * <p>
     * 不考虑最后是A胜还是B胜.只返回最大分数.
     */
    public static int solution(int[] array, int i, int j) {

        if (array == null || array.length < 1) return -1;
        if (i == j) return array[i];
        if (j - i == 1) return Math.max(array[i], array[j]);

        int aMaxFromI = Math.min(solution(array, i + 2, j), solution(array, i + 1, j - 1)) + array[i];
        //重点理解为什么这里是两个min,而不是max.因为B选取后,会让A的下一次选取收益最小.所以这个地方得注意.
        int aMaxFromJ = Math.min(solution(array, i, j - 2), solution(array, i + 1, j - 1)) + array[j];
        int aMax = Math.max(aMaxFromI, aMaxFromJ);
        //A能获得的最大分数.但A不应是赢家.所以应该比较总分-aMax与aMax的大小.
        return sum(array, i, j) - aMax > aMax ? sum(array, i, j) - aMax : aMax;

    }

    private static int sum(int[] array, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += array[k];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 100, 4};
        System.out.println(solution(array, 0, array.length - 1));
    }
}
