package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        try {
            argsName.get("d");
            argsName.get("e");
            argsName.get("o");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("The key \"d\" does not exist.")) {
                throw new IllegalArgumentException("Directory not specified.");
            } else if (e.getMessage().equals("The key \"e\" does not exist.")) {
                throw new IllegalArgumentException("Extension not specified.");
            } else if (e.getMessage().equals("The key \"o\" does not exist.")) {
                throw new IllegalArgumentException("Output not specified");
            }
        }
        if (!Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory \"" + argsName.get("d") + "\" doesn't exist");
        }
    }

    private static List<File> getListFiles(String path, String extension) throws IOException {
        return Search.search(Paths.get(path), p -> !p.toFile().getName().endsWith(extension))
                .stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<File> files = getListFiles(argsName.get("d"), argsName.get("e").replaceFirst("-", ""));
        packFiles(files, Paths.get(argsName.get("o")).toFile());
    }
}