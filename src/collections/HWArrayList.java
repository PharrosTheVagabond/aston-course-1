package collections;

import java.util.Collection;
import java.util.Objects;

public class HWArrayList<T> implements HWList<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private T[] array;
    private final int initialCapacity;
    private int size;

    public HWArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HWArrayList(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.array = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    public HWArrayList(Collection<? extends T> source) {
        this(source.size());
        addAll(source);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size() + 1);
        if (array.length == size) {
            T[] newArray = (T[]) new Object[array.length + array.length / 2 + 1];
            System.arraycopy(this.array, 0, newArray, 0, array.length);
            this.array = newArray;
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size());
        array[index] = element;
    }

    @Override
    public void setAll(int index, T[] source) {
        Objects.checkIndex(index + source.length - 1, size());
        for (T newElem : source) {
            array[index++] = newElem;
        }
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size());
        return array[index];
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        Objects.checkIndex(index, size());
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.array = (T[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] deepCopy = new Object[size];
        System.arraycopy(this.array, 0, deepCopy, 0, size);
        return deepCopy;
    }

}
