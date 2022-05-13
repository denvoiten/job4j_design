package ru.job4j.odd.lsp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Не все условия базового класса сохранены в подклассе
 */
public class Reader {
    private String path;

    public Reader(String path) {
        validate(path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void validate(String path) {
        File in = Paths.get(path).toFile();
        if (!in.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", in.getAbsoluteFile()));
        }
        if (!in.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", in.getAbsolutePath()));
        }
    }

    public void read(String path) {
        String rsl = null;
        try {
            rsl = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(rsl);
    }
}

class ReadDirectory extends Reader {

    public ReadDirectory(String path) {
        super(path);
    }

    @Override
    public void read(String path) {
        super.read(path);
    }
}
