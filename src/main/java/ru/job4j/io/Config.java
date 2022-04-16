package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines()
                    .filter(str -> !str.equals(""))
                    .filter(str -> !str.contains("#"))
                    .filter(this::check)
                    .map(str -> str.split("=", 2))
                    .forEach(str -> values.put(str[0], str[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean check(String str) {
        String[] strSplit = str.split("=", 2);
        if (strSplit[0].isEmpty() || strSplit[1].isEmpty()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties"));
    }
}
