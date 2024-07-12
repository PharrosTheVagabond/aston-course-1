package collections;

import java.util.Collection;
import java.util.Objects;

public class HWLinkedList<T> implements HWList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public HWLinkedList() {}

    public HWLinkedList(Collection<? extends T> source) {
        addAll(source);
    }

    @Override
    public void add(int index, T element) {
        Node<T> newNode = new Node<>(element);

        if (index == size) {
            linkNodes(tail, newNode);
            tail = newNode;
        }

        else {
            Node<T> curNode = getNode(index);
            linkNodes(curNode.prev, newNode);
            linkNodes(newNode, curNode);
        }

        if (index == 0) {
            head = newNode;
        }
        size++;
    }

    @Override
    public void set(int index, T element) {
        getNode(index).value = element;
    }

    @Override
    public void setAll(int index, T[] source) {
        Objects.checkIndex(index + source.length - 1, size());
        Node<T> node = getNode(index);
        for (T t : source) {
            node.value = t;
            node = node.next;
        }
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public int indexOf(T value) {
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            T nodeVal = node.value;
            if (Objects.equals(value, nodeVal)) {
                return i;
            }
            node = node.next;
        }

        return -1;
    }

    @Override
    public void remove(int index) {
        Node<T> node = getNode(index);
        linkNodes(node.prev, node.next);
        if (index == 0) {
            this.head = node.next;
        }
        if (index == size - 1) {
            this.tail = node.prev;
        }
        size--;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<?> node = head;
        for (int i = 0; i < size; i++) {
            array[i] = node.value;
            node = node.next;
        }
        return array;
    }


    private static <T> void linkNodes(Node<T> node1, Node<T> node2) {
        if (node1 != null) {
            node1.next = node2;
        }
        if (node2 != null) {
            node2.prev = node1;
        }
    }

    private Node<T> getNode(int index) {
        Objects.checkIndex(index, size());
        Node<T> node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        else {
            node = tail;
            for (int i = size; i > index + 1; i--) {
                node = node.prev;
            }
        }
        return node;
    }


    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

}
