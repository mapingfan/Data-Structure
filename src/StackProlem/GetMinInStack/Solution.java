package StackProlem.GetMinInStack;
//设计一个栈,可以在O(1)时间获得最小值.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 因为入栈顺序是随机的,也无法进行排序.
 * 要想获得常数效率,要用空间换时间.
 * <p>
 * 其实这样想问题就很好分析:
 * <p>
 * 我们想获得栈中任意时刻的最小值.
 * 三个元素和五个元素的栈的最小值不一定相同.
 * 我们只要每次插入时,保存当前栈的最小值即可.
 * <p>
 * 这个地方就不写具体实现了,只分析下具体的思路:
 * <p>
 * 考虑n个元素栈的最小值,想要常数时间获得,扫描栈肯定是不行的,只能查表.
 * n个元素栈的最小值 = (n-1)个元素栈的最小值或者就是第n个元素自身.
 * <p>
 * 所以我们的策略是建立一个辅助栈:
 * 第一次入栈,这个元素同时加入辅助栈.
 * 第二个元素入栈,将当前元素与辅助栈中的栈顶元素比较.
 * 如果第二个元素小,则将第二个元素入栈,否则将栈顶元素拷贝一份放到辅助栈中.
 * <p>
 * 对后续元素都是如此处理.这是可以始终保持辅助栈中都是当前栈规模最小的值,
 */

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> mainStack = new LinkedList<>();
        LinkedList<Integer> assistStack = new LinkedList<>();
        int value;
        System.out.println("输入-1停止录入数字.");
        while ((value = scanner.nextInt()) != -1) {
            mainStack.addFirst(value);
            if (assistStack.isEmpty()) {
                assistStack.addFirst(value);
            } else {
                assistStack.addFirst(value < assistStack.peekFirst() ? value : assistStack.peekFirst());
            }
        }

        System.out.println("当前栈的最小值是:" + assistStack.peekFirst());


    }
}
