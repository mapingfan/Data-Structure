package SwordToOffer.Ex57;


public class Solution {
    String Serialize(TreeNode root) {
        if (root == null) return "!#";
        return root.val + "#" + Serialize(root.left) + Serialize(root.right);
    }

    TreeNode Deserialize(String str) {
        if (str == "!#") return null;
        String[] strings = str.split("#");
        return Deserialize(strings);

    }

    int index = 0;

    private TreeNode Deserialize(String[] strings) {
        if (index >= strings.length) return null;
        String tmp = strings[index++];
        TreeNode root;
        if (tmp.equals("!")) return null;
        else {
            root = new TreeNode(Integer.parseInt(tmp));
        }
        root.left = Deserialize(strings);
        root.right = Deserialize(strings);

        return root;
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(6);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(9);
        node1.left = node2;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        node4.left = node7;
        node4.right = node8;

        Solution solution = new Solution();
        String serialize = solution.Serialize(node1);
        System.out.println(serialize);

        TreeNode root = solution.Deserialize(serialize);
        System.out.println(root.val);

    }
}