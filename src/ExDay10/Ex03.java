package ExDay10;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 单调栈结构.单调,就是指栈中元素有序,从大到小(从栈底依次到栈顶)
 * 应用:
 * 求数组中每个元素的离它最近的左边最大值,右边最近的最大值.
 */
public class Ex03 {

    @Data
    private static class ResultData {
        Integer value;
        Integer leftMax;
        Integer rightMax;

        public ResultData(Integer value, Integer leftMax, Integer rightMax) {
            this.value = value;
            this.leftMax = leftMax;
            this.rightMax = rightMax;
        }
    }

    //这个单调栈对于重复元素没有很好的解决,下面改进下这个版本.
    public static LinkedList<ResultData> solution(int[] array) {
        LinkedList<ResultData> help = new LinkedList<>();
        LinkedList<Integer> stack = new LinkedList<>();
        if (array == null) return null;
        int index = 0;
        while (index < array.length) {
            if (stack.isEmpty()) {
                stack.addFirst(array[index++]);
                continue;
            } else {
                while (!stack.isEmpty() && array[index] > stack.peekFirst()) {
                    ResultData resultData = new ResultData(stack.pollFirst(), stack.isEmpty() ? null : stack.peekFirst(), array[index]);
                    help.add(resultData);
                }
                stack.addFirst(array[index++]);
            }
        }
        while (!stack.isEmpty()) {
            ResultData resultData = new ResultData(stack.pollFirst(), stack.isEmpty() ? null : stack.peekFirst(), null);
            help.add(resultData);
        }
        return help;
    }

    //改进,可以处理数组中的重复元素情况.
    public static LinkedList<ResultData> solution_2(int[] array) {
        LinkedList<ResultData> help = new LinkedList<>();
//        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<LinkedList<Integer>> stack = new LinkedList<>();
        //要存放重复元素,栈中的元素类型就得修改了.
        if (array == null) return null;
        int index = 0;
        while (index < array.length) {
            if (stack.isEmpty()) {
                LinkedList<Integer> tmp = new LinkedList<>();
                tmp.add(index++); //只存入下标.
                stack.addFirst(tmp);
                continue;
            } else {
                while (!stack.isEmpty() && array[index] > array[stack.peekFirst().get(0)]) {
                    if (stack.peekFirst().size() == 1) {
                        ResultData data = new ResultData(array[stack.pollFirst().get(0)], stack.isEmpty() ? null : array[stack.peekFirst().get(0)], array[index]);
                        help.add(data);
                    } else {
                        LinkedList<Integer> tmp = stack.pollFirst();
                        while (tmp.size() != 0) {
                            ResultData data = new ResultData(array[tmp.pollFirst()], stack.isEmpty() ? null : array[stack.peekFirst().get(0)], array[index]);
                            help.add(data);
                        }
                    }
                }
                if (!stack.isEmpty() && array[index] == array[stack.peekFirst().get(0)]) {
                    stack.peekFirst().add(index++);
                } else {
                    LinkedList<Integer> tmp = new LinkedList<>();
                    tmp.add(index++);
                    stack.addFirst(tmp);
                }
            }
        }
        while (!stack.isEmpty()) {
                LinkedList<Integer> tmp = stack.pollFirst();
                while (tmp.size() != 0) {
                ResultData data = new ResultData(array[tmp.pollFirst()], stack.isEmpty() ? null : array[stack.peekFirst().get(0)],null);
                help.add(data);
            }
        }
        return help;
    }

    public static void print(LinkedList<ResultData> result) {
        if (result != null) {
            for (ResultData data : result) {
                System.out.println("value " + data.value + " left max " + data.leftMax + " right max " + data.rightMax);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 5, 5, 2, 4, 6, 2};
        print(solution_2(array));
    }
}
