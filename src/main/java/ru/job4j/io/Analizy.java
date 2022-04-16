package ru.job4j.io;

import java.io.*;

public class Analizy {
    boolean serverOn = true;

    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                in.lines().forEach(str -> check(sb, str));
                out.println(sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void check(StringBuilder sb, String str) {
        if ((str.contains("400 ") || str.contains("500 ")) && serverOn) {
            String[] strSplit = str.split(" ");
            sb.append(strSplit[1]).append(";");
            serverOn = false;
        } else if ((str.contains("200 ") || str.contains("300 ")) && !serverOn) {
            String[] strSplit = str.split(" ");
            sb.append(strSplit[1]).append(";").append(System.lineSeparator());
            serverOn = true;
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}

