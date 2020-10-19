import java.util.LinkedList;
import java.util.Queue;

public class TrieTree<V> {
    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public V get(String key) {
        Node x = get(root, key, 0);
        return (V) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, V val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, V val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * 查找所有键
     * @return
     */
    public Iterable<String> keys() {
        return keysWithPrefis("");
    }

    public Iterable<String> keysWithPrefis(String pre) {
        Queue<String> q = new LinkedList<>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.add(pre);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    /**
     * 通配符匹配
     * @param pat
     * @return
     */
    public Iterable<String> keysThatMathc(String pat) {
        Queue<String> q = new LinkedList<>();
        collect(root, "", pat, q);
        return q;
    }

    public void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return ;
        if (d == pat.length() && x.val != null) q.add(pre);
        if (d == pat.length()) return;

        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c)
                collect(x.next[c], pre + c, pat, q);
        }
    }

    /**
     * 最长前缀
     * @param s
     * @return
     */
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);

    }

    // 字符串结束或遇到空链接时终止
    private int search(Node x, String s, int d, int length) {

        if (x == null) return length;
        // 如果字符串终止, 更新长度
        if (x.val != null) length = d;
        if (d == s.length()){
            return length;
        }
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    public Node delete(Node x, String key, int d) {
        /**
         * 1. 找到对应节点置为空
         * 2. 如果节点有非空链接, 直接返回
         * 3. 如果都是空连接, 则从数据结构中删除这个节点
         * 4. 删除这个节点后父节点也可能只有空链接了, 父节点也要删除
         */
        if (x == null) return null;

        if (d == key.length()){
            // 找到对应节点
            x.val = null;
        }else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if (x.val != null) {
            // 链路上的其余节点, 有可能是其他字符串的结束
            return x;
        }

        for (char c = 0; c < R; c++){
            if (x.next[c] != null) return x;
        }
        return null;
    }
}