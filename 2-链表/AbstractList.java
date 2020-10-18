public abstract class AbstractList<E> implements List<E> {

    protected int size;

    private boolean isElementIndex(int index){
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index){
        return index >= 0 && index <= size;
    }

    protected String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    protected void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    protected void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element)!=ELEMENT_NOT_FOUND;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

}
