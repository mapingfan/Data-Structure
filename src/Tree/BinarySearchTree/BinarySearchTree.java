package Tree.BinarySearchTree;

import java.util.Scanner;
import java.util.logging.Level;

public class BinarySearchTree implements Tree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * @param key 待查找的值.
     * @return 返回查找到的节点.没找到就返回null.
     */
    @Override
    public Node find(int key) {
        Node current = root;
        while (current != null && current.iData != key) {
            if (key < current.iData) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return current;
    }

    //    插入节点.
    @Override
    public void insert(int id, double dd) {
        Node newNode = new Node(id, dd, null, null);
        if (root == null) {
            root = newNode;
            return;
        }
//        寻找插入位置.被插入的节点一定是叶子节点,需要做的就是找到合适的插入位置.
//        插在左边还是右边?
//        一定能够找到插入的位置.
        Node current = root;
        while (true) { //插入位置一定能找到,故死循环就好.
            if (id < current.iData) {
                if (current.leftChild != null) {
                    current = current.leftChild;
                } else {
                    current.leftChild = newNode;
                    break;
                }
            } else {
                if (current.rightChild != null) {
                    current = current.rightChild;
                } else {
                    current.rightChild = newNode;
                    break;
                }
            }
        }
    }

    /**
     * @param key 删除指定key所在的节点.
     * @return 删除成功返回true, 否则返回false.
     * 删除操作是最复杂的操作,分为三种情况.
     * case 1: 删除叶子节点
     * case 2: 删除的节点只有一个叶子节点
     * case 3: 删除的节点有两个叶子节点.
     * 删除一个节点时,类似链表,需要找到父节点.修改父节点的指针即可.
     * 所以这个地方设置一个parent记录父节点.
     */
    @Override
    public boolean delete(int key) {
        Node current = root;
        Node parent = current;
        boolean isLeftNode = true;
        while (current != null && current.iData != key) { //这个条件隐含current!=null
            parent = current;
            if (key < current.iData) {
                isLeftNode = true;
                current = current.leftChild;
            } else {
                isLeftNode = false;
                current = current.rightChild;
            }
        }

        if (current == null) {
            return false;  //没有此节点,删除操作失败.
        } else {
//            找到了删除的节点.即current,它的父亲借点为parent.开始判断三种情况进行删除操作.
//            case 1:删除的是叶子节点.
//            这个地方出现了一个问题,我们不知道被删除的是左叶子节点还是右叶子节点,而我们需要知道这个,所以得想办法获取这个信息.
//            出现了问题,得想办法解决.设置一个布尔变量记录current节点时左节点还是右节点.
            if (current.leftChild == null && current.rightChild == null) {
                if (current == root) {
                    root = null; //此时树只有一个根节点,直接把树置空即可.
                    return true;
                } else { //current节点只是普通的叶子节点.
                    if (isLeftNode) {
                        parent.leftChild = null;
                    } else {
                        parent.rightChild = null;
                    }
                    return true;
                }
            } else if (null != current.leftChild && null == current.rightChild) { //current的左子节点不空,右空.
                if (current == root) {
                    root = current.leftChild;
                    return true;
                }
                if (isLeftNode) {
                    parent.leftChild = current.leftChild;
                } else {
                    parent.rightChild = current.leftChild;
                }
                return true;
            } else if (null != current.rightChild && null == current.leftChild) { //右不空,左空.
                if (current == root) {
                    root = current.rightChild;
                    return true;
                }
                if (isLeftNode) {
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
                return true;
            } else {
                //current的左右子节点都不为空.寻找后继节点代替它的位置.
                PairNode pairNode = findSuccessor(current);
                if (null != pairNode.successor && null != pairNode.parent) {
                    pairNode.parent.leftChild = pairNode.successor.rightChild;
                    pairNode.successor.leftChild = current.leftChild;
                    pairNode.successor.rightChild = current.rightChild;
                    current.rightChild = current.leftChild = null;
                    if (current != root) {
                        if (isLeftNode) {
                            parent.leftChild = pairNode.successor;
                        } else {
                            parent.rightChild = pairNode.successor;
                        }
                    } else {
                        root = pairNode.successor;
                    }
                }
                return true;
            }
        }
    }
//寻找当前个的中序遍历后继节点.

    /**
     * 实现思路如下,从给点的节点寻找,寻找此节点的左节点.
     * 如果当前节点仍有左节点,继续寻找左节点.
     * 如果当前节点的左节点为空,那么当前节点就是我们要找的节点.
     * 查找过程中,仍要保存后继节点的父节点,留作调整使用.
     */
    private PairNode findSuccessor(Node node) {
        Node current = node;
        Node parent = current;
        while (current != null) {
            parent = current;
            current = current.leftChild;
            if (current.leftChild == null) {
                break;
            }
        }
        return new PairNode(parent, current);
    }

    //    辅助类,用于存放查找后继节点时,产生的后继节点和后继节点的父节点.
    private static class PairNode {
        Node parent;
        Node successor;

        public PairNode(Node parent, Node successor) {
            this.parent = parent;
            this.successor = successor;
        }
    }

    /**
     * @param to 1表示前序便利,2表示中序便利,3表示后序遍历,4表示层次遍历.
     */
    @Override
    public void traverse(TraverseOrder to) {
        switch (to) {
            case PREORDER:
                preOrder(root);
                break;
            case INORDER:
                inOrder(root);
                break;
            case POSTORDER:
                postOrder(root);
                break;
            case LEVELORDER:
                levelOrder(root);
                break;
        }
    }

    @Override
    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root + " ");
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    @Override
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.leftChild);
            System.out.print(root + " ");
            inOrder(root.rightChild);
        }
    }

    @Override
    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            System.out.print(root + " ");
        }
    }

    @Override
    public void levelOrder(Node root) {
//        需要一个队列进行辅助.采用LinkedList模拟队列实现.addLast,removeFirst,isEmpty .
        java.util.LinkedList<Node> list = new java.util.LinkedList<>();
        if (root != null) {
            list.add(root);
            while (!list.isEmpty()) {
                Node node = list.removeFirst();
                System.out.print(node + " ");
                if (node.leftChild != null) {
                    list.add(node.leftChild);
                }
                if (node.rightChild != null) {
                    list.add(node.rightChild);
                }
            }
        }

    }

    //    查找最小值的节点.
    @Override
    public Node min() {
        Node current = root;
        while (current != null) {
            current = current.leftChild;
            if (current.leftChild == null) {
                return current;
            }
        }
        return null;
    }

    //查找最大值所在节点.
    @Override
    public Node max() {
        Node current = root;
        while (current != null) {
            current = current.rightChild;
            if (current.rightChild == null) {
                return current;
            }
        }
        return null;
    }

    //    节点类的定义;
    static class Node {
        private int iData;
        private double dData;
        private Node leftChild;
        private Node rightChild;

        public Node() {
        }

        public Node(int iData, double dData, Node leftChild, Node rightChild) {
            this.iData = iData;
            this.dData = dData;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return iData + "";
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50, 1.5);
        bst.insert(25, 1.2);
        bst.insert(75, .17);
        bst.insert(12, 1.5);
        bst.insert(37, 1.2);
        bst.insert(43, 1.7);
        bst.insert(30, 1.5);
        bst.insert(33, 1.2);
        bst.insert(87, 1.7);
        bst.insert(93, 1.5);
        bst.insert(97, 1.5);
        System.out.println(bst.min());
        System.out.println(bst.max());
        Node node = bst.find(44);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println("Not exist");
        }
        boolean delete = bst.delete(50);
        if (delete) {
            bst.levelOrder(bst.root);
        }


//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("输入数字选择遍历模式: 1>前序/2>中序/3>后序/4>层次");
//            String input;
//            while (true) {
//                input = scanner.next();
//                if (!input.matches("^[0-9]+([.]{0,1}[0-9]+){0,1}$")) {
//                    System.out.println("输入的不是数字,重新输入整数.");
//                    continue;
//                } else if (input.matches("^[0-9]+$")) {
//                    if ((Integer.parseInt(input) > 4) || Integer.parseInt(input) < 1) {
//                        System.out.println("数字范围不合法,重新输入.");
//                        continue;
//                    }
//                    break;
//                } else {
//                    System.out.print("输入的是浮点数,重新输入.");
//                    continue;
//                }
//            }
//            int anInt = Integer.parseInt(input);
//            switch (anInt) {
//                case 1:
//                    bst.traverse(TraverseOrder.PREORDER);
//                    break;
//                case 2:
//                    bst.traverse(TraverseOrder.INORDER);
//                    break;
//                case 3:
//                    bst.traverse(TraverseOrder.POSTORDER);
//                    break;
//                case 4:
//                    bst.traverse(TraverseOrder.LEVELORDER);
//                    break;
//            }
//            System.out.println();
//        }

    }

}
