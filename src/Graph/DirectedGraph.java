package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 有向图
 */
public class DirectedGraph {

    private final int MAX_VERTS = 20;
    private Vertex[] vertices;
    private int[][] adjMat = new int[MAX_VERTS][MAX_VERTS];
    private int[][] adjMatCopy = new int[MAX_VERTS][MAX_VERTS];
    private int[] arrayList = new int[MAX_VERTS];
    private int index ;
    private int nVerts;
    private LinkedList<Character> sortedArray = new LinkedList<>();


    public DirectedGraph() {
        vertices = new Vertex[MAX_VERTS];
        nVerts = 0;
        index = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
//        for (int i = 0; i < MAX_VERTS; i++) {
//            arrayList[i] = -1;
//        }
        Arrays.fill(arrayList, -1);
    }

    //    add a vertex to the graph
    public void addVertex(char label) {
        vertices[nVerts++] = new Vertex(label);
    }

    /**
     * @param start 在邻接矩阵中的下标
     * @param end   在邻接矩阵中的下标
     *              有向图不需要注意对称修改.这个地方没有做下标是否越界检查.
     */
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    static class Vertex {
        private char label;
        private boolean wasVisited;

        public Vertex(char lable) {
            this.label = lable;
            this.wasVisited = false;
        }
    }

    public void displayVertex(int v) {
        if (v < nVerts && v >= 0)
            System.out.println(vertices[v].label);
    }

    private boolean isInArray(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    /**
     * 算法思路分析:针对是有向无环图,进行拓扑排序.
     * 1.找出没有后继节点的顶点
     * 2.从图中删除这个顶点.在排序结果列表里插入它的label.
     */
    public void topo() {
        for (int i = 0; i < nVerts; i++) {
            System.arraycopy(adjMat[i], 0, adjMatCopy[i], 0, adjMat[i].length);
        }
        int i = findNoSuccessor();
        while (i != -1) {
            sortedArray.add(vertices[i].label);
            i = findNoSuccessor();
        }
        if (sortedArray.isEmpty()) {
            System.out.println("空");
        } else
            for (int size = sortedArray.size()-1; size >= 0; size--) {
                System.out.print(sortedArray.get(size)+" ");
            }
    }

    private int findNoSuccessor() {
        boolean isFound = false;
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                if (i != j&&!isInArray(arrayList,i)) {
                    if (adjMatCopy[i][j] != 0) {
                        isFound = false;
                        break;
                    } else {
                        isFound = true;
                    }
                }
            }
            if (isFound == true) {
//                找到了没有后继的节点.返回节点,并从图中删除.
                for (int l = 0; l < nVerts; l++) {
                    if (l != i&&!isInArray(arrayList,l)&&!isInArray(arrayList,i)) {
                        if (adjMatCopy[l][i] == 1) {
                            adjMatCopy[l][i] = 0;
                        }
                    }
                }
                arrayList[index++] = i;
                return i;
            }
        }
        return -1;  //有环,不存在后继节点.
    }


    public static void main(String[] args) {
        DirectedGraph theGraph = new DirectedGraph();
        theGraph.addVertex('A');    // 0
        theGraph.addVertex('B');    // 1
        theGraph.addVertex('C');    // 2
        theGraph.addVertex('D');    // 3
        theGraph.addVertex('E');    // 4
        theGraph.addVertex('F');    // 5
        theGraph.addVertex('G');    // 6
        theGraph.addVertex('H');    // 7

        theGraph.addEdge(0, 3);     // AD
        theGraph.addEdge(0, 4);     // AE
        theGraph.addEdge(1, 4);     // BE
        theGraph.addEdge(2, 5);     // CF
        theGraph.addEdge(3, 6);     // DG
        theGraph.addEdge(4, 6);     // EG
        theGraph.addEdge(5, 7);     // FH
        theGraph.addEdge(6, 7);     // GH
        theGraph.topo();            // do the sort
    }


}
