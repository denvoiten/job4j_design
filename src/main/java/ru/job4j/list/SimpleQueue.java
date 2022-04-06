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
        if (xOut == 0) {
            while (xIn != 0) {
                out.push(in.pop());
                xIn--;
                xOut++;
            }
        }
        xOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        xIn++;
    }
}

