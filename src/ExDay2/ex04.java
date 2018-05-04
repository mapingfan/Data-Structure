package ExDay2;


import java.util.LinkedList;

/**
 * 实现一个栈,可以实时获得栈中最小值.比如栈中一个元素时的最小值,5个元素值最小值.
 * 要求能在O(1)时间内获得.
 * 因为栈的最小值不一定在栈顶,而且栈无法直接操作栈顶之外的元素.根据经验,要获得时间效率,
 * 肯定得拿空间换时间.具体怎么换,就是本题的核心.
 *
 * 每次入栈时,保存辅助栈中保存当前的最小值即可.
 */
public class ex04 {

    private static class MyStack {
        private LinkedList<Integer> data = new LinkedList<>();
        private LinkedList<Integer> help = new LinkedList<>();

        //define the action of push and pop .

        public void push(Integer obj) {
            if (help.isEmpty()) help.addFirst(obj);
            else if (help.peekFirst() > obj)
                help.addFirst(obj);
            else help.addFirst(help.peekFirst());
            data.addFirst(obj);
        }

        /**
         * define the action of pop
         */
        public Integer pop() {
            if (data.isEmpty()) throw new IllegalStateException("The stack is empty");
            data.removeFirst();
            return help.removeFirst();
        }

        public Integer getMin() {
            return help.peekFirst();
        }
    }

}
