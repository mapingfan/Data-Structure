package ExDay2;

import java.util.LinkedList;

/**
 * 双栈队列
 * 分析:
 *  如何定义入队出队操作是核心
 */
public class ex06 {

    private static class MyQueue {
        LinkedList<Integer> data = new LinkedList<>();
        LinkedList<Integer> help = new LinkedList<>();

        public void push(Integer obj) {
            data.addFirst(obj);
        }
        //出队操作
        public Integer pop() {
            if (data.isEmpty()&&help.isEmpty()) throw new IllegalStateException("The queue is empty");
            if (!help.isEmpty()) {
                return help.removeFirst();
            } else {
                while (data.size() >1) {
                    help.addFirst(data.removeFirst());
                }
                return data.removeFirst();
            }
        }

    }
}
