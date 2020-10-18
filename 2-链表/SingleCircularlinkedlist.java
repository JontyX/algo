public class SingleCircularlinkedlist<E> extends AbstractList<E> {

    private Node<E> first;

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return null;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == 0){
            Node<E> newFirst = new Node<>(element, first);
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        }else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        Node<E> f = first;
        if (index == 0){
            if (size == 0){
                first = null;
            }else{
                Node<E> last = node(size - 1);
                last.next = first.next;
                first = first.next;
            }
        }else{
            Node<E> prev = node(index - 1);
            f = prev.next;
            prev.next = f.next;
        }
        size--;
        return f.item   ;
    }

    @Override
    public int indexOf(E element) {
        if (element == null){
            Node<E> x = this.first;
            for (int i = 0; i < size; i++){
                if (x.item == element){
                    return i;
                }
                x = x.next;
            }
        }else {
            Node<E> x = this.first;
            for (int i = 0; i < size; i++){
                if (x.item.equals(element)){
                    return i;
                }
                x = x.next;
            }
        }
        return -1;
    }

    private Node<E> node(int index){
        Node<E> x = first;
        for (int i = 0; i < index; i++){
            x = x.next;
        }
        return x;
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[size=").append(size).append(", ");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node.item);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(item).append("_").append(next.item);
            return sb.toString();
        }
    }
}
