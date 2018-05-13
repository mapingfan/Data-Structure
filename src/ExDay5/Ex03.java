package ExDay5;

/**
 * 前缀树结构实现.
 *
 */
public class Ex03 {

    public static class TrieNode {
        int pass=0; //路过的节点个数.
        int end=0;  //以这个节点的字符串个数.
        TrieNode[] next = new TrieNode[26]; //TrieNode[0]表示路径上放的a.
    }

    public static class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }
        //往前缀树中插入一个字符串,字符串的字符只能是a-z.共26个.
        public void insert(String word) {
            if (word==null) return;
            TrieNode tmp = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 97;
                if (tmp.next[index] == null) {
                    tmp.next[index] = new TrieNode();
                }
                tmp = tmp.next[index];
                tmp.pass++;
            }
            tmp.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if (--node.next[index].pass == 0) { //如果通过next[index]的节点只有一个了,后面直接置为null.
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }

        public int search(String word) {
            if (word==null) return 0;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 97;
                if (root.next[index]!=null) root = root.next[index];
                else return 0;
            }
            return root.end;
        }

        public int prefixNumber(String pre) {
            if (pre==null) return 0;
            char[] chars = pre.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.next[index]!=null) node = node.next[index];
                else return 0;
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {

    }
}
