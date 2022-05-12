package ru.job4j.odd.srp;

/**
 * В одном методе происходит получение данных и вывод в консоль.
 * Такой код сложно переиспользовать.
 * Лучше разделить на вывод в консоль и метод расчета
 */

public class BadWorker {
    private int id;
    private String name;

    public BadWorker(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print() {
        int a = 2;
        int b = 3;
        int rsl = a + b;
        System.out.println(rsl);
    }
}
