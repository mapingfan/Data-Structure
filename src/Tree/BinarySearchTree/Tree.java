package Tree.BinarySearchTree;

import Tree.BinarySearchTree.BinarySearchTree.Node;

public interface Tree {
    Node find(int key);

    void insert(int id, double dd);

    boolean delete(int key);

    void traverse(TraverseOrder to);

    void preOrder(Node root);

    void preOrderV2(Node root);

    void inOrder(Node root);

    void inOrderV2(Node root);

    void postOrder(Node root);

    void postOrderV2(Node root);

    void levelOrder(Node root);

    Node min();

    Node max();


}
