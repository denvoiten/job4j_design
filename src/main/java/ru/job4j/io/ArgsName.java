package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The key \"" + key + "\" does not exist.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String str : args) {
            values.put(validate(str)[0], validate(str)[1]);
        }
    }

    private String[] validate(String str) {
        if (!str.startsWith("-") || !str.contains("=")) {
            throw new IllegalArgumentException("Parameters must be passed in the format \"-key=value\".");
        }
        String[] strSplit = str.replaceFirst("-", "").split("=", 2);
        if (strSplit[0].isEmpty() || strSplit[1].isEmpty()) {
            throw new IllegalArgumentException("Parameters must be passed in the format \"-key=value\".");
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
