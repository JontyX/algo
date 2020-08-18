/**
 * @Author: JontyX
 */
public class StackBasedOnLinkedList<E> {

    private Node<E> top = null;

    public void push(int val){
        Node newNode = new Node(val, null);

        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    public E pop() {
        if (top == null){
            throw new RuntimeException("栈为空");
        }
        E value = top.data;
        top = top.next;
        return value;
    }

    private static class Node<E> {
        E data;
        Node next;
        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
