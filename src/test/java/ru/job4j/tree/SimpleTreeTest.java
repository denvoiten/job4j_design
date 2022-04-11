package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class SimpleTreeTest {
    private SimpleTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new SimpleTree<>(1);
    }

    @Test
    public void when6ElFindLastThen6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }

    @Test
    public void whenTreeIsBinary() {
        tree.add(1, 0);
        tree.add(1, 4);
        tree.add(4, 3);
        tree.add(4, 6);
        tree.add(6, 8);
        tree.add(6, 11);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenTreeIsNotBinary() {
        tree.add(1, 0);
        tree.add(1, 4);
        tree.add(4, 3);
        tree.add(4, 6);
        tree.add(4, 8);
        assertFalse(tree.isBinary());
    }

}