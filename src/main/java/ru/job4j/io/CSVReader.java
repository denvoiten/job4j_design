package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        Path path = Path.of(argsName.get("path"));
        validate(argsName, delimiter, out, path);
        List<String> filters = List.of(argsName.get("filter").split(","));
        dataOutput(out, delimiter, path, filters);
    }

    private static void dataOutput(String out, String delimiter, Path path, List<String> filters) throws IOException {
        String rsl = takeData(delimiter, path, getIndexes(path, filters, delimiter));
        if ("stdout".equals(out)) {
            System.out.println(rsl);
        } else {
            try (FileOutputStream dataOut = new FileOutputStream(out)) {
                dataOut.write(rsl.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    private static String takeData(String delimiter, Path path, List<Integer> indexes) {
        String rsl = "";
        try (Scanner sc = new Scanner(path)) {
            while (sc.hasNextLine()) {
                String[] dataLine = sc.nextLine().split(delimiter);
                for (int i = 0; i < indexes.size(); i++) {
                    if (i < indexes.size() - 1) {
                        rsl = rsl.concat(dataLine[indexes.get(i)]).concat(delimiter);
                    } else if (i == indexes.size() - 1) {
                        rsl = rsl.concat(dataLine[indexes.get(i)]).concat(System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private static List<Integer> getIndexes(Path path, List<String> filters, String delimiter) throws IOException {
        List<Integer> indexes = new ArrayList<>();
        try (Scanner sc = new Scanner(path)) {
            List<String> tags = List.of(sc.nextLine().split(delimiter));
            for (int i = 0; i < tags.size(); i++) {
                if (filters.contains(tags.get(i))) {
                    indexes.add(i);
                }
            }
        }
        return indexes;
    }

    private static void validate(ArgsName argsName, String delimiter, String out, Path path) {
        if (!(";".equals(delimiter) || ",".equals(delimiter))) {
            throw new IllegalArgumentException("Param -delimiter is not correct. Usage -delimiter=\";\" or \",\"");
        }
        if (!("stdout".equals(out))) {
            throw new IllegalArgumentException(
                    "Param -out is not correct. "
                            + "Usage -out=stdout for output to the console, "
                            + "or -out=File.CSV for output to file");
        }
        if (!Files.isRegularFile(Path.of(argsName.get("path")))) {
            throw new IllegalArgumentException(String.format("%s is not directory. Enter root folder", path));
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}

