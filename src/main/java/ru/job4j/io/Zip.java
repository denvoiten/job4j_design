package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    static String message = "Example: -d=c:\\project\\job4j\\ -e=.class -o=project.zip";

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

    private static String[] validate(String[] args) {
        ArgsName names = ArgsName.of(args);
        if (args.length != 3) {
            throw new IllegalArgumentException("Not all parameters entered. " + message);
        } else if (!Files.isDirectory(Path.of(names.get("d")))) {
            throw new IllegalArgumentException("Directory \"" + names.get("d") + "\" doesn't exist");
        }
        return new String[]{names.get("d"), names.get("e"), names.get("o")};
    }

    private static List<File> getListFiles(String path, String extension) throws IOException {
        return Search.search(Path.of(path), p -> !p.toFile().getName().endsWith(extension))
                .stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        String[] arrArgs = validate(args);
        List<File> files = getListFiles(String.valueOf(Path.of(arrArgs[0])), arrArgs[1]);
        packFiles(files, Path.of(arrArgs[2]).toFile());
    }
}