package Heap;
//实现一下数据结构中的堆.
/**
 * 父节点下标:(X-1)/2;
 * 左子节点:2*x+1,右子节点:2*x+2 .
 * 所以的x从0开始.
 */

import java.util.ArrayList;

public class Heap {

    private ArrayList<Node> heapArray;


    public Heap() {
        heapArray = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heapArray.size() == 0;
    }

    public boolean insert(int key) {
        //construct a new node .
        //插入到数组最顶端,然后向上调整它的位置,找个一个比它大的父节点.最多找到跟.
        Node tmpNode = new Node(key);
        heapArray.add(tmpNode);
        int index = heapArray.size() - 1; //新节点在数组中的下标位置.
        //int parent = (index - 1) / 2;
        trickleUp(index, key);
        return true;

    }

    public int remove() {
        int value = heapArray.get(0).getiData();
        heapArray.get(0).setiData(heapArray.get(heapArray.size() - 1).getiData());
        trickleDown(0);
        heapArray.remove(heapArray.size() - 1);
        return value;
    }

    public boolean change(int index, int newValue) {
        if (index < 0) {
            return false;
        }
        int oldValue = heapArray.get(index).iData;
        heapArray.get(index).setiData(newValue);
        if (oldValue < newValue) {
            trickleDown(index);
        } else {
            trickleDown(index);
        }
        return true;
    }

    private void trickleDown(int index) {

        int largeChild;
        int top = heapArray.get(index).getiData();
        while (index < heapArray.size() / 2) {
            int left = 2 * index + 1;
            int right = left + 1;
            if (right < heapArray.size() && heapArray.get(left).getiData() < heapArray.get(right).getiData()) {
                largeChild = right;
            } else {
                largeChild = left;
            }
            if (top >= heapArray.get(largeChild).getiData()) {
                    break;
            }
            heapArray.get(index).setiData(heapArray.get(largeChild).getiData());
            index = largeChild;
        }
        heapArray.get(index).setiData(top);
    }

    private void trickleUp(int index, int key) {
        int parent = (index - 1) / 2;
        while (index > 0 && heapArray.get(parent).iData < key) {
            heapArray.get(index).setiData(heapArray.get(parent).getiData());
            index = parent;
            parent = (index - 1) / 2;
        }
        heapArray.get(index).setiData(key);
    }

    /**
     * 把一个数组调整成堆.
     *
     * @param index 数组下标.
     *              基于这样的理论.如果跟的左子树,右子树都是堆,那么只需要trickleDown跟节点即可.
     *              所以把一个数字调整成堆,只需要把跟的左右子树调整成堆,然后把跟节点向下挪到合适位置即可.
     *              因为递归的操作都是回到了trickleDown方法上,所以也可以改成循环.
     */
    public void heapify(int index) {
        if (index > heapArray.size() / 2 - 1) {  //基准条件,没有孩子节点递归结束.
            return;
        }
        heapify(2 * index + 2);  //把右边递归调整成堆.
        heapify(2 * index + 1);  //把左边递归调整成堆.
        trickleDown(index);            //把跟调整到合适的位置.
    }

    /**
     * 考虑这样的情况,只有三个点的树,若想调成最大堆,那么只需要对跟节点进行trickleDown方法就好.因为堆是弱序规则,所以只调整一个跟就能解决问题.
     * 比如:
     * 1
     * - -
     * =    -
     * 2       3
     * <p>
     * 若要成为最大堆,只需要对跟1进行向下调整就好.而我们又知,子堆可以合并成大堆.
     * 具体的图示可以参考 Robert Lafore 的java数据结构和算法.
     * 这个地方我们不需要从最后一个叶子节点开始,因为只有叶子节点无孩子,调整无意义.
     * 比较好的思路是从最后一个叶子节点的父节点开始.比如有15个节点,最后叶子节点编号是14,那么通过2*i+2/2*i+1可以计算出
     * 相应的父节点为6.直接从6一直调整到数组的开头.全部调整完毕.
     * <p>
     * --------------------------------------------------------------------------
     * 最简单的思路其实是调用insert方法,插入多少,自动成为堆.其中核心就是trickleUp方法,因为插入一个调用一次,
     * 故为O(n).上面的方法可以O(n/2).略有改善.
     * <p>
     * 有了建立堆的算法后,就可以快速实现堆排序算法.
     */

    public void heapSort(int[] arr) {
        //step 1:数组成堆.
        for (int i = 0; i < arr.length; i++) {
            this.insert(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            this.remove();  //降序排序,依次输出最大值.
        }

    }

    public void heapify() {
        for (int i = heapArray.size() / 2 - 1; i >= 0; i--) {
            trickleDown(i);
        }
    }


    static class Node {
        private int iData;

        public int getiData() {
            return iData;
        }

        public void setiData(int iData) {
            this.iData = iData;
        }

        public Node(int iData) {
            this.iData = iData;
        }

        @Override
        public String toString() {
            return iData + "";
        }
    }

    public static void main(String[] args) {
        Heap theHeap = new Heap();

        theHeap.insert(70);           // insert 10 items
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(0);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(990);
        System.out.println(theHeap.heapArray);
        for (int i = 0; i < 10; i++) {
            System.out.print(theHeap.remove()+" ");
        }

    }

}
