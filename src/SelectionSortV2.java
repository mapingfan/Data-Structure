import java.util.Arrays;

public class SelectionSortV2 {

    public static void main(String[] args) {
        int[] array = {1, 3, 0, 99, 11, 22, 18, 0};
        SelectionSortV2.selectSort(array);
    }

    /**
     *
     * @param array 待排序数组；
     * 非降序排列数组；
     */
    public static void selectSort(int[] array) {
        int length = array.length;
        int temp;
        System.out.println("Before sort :");
        System.out.println(Arrays.toString(array));
        for (int i = 0; i <length-1; i++) {
            for (int j = i+1; j < length; j++) {
                if (array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                } else {
                    continue;
                }
            }
        }
        //排序结束输出结果：
        System.out.println("After sort :");
        System.out.println(Arrays.toString(array));
    }
}
