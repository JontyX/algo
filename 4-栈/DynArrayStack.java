
public class DynArrayStack<E> {

    private int top = -1;
    private int capacity;
    private E[] array;

    public DynArrayStack(){
        this(10);
    }

    public DynArrayStack(int initialCapacity){
        if (initialCapacity >= 0){
            this.capacity = initialCapacity;
            array = (E[]) new Object[initialCapacity];
            top = -1;
        }else{
            throw new RuntimeException("初始化大小不能小于-1");
        }
    }


    public void push(E e){
        if (isFull())
            resize();
        array[++top] = e;
    }

    public E pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }else {
            return array[top--];
        }
    }

    public E peek(){
        if (top == -1){
            return null;
        }else {
            return array[top];
        }
    }

    private void resize(){
        E[] newArray = (E[]) new Object[capacity * 2];
        System.arraycopy(array, 0, newArray, 0, capacity);
        capacity = capacity * 2;
        array = newArray;
    }

    public int size(){
        return top + 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return top == capacity - 1;
    }
}
