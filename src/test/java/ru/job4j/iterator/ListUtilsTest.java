package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(15, 3, 4, 88, 55, 23, 42));
        ListUtils.removeIf(input, n -> n % 2 == 0);
        assertThat(input, is(Arrays.asList(15, 3, 55, 23)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(15, 3, 4, 88, 55, 23, 42));
        ListUtils.replaceIf(input, n -> n % 2 != 0, 0);
        assertThat(input, is(Arrays.asList(0, 0, 4, 88, 0, 0, 42)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(15, 3, 15, 4, 88, 55, 23, 3, 42));
        List<Integer> elements = new ArrayList<>(Arrays.asList(15, 3, 55, 23));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(4, 88, 42)));
    }
}