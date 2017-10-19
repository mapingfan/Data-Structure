package Recursion;

public class FindMaxMin {
    public static void main(String[] args) {
        int[] array = {1,3,4,5,6,323,4,324,121,4334,1};
        Pair pair = FindMaxMin.findMaxMin(array,0,array.length-1);
        System.out.println(pair.max+" "+pair.min);
    }

    public static Pair findMaxMin(int[] array,int low, int high) {
        Pair pair = new Pair();
        if (high - low < 2) {
            if (array[low] > array[high]) {
                pair.max = array[low];
                pair.min = array[high];
            } else {
                pair.max = array[high];
                pair.min = array[low];
            }
        } else {
            int mid = (low+high)/2;
            Pair left_pair = findMaxMin(array,low,mid);
            Pair right_pair = findMaxMin(array,mid+1,high);
            /*if (left_pair.max > right_pair.max) {
                pair.max = left_pair.max;
            } else {
                pair.max = right_pair.max;
            }
            if (left_pair.min < right_pair.min) {
                pair.min = left_pair.min;
            } else {
                pair.min = right_pair.min;
            }*/
            pair.max = left_pair.max > right_pair.max ? left_pair.max : right_pair.max;
            pair.min = left_pair.min < right_pair.min ? left_pair.min : right_pair.min;
        }
        return pair;
    }
}

class Pair {
    int max;
    int min;
}