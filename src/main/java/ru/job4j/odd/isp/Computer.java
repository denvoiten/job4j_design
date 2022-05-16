package ru.job4j.odd.isp;

/**
 * У компьютера может не оказаться привода для чтения компакт-дисков, но ему придется реализовать метод readCD();
 */

public interface Computer {
    void readCD();

    double calculate();

    void showInfo();
}
