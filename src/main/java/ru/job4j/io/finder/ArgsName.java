package ru.job4j.io.finder;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final String ln = System.lineSeparator();
    final String message = "Parameters must be passed in the format:" + ln
            + "-d=[path] -n=[file] -t=[mask|name|regex] -o=[filename]log.txt" + ln
            + "-d - директория, в которой начинать поиск." + ln
            + "-n - имя файла, маска, либо регулярное выражение для поиска." + ln
            + "-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению." + ln
            + "-o - имя файла для записи результата." + ln;

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The key \"" + key + "\" does not exist.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Parameters not specified. " + message);
        }
        if (args.length != 4) {
            throw new IllegalArgumentException(message);
        }
        for (String str : args) {
            String[] valid = validate(str);
            values.put(valid[0], valid[1]);
        }
    }

    private String[] validate(String str) {
        if (!str.startsWith("-") || !str.contains("=")) {
            throw new IllegalArgumentException(message);
        }
        String[] strSplit = str.replaceFirst("-", "").split("=", 2);
        if (strSplit[0].isEmpty() || strSplit[1].isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return strSplit;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding="});
        System.out.println(zip.get("out"));
    }
}
