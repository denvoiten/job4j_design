package ru.job4j.odd.srp;

import java.io.File;
import java.nio.file.Path;

/**
 * При имплементации интерфейса нужно будет риализовать
 * функционал, который может быть не нужен
 */

public interface BadEditor {
    String read(File file);

    void validate(Path path);

    String edit(String text);
}
