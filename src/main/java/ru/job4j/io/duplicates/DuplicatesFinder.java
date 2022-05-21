package ru.job4j.io.duplicates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    private static final Logger LOG = LoggerFactory.getLogger(DuplicatesFinder.class.getName());

    public static void main(String[] args) throws IOException {

        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects"), duplicatesVisitor);
        for (List<Path> value : duplicatesVisitor.getFilePaths().values()) {
            if (value.size() > 1) {
                value.forEach(v -> LOG.info(("Address: ").concat(String.valueOf(v.toAbsolutePath()))
                        .concat(" Size: ").concat(String.valueOf(v.toFile().length()))));
            }
        }
    }
}

