package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(multiple(10).getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String multiple(int size) {
        StringBuilder rsl = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                rsl.append((row + 1) * (cell + 1)).append("\t");
            }
            rsl.append(System.lineSeparator());
        }
        return rsl.toString();
    }
}

