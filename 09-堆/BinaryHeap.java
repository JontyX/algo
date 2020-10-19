import printer.BinaryTreeInfo;

import java.io.EOFException;
import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(E[] elements, Comparator<E> comparator)  {
        super(comparator);

        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    public BinaryHeap(E[] elements)  {
        this(elements, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap() {
        this(null, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();

        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;

        siftDown(0);
        return root;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    /**
     * 批量建堆
     */
    private void heapify() {
        // 自上而下的上滤
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}

        // 自下而上的下滤
        for (int i = size - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 让index位置的元素上浮
     */
    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0){
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            // 父节点元素更大, 退出
            if (compare(element, parent) <= 0) break;

            elements[index] = parent;

            // 重新复制Index
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void siftDown(int index) {
        int left = index * 2 + 1;
        while (left < size) {
            //判断走左边还是右边
            // left + 1 给右节点预留位置
            // 如果 left + 1 < heapsize 不满足, 则没有右节点了
            int largest = left + 1 < size && compare(elements[left + 1], elements[left]) > 0 ? left+1 : left;

            //上面的largest判断的是左右两个子节点中最大值,现在判断子节点和父节点最大值
            largest = compare(elements[index], elements[largest]) > 0 ? index:largest;
            //如果满足大根堆结构,就不下沉了
            if (largest == index) {
                break;
            }
            E t = elements[largest];
            elements[largest] = elements[index];
            elements[index] = t;
            //继续下沉
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;


        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < 0) newCapacity = Integer.MAX_VALUE;
        if (newCapacity < capacity) newCapacity = capacity;
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int)node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int)node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int)node];
    }
}
