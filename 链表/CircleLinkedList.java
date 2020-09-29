/**
 * 双向循环链表
 */
public class CircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;
    private Node<E> current;


    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    public E remove() {
        if (current == null) return null;
        Node<E> next = current.next;
        E element = remove(current);
        if(size == 0){
            current = null;
        }else{
            current = next;
        }
        return element;
    }


    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> node = node(index);
        E oldVal = node.element;
        node.element = element;
        return oldVal;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size){
            Node<E> oldLast = this.last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null){
                first = last;
                first.next = first;
                first.prev = first;
            }else {
                oldLast.next = last;
                first.prev = last;
            }
        }else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev, element, next);
            next.prev = newNode;
            prev.next = next;
            if (next == first) {
                first = newNode;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return remove(node(index));
    }

    public E remove(Node<E> node){
        if (size == 1){
            first = null;
            last = null;
        }else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (node == first){
                first = next;
            }

            if (node == last){
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == element)
                    return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element))
                    return i;
                node = node.next;
            }
        }
        return -1;
    }


    private Node<E> node(int index){
        checkElementIndex(index);
        if (index < (size >> 1)){
            Node<E> node = first;
            for (int i = 0; i < size; i++){
                node = node.next;
            }
            return node;
        }else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }
            sb.append("_").append(element).append("_");
            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }
}
