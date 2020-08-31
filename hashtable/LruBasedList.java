import java.util.HashMap;
import java.util.LinkedList;



public class LruBasedList<K,V> {

    LinkedList<Node> list;

    HashMap<K, Node> map;

    int capcity;

    public LruBasedList(int capcity){
        this.capcity = capcity;
        this.list = new LinkedList<>();
        this.map = new HashMap<>((int)(capcity / 0.75f) + 1);
    }

    public void put(K key, V val){
        if (list.size() == capcity){
            map.remove(key);
            list.removeFirst();
        }
        Node<K, V> node = new Node<>(key, val);
        map.put(key, node);
        list.add(node);
    }

    public int size(){
        return list.size();
    }

    public V get(K key){
        if (map.get(key) == null){
            return null;
        }
        Node node = list.removeFirst();
        list.add(node);
        return (V)node.value;
    }

    public V delete(K key){
        if (map.get(key) == null){
            return null;
        }
        Node node = map.get(key);
        list.remove(node);
        return (V)node.value;
    }

    class Node<K, V>{

        K key;

        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
