import java.util.ArrayList;

/**
 * @Author: JontyX
 */
public class DynamicArray<E> {

    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public DynamicArray(int capacity){
        capacity  = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements =  (E[]) new Object[capacity];
    }

    public DynamicArray(){
        this(DEFAULT_CAPACITY);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FOUND; // 找的到该元素则返回True
    }

    public E set(int index, E element){
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    public E get(int index){
        rangeCheck(index);
        return elements[index];
    }

    public E remove(int index){
        rangeCheck(index);

        E old = elements[index];
        for(int i = index; i < size - 1; i ++){
            elements[i] = elements[i+1];
        }
        elements[size--] = null;
        return old;
    }

    public void add(E element){
        add(size, element);
    }

    public void add(int index, E element){
        rangeCheckForAdd(index);
        ensureCapacity(size+1);

        for(int i = size; i > index; i--){
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }


    public int indexOf(E element){
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i] == null ) return i;
            }
        }else{
            for(int i = 0; i < size; i++){
                if (elements[i].equals(element)) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public void clear(){
        // 使用泛型数组后要注意内存管理(将元素置null)
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, oldCapacity);
        elements = newElements;
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    private void rangeCheck(int index){
        if(index < 0 || index >= size){
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if(0 != i) string.append(", ");
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

    public static void main(String[] args) {
        DynamicArray<Integer> list =new DynamicArray<>();
        list.add(0);
        list.add(2);
        list.add(0,-1);
        System.out.println(list.toString());
        list.clear();
        System.out.println(list.toString());
        /**
         * size=3, [-1, 0, 2]
         * size=0, []
         */
    }
}
