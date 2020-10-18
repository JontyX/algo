import java.util.NoSuchElementException;

public class DoublyLinkedList {

    int size = 0;

    Node first;

    Node last;

    public DoublyLinkedList(){
    }

    void linkFirst(Object e){
        Node f = first;
        Node newNode = new Node(null, e, f);
        first = newNode;
        if (f == null){
            last = newNode;
        }else{
            f.prev = newNode;
        }
        size++;
    }

    void linkLast(Object e){
        Node l = last;
        Node newNode = new Node(l, e, null);
        last = newNode;
        if (l == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        size++;
    }

    void linkBefore(Object e, Node succ){
        Node pred = succ.prev;
        Node newNode = new Node(pred, e, succ);
        succ.prev = newNode;
        if (pred == null){
            first = newNode;
        }else{
            pred.next = newNode;
        }
        size++;
    }

    private Object unlinkFirst(Node f){
        Object element = f.val;
        Node next = f.next;
        f.val = null;
        f.next = null;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    private Object unlinkLast(Node l){
        Object element = l.val;
        Node prev = l.prev;
        l.prev = null;
        l.val = null;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    Object unlink(Node x){
        Object element = x.val;
        Node next = x.next;
        Node prev = x.prev;
        x.val = null;
        if (prev == null){
            first = next;
        }else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null){
            last = prev;
        }else {
            next.prev = prev;
            x.next = null;
        }
        size--;
        return element;
    }

    public Object getFirst(){
        Node f = first;
        if (f == null){
            throw new NoSuchElementException();
        }
        return f.val;
    }

    public Object getLast(){
        Node l = last;
        if (l == null){
            throw new NoSuchElementException();
        }
        return l.val;
    }

    public Object removeFirst(){
        Node f = this.first;
        if (f == null){
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    public Object removeLast(){
        Node l = this.last;
        if (l == null){
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }


    public void addFirst(Object e){
        linkFirst(e);
    }

    public void addLast(Object e){
        linkLast(e);
    }

    public boolean contains(Object o){
        return indexOf(o) != -1;
    }

    public int size(){
        return size;
    }


    public boolean add(Object e){
        linkLast(e);
        return true;
    }

    public boolean remove(Object o){
        if (o == null){
            for (Node x = first; x != null; x = x.next){
                if (x.val == null){
                    unlink(x);
                    return true;
                }
            }
        }else{
            for (Node x = first; x != null; x = x.next){
                if (o.equals(x.val)){
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public void clear(){
        for (Node x = first;  x != null; ){
            Node next = x.next;
            x.val = null;
            x.prev = null;
            x.next = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    public Object get(int index){
        return node(index).val;
    }

    public Object set(int index, Object element){
        checkElementIndex(index);
        Node x = node(index);
        Object oldVal = x.val;
        x.val = element;
        return oldVal;
    }

    public void add(int index, Object element){
        checkPositionIndex(index);
        if (index == size){
            linkLast(element);
        }else {
            linkBefore(element, node(index));
        }
    }

    public Object remove(int index){
        return unlink(node(index));
    }

    private boolean isElementIndex(int index){
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index){
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    Node node(int index){
        if (index < (size >> 1)){
            Node x = first;
            for (int i = 0; i < index; i++){
                x = x.next;
            }
            return x;
        }else{
            Node x = last;
            for (int i = size - 1; i > index; i--){
                x = x.prev;
            }
            return x;
        }
    }

    public int indexOf(Object o){
        int index = 0;
        if (o == null){
            for (Node x = first;  x != null; x = x.next){
                if (x.val == null){
                    return index;
                }
                index++;
            }
        }else{
            for (Node x = first; x != null; x = x.next){
                if (o.equals(x.val)){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    private static class Node{

        Object val;
        Node next;
        Node prev;

        private Node(Node prev, Object val, Node next){
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }
}
