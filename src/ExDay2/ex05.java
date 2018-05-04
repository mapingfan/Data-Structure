package ExDay2;

import java.util.LinkedList;

/**
 * 两个队列实现栈.
 * 分析:
 *  如何定义出栈入栈操作是核心.
 *
 *  面试贱人:不给你栈,要你对图进行DFS.此时应该用两个队列构建一个栈,即可解决问题.
 *
 */
public class ex05 {
    private static class MyStack {
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> help = new LinkedList<>();
        //define the action of push .
        public void push(Integer obj) {
            queue.addLast(obj);
        }

        public Integer pop() {
            if (queue.isEmpty()) throw new RuntimeException("The stack is empty");
            //拷贝元素
            while (queue.size() >1) {
                help.addLast(queue.removeFirst());
            }
            Integer result = queue.removeFirst();
            //这个地方的交换操作就很皮了.建议学习.
            LinkedList<Integer> tmp = queue;
            queue = help;
            help = tmp;
            return result;
        }

    }


}
