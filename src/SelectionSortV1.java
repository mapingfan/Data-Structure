import java.awt.geom.Arc2D;
import java.util.Arrays;

public class SelectionSortV1 {
    public static void main(String[] args) {
        int[] array = {1,2,5,212,43,12,54,232,5};
        int[] temp = selectionSort(array);
        System.out.println(Arrays.toString(temp));
    }

    public static int[] selectionSort(int[] array) {
        int tmp_pos;
        for (int i = 0; i < array.length; i++) {
            tmp_pos = findMin(array,i,array.length-1);
            int temp = array[i];
            array[i] = array[tmp_pos];
            array[tmp_pos] = temp;
        }
        return array;

    }


    public static int findMin(int[] array,int begin, int end) {
        int min = array[begin];
        int result = begin;
        for (int i = begin+1; i <=end ; i++) {
            if (min > array[i]) {
                min = array[i];
                result = i;
            } else {
                continue;
            }
        }
        return result;
    }
}
