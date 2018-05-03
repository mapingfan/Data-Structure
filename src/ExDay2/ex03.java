package ExDay2;

/**
 * 给定一个数组,实现成栈,和循环队列.
 */
public class ex03 {

    private static class ArrayStack {
        Integer[] array;
        int size;

        public ArrayStack(int initSize) {
            if (initSize < 0) throw new IllegalArgumentException("The init size is less than 0");
            array = new Integer[initSize];
            size = 0;
        }

        /**
         * @return 栈顶元素, 但并不移除.
         */
        public Integer peek() {
            if (size == 0) throw new IllegalStateException("The stack is empty");
            return array[size - 1];
        }

        public void push(int obj) {
            if (size == array.length) throw new IllegalStateException("The stack is full");
            array[size++] = obj;
        }

        public Integer pop() {
            if (size == 0) throw new IllegalStateException("The stack is empty");
            return array[--size];
        }
    }

    /**
     * last指向队列中最后一个元素的后面.一开始队列为空,所以last指向0.
     * top指向对首元素.初始指向0.
     * size控制队列的长度.
     * 这样判断队列空,队列满,靠size变量来解决.不依赖top,last.
     */
    private static class ArrayQueue {
        Integer[] array;
        int top, last, size;

        public ArrayQueue(int initSize) {
            if (initSize < 0) throw new IllegalArgumentException("The init size is less than 0");
            array = new Integer[initSize];
            size = 0;
            top = 0;
            last = 0;
        }

        /**
         * @return 返回队列头元素, 但不移除.
         */
        public Integer peek() {
            if (size == 0) throw new IllegalStateException("The queue is empty");
            return array[top];
        }

        public void push(int obj) {
            if (size == array.length) throw new IllegalStateException("The queue is full");
            size++;
            array[last] = obj;
            last = last == array.length - 1 ? 0 : last + 1;
        }

        public Integer poll() {
            if (size == 0) throw new ArrayIndexOutOfBoundsException("The queue is empty");
            size--;
            int tmp = top;
            top = top == array.length - 1 ? 0 : top + 1;
            return array[tmp];
        }
    }


}
