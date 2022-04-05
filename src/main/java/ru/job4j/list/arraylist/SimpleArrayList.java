package ru.job4j.list.arraylist;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList() {
        this.container = (T[]) new Object[8];
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void grow() {
        int capacity = (container.length == 0 ? 8 : size) * 2;
        container = Arrays.copyOf(container, capacity);
    }


    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = get(index);
        System.arraycopy(container, index + 1,
                container, index,
                container.length - index - 1);
        container[--size] = null;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int iterMod = modCount;
        return new Iterator<T>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (iterMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}
