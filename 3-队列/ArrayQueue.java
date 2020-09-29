
public class ArrayQueue<E> {

    private int front;
    private int rear;
    private int capacity;
    private E[] array;

    private ArrayQueue(int size){
        capacity = size;
        front = 0;
        rear = 0;
        array = (E[]) new Object[size];
    }

    public static ArrayQueue createQueue(int size){
        return new ArrayQueue(size);
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public boolean isFull(){
        return ((rear + 1) % capacity == front);
    }

    public int size(){
        return (capacity + rear - front) % capacity;
    }

    public boolean enqueue(E element){
        if (element == null){
            throw new NullPointerException("不能为空");
        }
        if (isFull()) {
            return false;
        }
        array[rear] = element;
        rear = (rear + 1) % capacity;
        return true;
    }

    public E dequeue(){
        if (isEmpty())
            return null;
        E element = array[front];
        front = (front + 1) % capacity;
        return element;
    }

}
