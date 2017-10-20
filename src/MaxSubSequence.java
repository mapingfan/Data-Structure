/**
 * 求最大子序列和；
 * 暴力搜索求最大子列和；
 * O(n^3);
 */
public class MaxSubSequence {
    public static void main(String[] args) {
        int[] array = {1,2,-1,3,4,23,5,-12};
        System.out.println(maxSubSequenceSumV1(array));
        System.out.println(maxSubSequenceSumV2(array));
        System.out.println(maxSubSequenceSumV3(array,0,array.length-1));
        System.out.println(maxSubSequenceSumV4(array));

    }

    public static int maxSubSequenceSumV1(int[] array) {
        int maxSum = 0;
        int currentSum = 0;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                currentSum = 0;
                for (int k = i; k <=j ; k++) {
                    currentSum += array[k];
                }
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    begin = i;
                    end = j;
                }
            }
        }
        System.out.println(begin+" "+end);
        return maxSum;
    }

    public static int maxSubSequenceSumV2(int[] array) {
        int maxSum = 0;
        int currentSum = 0;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            currentSum = 0;
            for (int j = i; j < array.length; j++) {
                currentSum += array[j];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    begin = i;
                    end = j;
                }
            }
        }
        System.out.println(begin+" "+end);
        return maxSum;
    }

    public static int maxSubSequenceSumV3(int[] array,int begin, int end) {
        if (end == begin) {
            return array[begin];
        } else {
            int mid = (begin+end)/2;
            int left_max = maxSubSequenceSumV3(array,begin,mid);
            int right_max = maxSubSequenceSumV3(array,mid+1,end);
            int leftbordermax = 0;
            int currentleftbordermax = 0;
            int rightbordermax = 0;
            int currentrightbordermax = 0;
            for (int i = mid; i >=0 ; i--) {
                currentleftbordermax += array[i];
                if (currentleftbordermax > leftbordermax) {
                    leftbordermax = currentleftbordermax;
                }
            }

            for (int i = mid+1; i <=end ; i++) {
                 currentrightbordermax += array[i];
                if (currentrightbordermax > rightbordermax) {
                    rightbordermax = currentrightbordermax;
                }
            }
            int mid_max = leftbordermax+rightbordermax;
            if (left_max > right_max) {
                if (left_max > mid_max) {
                    return left_max;
                } else {
                    return mid_max;
                }
            } else {
                if (right_max > mid_max) {
                    return right_max;
                } else {
                    return mid_max;
                }
            }
        }
    }

    public static int maxSubSequenceSumV4(int[] array) {
        int maxSum = 0;
        int currentSum = 0;
        for (int i = 0; i < array.length; i++) {
            currentSum += array[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            } else if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }
}
