package Graph;

import java.util.LinkedList;

/**
 * 无向图,实现了深度,广度,最小生成树三个算法.
 */
public class UndirectedGraph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertices;
    private int[][] adjMat = new int[MAX_VERTS][MAX_VERTS];
    private int[][] adjMatCopy = new int[MAX_VERTS][MAX_VERTS];
    private int nVerts;

    //    用于辅助深度优先遍历.使用前后记得清空.
    private static LinkedList<Integer> stack = new LinkedList<>();

    public UndirectedGraph() {
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


    /**
     * 深度优先遍历的递归算法,我想了好久,最后看了下答案才实现.其实也和我最近看的回溯算法有关系,导致刚刚理解递归算法的时候一直
     * 在想这个算法什么适合回溯.其实我做的应该事类比二叉树的前序遍历算法.
     * 访问跟节点,递归访问根节点的左右子树.类比到图中,应该就是访问指定顶点,递归访问指定顶点的所有相邻顶点.
     * 图也就是多叉,并且回遇到相交,所以需要标志位判断是否已经访问过.
     */
    private void dfsV2(int v) {
        vertices[v].wasVisited = true;
        displayVertex(v);
        for (int i = 0; i < nVerts; i++) {
            if (i != v && vertices[i].wasVisited == false && adjMat[v][i] == 1) {
                dfsV2(i);
            }
        }

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

    public void warshall() {
        for (int i = 0; i < nVerts; i++) {
            System.arraycopy(adjMat[i], 0, adjMatCopy[i], 0, adjMat[i].length);
        }
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                for (int k = 0; k < nVerts; k++) {
                    if (i != j && k != j && k != i) {
                        if (adjMat[i][j] == 1 && adjMat[j][k] == 1) {
                            adjMatCopy[i][k] = adjMatCopy[k][i] = 1;
                        }
                    }
                }
            }
        }
//        输出新旧矩阵
        System.out.println("原矩阵");
        printMatrix(adjMat);
        System.out.println("闭包矩阵");
        printMatrix(adjMatCopy);
    }

    private void printMatrix(int[][] arr) {
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addEdge(0, 2);
        graph.addEdge(1, 0);
        graph.addEdge(3, 4);
        graph.addEdge(1, 4);
        graph.addEdge(4, 2);
        System.out.println("Visits: ");
        graph.warshall();
        System.out.println("---");
    }


}
