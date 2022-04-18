package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        search(validate(args), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getRsl();
    }

    public static Path validate(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Enter root folder and file extension to search.");
        } else if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("\"" + args[0] + "\"" + " is not directory. Enter root folder");
        } else if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("\"" + args[1] + "\"" + " is not file extension. Enter file extension. Example: \".java\"");
        }
        return Paths.get(args[0]);
    }
}
