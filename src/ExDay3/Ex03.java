package ExDay3;

import java.util.ArrayList;

/**
 * 找给定节点中序遍历的前一个节点.
 */
public class Ex03 {
    private static class Node {
        Node parent;
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    private static Node getPrecursor(Node givenNode) {
        if (givenNode == null) return null;
        ArrayList<Node> inOrderArray = new ArrayList<>();
        //下面找到给定节点所在树的根节点.
        Node root = findRoot(givenNode);
        inOrderArray = inOrder(root, inOrderArray);
        //根据root进行中序遍历.
        return inOrderArray.indexOf(givenNode) == 0 ? null : inOrderArray.get(inOrderArray.indexOf(givenNode) - 1);
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

    //下面按照别的思路分析下:

    /**
     * 按照找后继的思路,进行类比:
     * 1>如果给定节点存在左子树.那么根据中序:左根右, 那么一定先中序遍历完左子树,而后是这个给定节点.
     * 而左子树的中序最后一个遍历节点时它的最右节点.很好找.
     * 2>如果不存在左子树,那么类比下,往上找.往上找到第一个根节点,使得给定节点在它的右子树上.
     * <p>
     * 下面简单实现下.
     */

    public static Node getSuccessor_2(Node givenNode) {
        if (givenNode == null) return null;
        if (givenNode.left != null) return findMostRightNode(givenNode.left);
        Node parent = givenNode.parent;
        while (parent != null && parent.right != givenNode) {
            givenNode = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private static Node findMostRightNode(Node node) {
        //上面已经判空,此处不继续.
        while (node.right != null) {
            node = node.right;
        }
        return node;
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
//        System.out.println(test.value + " before: " + getPrecursor(test).value);
//        System.out.println(test.value + " before: " + getSuccessor_2(test).value);
//        System.out.println("-----------------------------------------");

        test = head.left.left.right;
        System.out.println(test.value + " before: " + getPrecursor(test).value);
        System.out.println(test.value + " before: " + getSuccessor_2(test).value);
        System.out.println("-----------------------------------------");
        test = head.left;
        System.out.println(test.value + " before: " + getPrecursor(test).value);
        System.out.println(test.value + " before: " + getSuccessor_2(test).value);
        System.out.println("-----------------------------------------");

        test = head.left.right;
        System.out.println(test.value + " before: " + getPrecursor(test).value);
        System.out.println(test.value + " before: " + getSuccessor_2(test).value);
    }

}
