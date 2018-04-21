package QueueProblem.StackQueue;

import java.util.LinkedList;

/**
 * 用两个栈实现队列.直接写.
 */
public class Solution {
}

class QueueWithTwoStack {
    LinkedList<Integer> stackA = new LinkedList<>();
    LinkedList<Integer> stackB = new LinkedList<>();

    //定义入队操作.
    public void enqueue(Integer value) {
        if (stackA.isEmpty()) {
            stackB.addFirst(value);
        } else {
            stackA.addFirst(value);
        }
    }

    public Integer dequeue() {
        if (stackA.isEmpty() && !stackB.isEmpty()) {
            while (stackB.size() > 1) {
                stackA.addFirst(stackB.removeFirst());
            }
            Integer result = stackB.removeFirst();
            //在拷贝回去B.
            while (stackA.isEmpty()) {
                stackB.addFirst(stackA.removeFirst());
            }
            return result;
        } else if (!stackA.isEmpty() && stackB.isEmpty()) {
            while (stackA.size() > 1) {
                stackB.addFirst(stackA.removeFirst());
            }
            Integer result = stackA.removeFirst();
            while (stackB.isEmpty()) {
                stackA.addFirst(stackB.removeFirst());
            }
            return result;
        } else {
            //队列空,请先入队.
            return -1;
        }
    }

    //其实上面的实现有点繁琐了,直接优化下.
    private void enqueue_variant(Integer value) {
        //直接如A栈.
        stackA.addFirst(value);
    }

    //这个地方其实有个小技巧.

    /**
     * 出队列的时候,我们发现,当我们第一次把元素导入到另一个栈中,栈顶元素就是我们想要的.
     * 下次出对,直接从这个栈中取就好.
     *  也就是只有B为空才需要导入.
     *
     *  上面的写法就有点饶人了.来回导,开销有点大.
     *
     */
    private Integer dequeue_variant() {
        if (stackA.isEmpty() && stackB.isEmpty()) {
            throw new IllegalStateException("队列空");
        }
        if (!stackB.isEmpty()) {
            return stackB.removeFirst();
        } else {
            //把A的元素导入到B.
            while (stackA.size() > 1) {
                stackB.addFirst(stackA.removeFirst());
            }
            return stackA.removeFirst();
        }
    }


}
