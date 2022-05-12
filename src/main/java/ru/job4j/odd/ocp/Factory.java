package ru.job4j.odd.ocp;

/**
 * Если появится необходимость делать что-то кроме велосипедов, метод нужно переписывать
 */

public class Factory {

    public Bicycle make() {
        return new Bicycle();
    }

    class Bicycle {

    }
}
