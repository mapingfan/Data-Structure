package Tree.BinarySearchTree;

import Tree.BinarySearchTree.BinarySearchTree.Node;

public interface Tree {
    Node find(int key);

    void insert(int id, double dd);

    boolean delete(int key);

    void traverse(TraverseOrder to);

    void preOrder(Node root);

    void inOrder(Node root);

    void postOrder(Node root);

    void levelOrder(Node root);

    Node min();

    Node max();


}
