package ru.job4j.list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size = 0;
    private int modCount;

    private static class Node<E> {
        private Node<E> prev;
        private E value;
        private Node<E> next;

        public Node(Node prev, E value, Node next) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    @Override
    public void add(E value) {
        if (size == 0) {
            first = new Node(null, value, null);
            last = first;
        } else {
            Node secondLast = last;
            last = new Node(secondLast, value, null);
            secondLast.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> item = first;
        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return item.value;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            Node<E> current = first;
            int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count != size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = current.value;
                current = current.next;
                count++;
                return rsl;
            }
        };
    }
}
