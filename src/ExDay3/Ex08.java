package ExDay3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 折纸打印.从上到下打印折痕.
 */
public class Ex08 {

    private static class Node {
        String string;

        public Node(String string) {
            this.string = string;
        }
    }


    private static void print(int num) {
        if (num < 1) return;
        int size = (1 << num) - 1;
        ArrayList<Node> arrayList = new ArrayList<>(size);
        arrayList.add(new Node("Down"));
        for (int i = 1; i < size; i++) {
            if (i % 2 != 0) arrayList.add(new Node("Down"));
            else arrayList.add(new Node("Up"));
        }
        //中序输出这个列表.
        int parent = 0;
        LinkedList<Node> stack = new LinkedList<>();
        while (true) {
            while (parent < size) {
                stack.push(arrayList.get(parent));
                parent = parent * 2 + 1;
            }
            if (stack.isEmpty()) {
                break;
            } else {
                Node pop = stack.pop();
                System.out.print(pop.string + " ");
                int i = arrayList.indexOf(pop);
                parent = 2 * i + 2;
            }
        }


    }

    public static void main(String[] args) {
        print(3);
    }
}
