import org.omg.CORBA.Object;

/**
 * @Author: JontyX
 */
public class DynArrayQueue<E> {

    private int front = 0;
    private int rear = 0;
    private int capacity;
    private E[] array;

    private DynArrayQueue(int size){
        capacity = size;
        array = (E[]) new Object[size];
    }

    public static DynArrayQueue getQueue(int size){
        if (size < 10) {
            size = 10;
        }
        return new DynArrayQueue(size);
    }

    public boolean isEmpty(){
        return (front == rear);
    }

    public boolean isFull(){
        return ((rear + 1) % capacity == front);
    }

    public int size(){
        return (rear - front + capacity) % capacity;
    }

    private void resize(){
        int oldCapacity = capacity;
        E[] oldArray = array;
        array = (E[])new Object[capacity];
        for (int i = 0; i < oldCapacity; i++) {
            array[i] = oldArray[i];
        }
        if (rear < front){
            for (int i = 0; i < front; i++){
                array[i + oldCapacity] = array[i];
                array[i] = null;
            }
            rear = rear + oldCapacity;
        }
    }

    public void enqueue(E element){
        if (element == null)
            throw new NullPointerException();
        if (isFull())
            resize();
        array[rear] = element;
        rear = (rear + 1) % capacity;
    }

    public E dequeue(){
        if (isFull()) return null;
        E element = array[front];
        front = (front + 1) % capacity;
        return element;
    }
}
