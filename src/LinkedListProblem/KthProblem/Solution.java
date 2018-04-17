package LinkedListProblem.KthProblem;

import java.util.*;

/**
 * 求单链表中倒数第k个节点.
 * 分析下:
 * 最直观的思路,遍历一编,求出链表的长度N,然后第二轮直接顺序访问到N-k+1个节点.
 * <p>
 * 补充两个思路:
 * 1.空间换时间: 第一次遍历时,建立hash表,存储每个节点的位置.然后直接查找.
 * <p>
 * 2.双指针查找.下面实现下.
 */
public class Solution {

    //寻找倒数第k个节点,设k不越界.链表里存放整数.
    private static int kthProblem(List<Integer> list, int k) {
        Iterator<Integer> iterator = list.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        //访问count-k+1个元素.
        Iterator<Integer> other = list.iterator();
        int result = 0;
        for (int i = 1; i <= count - k + 1; i++) {
            result = other.next();
        }
        return result;
    }

    private static int solutionV2(List<Integer> list, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Iterator<Integer> iterator = list.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Integer result = iterator.next();
            map.put(index++, result);
        }
        int size = map.size();
        //直接访问第size-m+1个元素就好.
        return map.get(size - k + 1);
    }

    /**
     * 核心的想法,当第一个指针走到第K个元素时,这时候第二个指针与第一个指针正好相差k.
     * 那么第一个指针走到头,那么第二个指针正好指向倒数第k个元素.
     */
    private static int solutionV3(List<Integer> list, int k) {
        ListIterator<Integer> p1 = list.listIterator();
        ListIterator<Integer> p2 = list.listIterator();
//        两个迭代器指针.
        int countP1 = 0, countP2 = 0;
        while (p1.hasNext()) {
            if (countP1 - countP2 == k) {
                p2.next();
                countP2++;
            }
            p1.next();
            countP1++;
        }
        return p2.next();
    }


    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(kthProblem(list, 2));
        System.out.println(solutionV2(list, 1));
        System.out.println(solutionV3(list, 5));
    }

}
