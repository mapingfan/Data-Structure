package ExDay5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 实现并查集结构.
 */
public class Ex02 {

    private static class Node {
    }

    public static class UnionFindSet {
        public HashMap<Node, Node> fatherMap = new HashMap<>();//k指向v,以v为父节点.
        public HashMap<Node, Integer> sizeMap = new HashMap<>(); //v表示以k的孩子个数+k自身.

        public void makeSet(List<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1); //初始时,队列中每个节点都指向1.所以大小也为1.
            }
        }

        /**
         * 判断两个节点a,b是否在同一个集合中.
         * 通过寻找各自的代表节点即可解决.如果代表节点相同,那么在同一个集合中.
         * 查找结束后,需要把链表扁平化.
         * 以a为例子,把的所有父节点都直接挂在代表下面.
         */
        public boolean find(Node a, Node b) {
            if (a == null || b == null) return false;
            Node r1 = findRepresent(a, fatherMap);
            Node r2 = findRepresent(b, fatherMap);
            if (r1 == null || r2 == null) return false;
            if (r1 == r2) return true;
            return false;
        }

        /**
         * @param a
         * @param b
         * @return 合并a, b所在的集合.
         */
        public Node union(Node a, Node b) {
            if (a == null && b != null) return b;
            if (a != null && b == null) return a;
            if (a == null && b == null) return a;
            Node r1 = findRepresent(a, fatherMap);
            Node r2 = findRepresent(b, fatherMap);
            if (r1 == r2) return r1; //已经在一个集合中,不用合并.
            else {
                //进行合并.如果r1代表的节点各数多,则把r2直接挂在r1下面.
                Integer sizeR1 = sizeMap.get(r1);
                Integer sizeR2 = sizeMap.get(r2);
                if (sizeR1 >= sizeR2) {
                    fatherMap.put(r2, r1);
                    sizeMap.put(r1, sizeR1 + sizeR2);
                    return r1;
                } else {
                    fatherMap.put(r1, r2);
                    sizeMap.put(r2, sizeR1 + sizeR2);
                    return r2;
                }
            }
        }


        //在hashMap中寻找a的代表节点.
        private Node findRepresent(Node a, HashMap<Node, Node> fatherMap) {
            LinkedList<Node> help = new LinkedList<>();
            if (fatherMap.containsKey(a)) {
                Node parent = fatherMap.get(a);
                while (a != parent) {
                    help.add(a);
                    a = parent;
                    parent = fatherMap.get(a);
                }
                //把a沿途节点直接挂在parent上.比如a->b->c->parent
                //改成a->parent,b->parent,c->parent.a的孩子不用管.
                for (Node node : help) {
                    fatherMap.put(node, parent);
                } //全部打平.
                return parent;
            }
            return null;
        }
    }
}
