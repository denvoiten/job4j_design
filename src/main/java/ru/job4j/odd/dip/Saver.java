package ru.job4j.odd.dip;

import java.io.FileWriter;
import java.io.IOException;

/**
 * При необходимости записи в БД или в другое хранилище нам придется создавать новые методы или тп.
 * Лучше сразу сделать интерфейс с методом save().
 */

public class Saver {
    public void save(String path, String information) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(information);
        }
    }
}
