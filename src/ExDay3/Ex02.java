package ExDay3;

import java.util.ArrayList;

/**
 * 找给定节点的中序遍历后继节点.
 * 每个节点多一个指针域指向其父节点.
 * <p>
 * 分析:
 * 笨办法:既然给出了父节点,那么一定能找到整棵树的根节点.
 * 那么只需要根据根节点中序遍历这个树,就可以找到后一个是谁.
 * <p>
 * 稍微改进的方法:
 * 如果给定节点存在右子树,那么找右子树的最左节点即可.
 * 原因如下:中序序列是左根右.
 * 1> 如果存在右子树,那么下一个要遍历的肯定是右子树.
 * 对于右子树,还是要安装左根右,所以要遍历的一定是右子树的最左的节点.
 * 2> 如果不存在右子树怎么办?首先可以知道一定是往上回溯.
 * 根据左神的提醒,从下往上找到第一个一个根节点,让给定节点在这个节点的左子树上.
 * 具体的实现策略略有不同.
 * <p>
 * 下面实现这个方法.
 */
public class Ex02 {
    /**
     * @param givenNode
     * @return 求给定节点的后继节点.
     */
    public static Node getSuccessor(Node givenNode) {
        if (givenNode == null) return null;
        if (givenNode.right != null) {
            //寻找右子树的最左节点.
            return findMostLeftNode(givenNode.right);
        } else {
            Node parent = givenNode.parent;
            //注意下面的while循环为何要加上parent!=null.因为根节点的parent指向null.找到根节点都没找到,说明不存在后继.
            //中序遍历的最后一个节点就不存在后继节点.
            while (parent != null && parent.left != givenNode) {
                givenNode = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    private static Node findMostLeftNode(Node right) {
        while (right.left != null) {
            right = right.left;
        }
        return right;
    }

    //方法二,笨方法实现
    public static Node getSuccessor_2(Node givenNode) {
        if (givenNode == null) return null;
        ArrayList<Node> inOrderArray = new ArrayList<>();
        //下面找到给定节点所在树的根节点.
        Node root = findRoot(givenNode);
        inOrderArray = inOrder(root, inOrderArray);
        //根据root进行中序遍历.
        return inOrderArray.indexOf(givenNode) == inOrderArray.size() - 1 ? null : inOrderArray.get(inOrderArray.indexOf(givenNode) + 1);
    }

    private static ArrayList<Node> inOrder(Node root, ArrayList<Node> inOrderArray) {
        if (root == null) return null;
        inOrder(root.left, inOrderArray);
        inOrderArray.add(root);
        inOrder(root.right, inOrderArray);
        return inOrderArray;
    }


    private static Node findRoot(Node givenNode) {
        while (givenNode.parent != null) {
            givenNode = givenNode.parent;
        }
        return givenNode;
    }


    private static class Node {
        Node parent;
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        //----------------------------------

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " next: " + getSuccessor(test).value);
        System.out.println(test.value + " next: " + getSuccessor_2(test).value);
        System.out.println("-----------------------------------------");

        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " next: " + getSuccessor(test).value);
        System.out.println(test.value + " next: " + getSuccessor_2(test).value);
        System.out.println("-----------------------------------------");
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " next: " + getSuccessor(test).value);
        System.out.println(test.value + " next: " + getSuccessor_2(test).value);
        System.out.println("-----------------------------------------");

        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " next: " + getSuccessor(test).value);
        System.out.println(test.value + " next: " + getSuccessor_2(test).value);

    }

    //下面的代码是左神的实现.
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
