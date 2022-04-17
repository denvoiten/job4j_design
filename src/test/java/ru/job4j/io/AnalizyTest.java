package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableTest() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("300 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("200 11:01:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(result::append);
        }
        assertThat(result.toString(), is("10:58:01;11:01:02;"));
    }

    @Test
    public void unavailableTest2Line() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 12:03:00");
            out.println("400 12:04:00");
            out.println("500 12:06:10");
            out.println("300 12:07:30");
            out.println("200 12:08:22");
            out.println("400 12:09:00");
            out.println("500 12:11:33");
            out.println("200 12:12:11");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(result::append);
        }
        assertThat(result.toString(), is("12:04:00;12:07:30;12:09:00;12:12:11;"));
    }
}