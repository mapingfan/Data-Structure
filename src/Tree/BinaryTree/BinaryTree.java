package Tree.BinaryTree;

class BinaryTreeNode {
    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}

public class BinaryTree {
    private BinaryTreeNode root;

    public BinaryTree() {
    }

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public void insertAsLeftChild(BinaryTreeNode node) {
        checkTreeEmpty();
        root.setLeft(node);
    }

    public void insertAsRightChild(BinaryTreeNode node) {
        checkTreeEmpty();
        root.setRight(node);
    }

    private void checkTreeEmpty() {
        if (root == null) {
            throw new IllegalStateException("The tree is empty.");
        }
    }

    public void deleteNode(BinaryTreeNode node) {
       checkTreeEmpty();
//        递归调用出口.
        if (node == null) {
            return;
        }
        deleteNode(node.getLeft());
        deleteNode(node.getRight());
        node = null;
    }

    public void clear() {
        checkTreeEmpty();
        deleteNode(root);
    }

    public int getTreeHeight() {
        checkTreeEmpty();
        return getNodeHeight(root);
    }

    private int getNodeHeight(BinaryTreeNode node) {
        if (node != null) {
            return Math.max(getNodeHeight(node.getRight()), getNodeHeight(node.getLeft())) + 1;
        } else {
            return 0;
        }
    }

    public int getSize() {
        checkTreeEmpty();
        return getChildSize(root);
    }

    private int getChildSize(BinaryTreeNode node) {
        if (node != null) {
            return getChildSize(node.getLeft()) + getChildSize(node.getRight()) + 1;
        } else {
            return 0;
        }
    }
//    获取指定节点的父亲节点.
    public BinaryTreeNode getParent(BinaryTreeNode node) {
        if (node == root || root == null) {
            throw new IllegalStateException("No parent node");
        } else {
            return traverse(root, node);
        }
    }

    private BinaryTreeNode traverse(BinaryTreeNode root, BinaryTreeNode node) {

        if (root != null) {
            if (root.getLeft() == node || root.getRight() == node) {
                System.out.println(root.getData());
                return root;
            }
            traverse(root.getLeft(), node);
            traverse(root.getRight(), node);
        }
        return null;
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(BinaryTreeNode root) {
        checkTreeEmpty();
        if (root != null) {
            System.out.println(root.getData());
            preOrder(root.getLeft());
            preOrder(root.getRight());
        } else {
            return;
        }
    }

    public void  inOrder() {
        inOrder(root);
    }

    private void inOrder(BinaryTreeNode root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.println(root.getData());
            inOrder(root.getRight());
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(BinaryTreeNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.println(root.getData());
        }
    }


}
