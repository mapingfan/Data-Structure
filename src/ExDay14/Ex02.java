package ExDay14;

public class Ex02 {
    /**
     * 换钱问题.每个币值存在数组中,每个币值有无线张,可以随意使用.
     * 所有币值都是大于0.
     * <p>
     * 考虑最后一个面值,如果用了它,那么问题转换为求子问题.
     * 如果不用,也可以转换为求子问题.
     *
     * @param array
     * @param aim
     * @param n     表示有n种货币.
     * @return
     */
    public static int solution(int[] array, int aim, int n) {
        if (array == null || array.length == 0) return 0; //0种
        if (aim == 0) return 1;
        if (aim < 0) return 0;
        if (n == 0 && aim != 0) return 0;
       /* if (n == 1) { //只有一种货币.
            return aim % array[0] == 0 ? 1 : 0;
        }*/
        return solution(array, aim - array[n - 1], n) + solution(array, aim, n - 1);
    }

    public static void main(String[] args) {
        int[] array = {4};
        System.out.println(solution(array, 100, array.length));
    }
}
