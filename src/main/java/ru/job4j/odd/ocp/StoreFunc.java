package ru.job4j.odd.ocp;

/**
 * Если изменится тип хранилища метод нельзя будет переиспользовать.
 */

public class StoreFunc {
    public String getData(SqlStore store) {

        return null;
    }

    class SqlStore {

    }
}
