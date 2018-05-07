package ExDay3;

import lombok.Data;

@Data
public class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
        this.value = value;
    }
}
