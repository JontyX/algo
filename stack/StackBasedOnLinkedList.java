/**
 * @Author: JontyX
 */
public class StackBasedOnLinkedList {

    private Node top = null;

    public void push(int val){
        Node newNode = new Node(val, null);

        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    public int pop() {
        if (top == null){
            throw new RuntimeException("栈为空");
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
