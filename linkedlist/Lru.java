import java.util.HashMap;
import java.util.Map;

public class Lru<K,V> {

    private Map<K, Node<K, V>> cacheMap = new HashMap<>();

    private int maxSize;

    private int nodeCount;

    private Node<K, V> head;

    private Node<K, V> tail;

    public Lru(int capacity){
        if (capacity < 2) throw new RuntimeException("容量最小为1");
        this.maxSize = capacity;
        this.cacheMap = new HashMap<>();

        head = new Node<>();
        head.pre = null;

        tail = new Node<>();
        tail.next = null;
    }

    public V get(K key){
        Node node = cacheMap.get(key);
        if (node == null) return null;
        this.moveToHead(node);
        return (V)node.value;
    }

    public void set(K key, V val){
        Node node = cacheMap.get(key);
        if (node == null){
            Node newNode = new Node(key, val);
            cacheMap.put(key, newNode);
            addNode(newNode);
            nodeCount++;
            if (nodeCount > maxSize){
                Node tail = popTail();
                cacheMap.remove(tail.key);
                nodeCount--;
            }
        }else{
            node.value = val;
            moveToHead(node);
        }
    }

    public void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }

    public Node popTail(){
        Node node = tail.pre;
        removeNode(node);
        return node;
    }

    public  void removeNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    // 添加节点到表头
    public void addNode(Node node){
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }


    private class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    public static void main(String[] args) {

    }
}
