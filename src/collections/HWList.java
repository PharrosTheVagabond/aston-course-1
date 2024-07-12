package collections;

import java.util.Collection;
import java.util.Comparator;

public interface HWList<T> {
    default void add(T element) {
        add(size(), element);
    }

    void add(int index, T element);

    void set(int index, T element);

    void setAll(int index, T[] source);

    T get(int index);

    default boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    int indexOf(T value);

    void remove(int index);

    void clear();

    default void addAll(Collection<? extends T> source) {
        for (T elemToAdd : source) {
            add(elemToAdd);
        }
    }

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    Object[] toArray();

    static <T extends Comparable<? super T>> void sort(HWList<T> list) {
        sort(list, Comparable::compareTo);
    }

    @SuppressWarnings("unchecked")
    static <T> void sort(HWList<T> list, Comparator<? super T> comparator) {
        T[] array = (T[]) list.toArray();

        boolean isSorted = false;
        for (int i = 0; i < array.length - 1 && !isSorted; i++) {
            isSorted = true;
            for (int j = array.length - 1; j > i; j--) {
                if (comparator.compare(array[j - 1], array[j]) > 0) {
                    T swap = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = swap;
                    isSorted = false;
                }
            }
        }

        list.setAll(0, array);
    }

}
