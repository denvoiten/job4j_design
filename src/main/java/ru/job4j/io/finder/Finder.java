package ru.job4j.io.finder;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Finder {
    public static void handle(ArgsName argsName) throws IOException {
        Path path = validate(argsName);
        String outFile = argsName.get("o");
        writeFile(outFile, search(path, getCondition(argsName)));
    }

    private static Path validate(ArgsName argsName) {
        Path path = Path.of(argsName.get("d"));
        String searchType = argsName.get("t");
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not directory %s", path));
        }
        if (!("mask".equals(searchType) || "regex".equals(searchType) || "name".equals(searchType))) {
            throw new IllegalArgumentException(argsName.message);
        }
        return path;
    }

    private static void writeFile(String outFile, List<Path> paths) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outFile)))) {
            paths.forEach(writer::println);
        }
    }

    private static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static Predicate<Path> getCondition(ArgsName argsName) {
        String searchType = argsName.get("t");
        String fileName = argsName.get("n");
        Predicate<Path> condition = null;
        Pattern pattern;
        if ("name".equals(searchType)) {
            condition = path -> path.toFile().getName().equals(fileName);
        } else if ("mask".equals(searchType)) {
            String mask = fileName
                    .replace(".", "[.]")
                    .replace("*", "\\w+")
                    .replace("?", "\\w");
            pattern = Pattern.compile(mask);
            condition = path -> path.toFile().getName().matches(pattern.toString());
        } else if ("regex".equals(searchType)) {
            pattern = Pattern.compile(fileName);
            condition = path -> path.toFile().getName().matches(pattern.toString());
        }
        return condition;
    }

    public static void main(String[] args) throws IOException {
        handle(ArgsName.of(args));
    }
}
