package ExDay1;

import java.util.Arrays;

/**
 * 堆相关练习.
 * 这个地方是数组堆.不是树堆.
 * <p>
 * 堆:(大顶堆)每个根节点大于其左右孩子节点.
 * 区别于二叉搜索树.
 */
public class ex03 {
    /**
     * 把index位置处的元素调整到堆中合适的位置.大根堆.
     * 从0开始,把0调整到合适位置,然后1进来,调整1.后来是2,后来是3.可以看见是一个相当于插入时保持堆结构.
     * 所以调整从下往上调.因为0-index-1处已经满足堆的条件.只需要把index往上调整到合适的位置即可.
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) { //当前节点值大于父节点值,需要交换上去.
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2; //观察交换后是否满足大跟堆条件.
        }
    }

    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) heapInsert(arr, i);
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }
    /**
     * 从堆中删除根元素,并且把剩余继续调整为堆结构.
     * 这个算法就证实我得思路了.通过删除调整来保持堆的性质也是可行的.
     */
    private static void delete(int[] arr) {
//        swap(arr, 0, arr.length - 1);
        int size = arr.length;
        for (int i = 0; i < arr.length; i++) {
            swap(arr, 0, --size);
            for (int i1 = 0; i1 < size; i1++) {
                heapInsert(arr, i1);
            }
        }
    }



    /**
     * 将0-heapSize-1处的index元素重新调整到堆的合适位置.
     * 因为这个元素是辅助堆排序的,所以index应该总是为0.也就是把根元素调整到合适的位置.
     * 往下调整.
     * 这个函数辅助堆排序.大根堆,每次把最大值根交换到最后一个位置,然后只需要把剩余的数组元素重新调整为堆即可.
     * 其实这个地方可以堆数组重新调用一遍插入算法,也能实现这个效果.
     * index要不要其实无所谓.写成index只是增加抽象成都,更兼容而已.
     * 即把index向下调整到堆中的合适位置.为什么不是向上调整,因为index一般都是根节点,所以只能向下调整.
     *
     * @param arr
     * @param heapSize
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest==index) break;
            swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int index, int i) {
        int tmp = arr[index];
        arr[index] = arr[i];
        arr[i] = tmp;
    }



    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        int[] array = generateRandomArray(20, 1000);
        int[] copyArray = copyArray(array);
        printArray(array);

        for (int i = 0; i < array.length; i++) {
            heapInsert(array, i);
        }
        delete(array);
        comparator(copyArray);
        System.out.println(Arrays.toString(array));

        if (isEqual(array, copyArray)) System.out.println("nice");
        else System.out.println("fucked");


//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            heapSort(arr1);
//            comparator(arr2);
//            if (!isEqual(arr1, arr2)) {
//                succeed = false;
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
//
//        int[] arr = generateRandomArray(maxSize, maxValue);
//        printArray(arr);
//        heapSort(arr);
//        printArray(arr);
    }


}
