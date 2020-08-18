import java.util.Date;

/**
 * @Author: JontyX
 */
public class ArrayStack<E> {

    private Object[] elementData = null;
    private int maxSize = 0;
    private int top = -1;

    ArrayStack(){
        this(10);
    }

    ArrayStack(int initialCapacity){
        if (initialCapacity >= 0){
            this.maxSize = initialCapacity;
            elementData = new Object[initialCapacity];
            top = -1;
        }else{
            throw new RuntimeException("初始化大小不能小于-1");
        }
    }

    public boolean push(E e){
        if (top == maxSize - 1){
            throw new RuntimeException("栈已满");
        }else {
            elementData[++top] = e;
            return true;
        }
    }

    public E pop(){
        if (top == maxSize - 1){
            throw new RuntimeException("栈为空");
        }else {
            return (E)elementData[top--];
        }
    }

    public E peek(){
        if (top == -1){
            throw new RuntimeException("栈为空");
        }else {
            return (E)elementData[top];
        }
    }
}
