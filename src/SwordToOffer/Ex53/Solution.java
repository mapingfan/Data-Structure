package SwordToOffer.Ex53;

public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        /**
         * 分类讨论: (注意不存在后继节点的情况)
         * 如果pNode   左孩子   右孩子
         *             Y        Y  同下第一种.
         *             N        Y  只有右孩子,那么下一个节点是右孩子所在树的最左节点.
         *             Y        N  找到一个父节点,使得此节点在此父节点的左子树上.
         *             N        N  找到一个父节点,使得此节点在此父节点的左子树上.
         *             会存在给定节点没有后继的情况,返回null.
         */

        TreeLinkNode result = null;
        if (pNode == null) return result;
        if (pNode.right != null) {
            //有右孩子.
            result = getMostLeftNode(pNode.right);
        } else {
            //没有右孩子.
            result = findSuitableParent(pNode);
        }
        return result;
    }

    //寻找pNode的合适父节点,使得pNode在其左子树上.
    private TreeLinkNode findSuitableParent(TreeLinkNode pNode) {
        TreeLinkNode parent = pNode.next;
        while (parent != null && parent.left != pNode) {
            pNode = parent;
            parent = parent.next;
        }
        return parent;
    }


    //root!=null已经被调用着判断过
    private TreeLinkNode getMostLeftNode(TreeLinkNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    private static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

}