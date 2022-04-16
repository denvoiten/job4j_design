package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Denis"));
        assertThat(config.value("surname"), is("Voitenko"));
    }

    @Test
    public void whenPairWithComments() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bruce"));
        assertThat(config.value("surname"), is("Wayne"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithNoName() {
        String path = "./data/pair_without_name.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithNoSurname() {
        String path = "./data/pair_without_surname.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithNameTwice() {
        String path = "./data/pair_with_name_twice.properties";
        Config config = new Config(path);
        config.load();
    }
}