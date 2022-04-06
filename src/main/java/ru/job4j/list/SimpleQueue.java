package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int xIn = 0;
    int xOut = 0;

    public T poll() {
        if (xIn == 0 && xOut == 0) {
            throw new NoSuchElementException();
        }
        if (xIn > 0) {
            xOut = swap(out, in, xIn);
            xIn = 0;
        }
        xOut--;
        return out.pop();
    }

    public void push(T value) {
        if (xOut > 0) {
            xIn = swap(in, out, xOut);
            xOut = 0;
        }
        in.push(value);
        xIn++;
    }

    public int swap(SimpleStack<T> first, SimpleStack<T> second, int count) {
        for (int i = 0; i < count; i++) {
            first.push(second.pop());
        }
        return count;
    }
}
