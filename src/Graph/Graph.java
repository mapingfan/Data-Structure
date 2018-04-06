package Graph;

import java.util.LinkedList;

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertices;
    private int[][] adjMat = new int[MAX_VERTS][MAX_VERTS];
    private int nVerts;

    //    用于辅助深度优先遍历.使用前后记得清空.
    private static LinkedList<Integer> stack = new LinkedList<>();

    public Graph() {
        vertices = new Vertex[MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    //    add a vertex to the graph
    public void addVertex(char label) {
        vertices[nVerts++] = new Vertex(label);
    }

    /**
     * @param start 在邻接矩阵中的下标
     * @param end   在邻接矩阵中的下标
     *              注意对称修改.这个地方没有做下标是否越界检查.
     */
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    static class Vertex {
        private char lable;
        private boolean wasVisited;

        public Vertex(char lable) {
            this.lable = lable;
            this.wasVisited = false;
        }
    }

    public void displayVertex(int v) {
        if (v < nVerts && v >= 0)
            System.out.println(vertices[v].lable);
    }

    //    返回顶点v相邻且没有被访问过的顶点.返回-1表示没有找到.
    public int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < nVerts; i++) {
            if (i != v) {
                if (adjMat[v][i] == 1 && vertices[i].wasVisited == false) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void dfs() {
        int start = 0;
        vertices[start].wasVisited = true;
        displayVertex(start);
        stack.addFirst(start);
        while (!stack.isEmpty()) {
            int adj = getAdjUnvisitedVertex(stack.peekFirst());
            if (adj != -1) {
                vertices[adj].wasVisited = true;
                displayVertex(adj);
                stack.addFirst(adj);
            } else {
//                如果当前节点没有可以访问的临界点,那么退回上一步,出栈一个元素.
                stack.removeFirst();
            }
        }
//        遍历结束,做收尾工作,把顶点数组的wasVisited设置false.方便下次遍历.
        for (int i = 0; i < nVerts; i++) {
            vertices[i].wasVisited = false;
        }
    }

    //递归遍历.思想如下,访问当前节点,然后递归访问当前节点的未访问过的邻接节点.
////    递归的出口是什么?
    private void dfsV2(int v) {
        if (vertices[v].wasVisited) {
            return;
        }
        vertices[v].wasVisited = true;
        displayVertex(v);

    }


    public void dfs(int v) {
        dfsV2(v);
        for (int i = 0; i < nVerts; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void bfs() {
        int init = 0; //假设从0顶点开始.
        stack.add(init); //此处虽然名字叫stack,但其实在模拟队列的功能.
        while (!stack.isEmpty()) {
//            出队列,并标记访问过.然后加入所有的邻接点.
            Integer first = stack.removeFirst();
            vertices[first].wasVisited = true;
            displayVertex(first);
            int adj;
//            把相邻顶点加入队列中.
            while ((adj = getAdjUnvisitedVertex(first)) != -1) {
                vertices[adj].wasVisited = true;
                stack.add(adj);
            }
        }
        for (int i = 0; i < nVerts; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void bfsV2() {
        int init = 0;
        int adj;
        vertices[init].wasVisited = true;
        displayVertex(init);
        stack.add(init);
        while (!stack.isEmpty()) {
            Integer first = stack.removeFirst();
            while ((adj = getAdjUnvisitedVertex(first)) != -1) {
                vertices[adj].wasVisited = true;
                displayVertex(adj);
                stack.add(adj);
            }
        }
    }

    public void mst() {
        stack.addFirst(0);
        vertices[0].wasVisited = true;
        while (!stack.isEmpty()) {
            Integer current = stack.peekFirst();
            Integer adj = getAdjUnvisitedVertex(current);
            if (adj != -1) {
                vertices[adj].wasVisited = true;
                displayVertex(current);
                displayVertex(adj);
                stack.addFirst(adj);
            } else {
                stack.removeFirst();
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        System.out.println("Visits: ");
        graph.mst();
        System.out.println("---");
    }


}
