package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> filePaths = new HashMap<>();

    public Map<FileProperty, List<Path>> getFilePaths() {
        return filePaths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (filePaths.putIfAbsent(newFile, new ArrayList<>(List.of(file.toAbsolutePath()))) != null) {
            filePaths.get(newFile).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}