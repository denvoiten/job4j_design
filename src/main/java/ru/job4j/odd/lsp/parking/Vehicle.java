package ru.job4j.odd.lsp.parking;

public abstract class Vehicle {
    private int size;

    protected Vehicle(int size) {
        this.size = size;
    }

    protected Vehicle() {
    }

    public int getSize() {
        return size;
    }
}
