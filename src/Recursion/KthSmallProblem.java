package Recursion;


/**
 * 从一个数组中找出第k个最小项.不用排序算法解决.
 */
public class KthSmallProblem {
//分区算法,参考快速排序实现.
    private static int getPivotIndex(int[] arr, int low, int high) {
        int base = arr[low];
        boolean flag = true;
        while (low < high) {
            if (flag) {
                if (arr[high] < base) {
                    arr[low++] = arr[high];
                    flag = false;
                } else {
                    high--;
                }
            } else {
                if (arr[low] > base) {
                    arr[high--] = arr[low];
                    flag = true;
                } else {
                    low++;
                }
            }
        }
        if (low == high) {
            arr[low] = base;
        }
        return low;
    }

    /**
     * 寻找第Kth个最小数字.
     * @param arr 待查找数组.
     * @param k 第K个
     * @param begin 数组起始下标
     * @param end   数组结束下标
     * @return    第K个最小值.
     *
     * 犯了一个错误,导致调制半天.对于当begin=end = 2时,我想当然认为pivotIndex=0,导致递归失败.
     */
    private static int solution(int[] arr, int k, int begin, int end) {
        int pivotIndex = getPivotIndex(arr, begin, end);
        if (k - 1 == pivotIndex) {   //如果要找5个最大元素,而pivotIndex=4,因为piv从0开始,正好piv的第四个是第五个最大.
            return arr[pivotIndex];
        } else if (k - 1 < pivotIndex) {
            return solution(arr, k, begin, pivotIndex - 1);
        } else {
            return solution(arr, k, pivotIndex + 1, end);  //当k>pivotIndex时,直接查找就好,不要画蛇添足.
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        System.out.println(solution(arr, 3, 0, arr.length - 1));

    }


}
