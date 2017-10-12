import java.util.Arrays;

public class ArrayMerge {

    public static void main(String[] args) {
        int[] arr_a = {1,3,6,7,8};
        int[] arr_b = {4,6,7,8};
        int[] arr_c = null;
        arr_c = ArrayMerge.mergeArray(arr_a,arr_b);
        System.out.println(Arrays.toString(arr_c));
    }

    public static int[] mergeArray(int[] arr_a, int[] arr_b) {
        int[] arr_c = new int[arr_a.length+arr_b.length];
        int a_index = 0;
        int b_index = 0;
        int c_index = 0;
        while (a_index<arr_a.length&&b_index<arr_b.length) {
                if (arr_a[a_index] < arr_b[b_index]) {
                arr_c[c_index] = arr_a[a_index];
                a_index++;
                c_index++;
            } else {
                arr_c[c_index] = arr_b[b_index];
                b_index++;
                c_index++;
            }
        }
        if ( a_index >= arr_a.length) {
            while (b_index < arr_b.length) {
                arr_c[c_index] = arr_b[b_index];
                b_index++;
                c_index++;
            }
        }
        else {
            while (a_index < arr_a.length) {
                arr_c[c_index] = arr_a[a_index];
                a_index++;
                c_index++;
            }
        }
        return arr_c;
    }
}
