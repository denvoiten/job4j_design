package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleMapTest {
    private SimpleMap<Integer, String> map;

    @Before
    public void setUp() {
        map = new SimpleMap<>();
    }

    @Test
    public void whenPut10ElementsThenSize10() {
        for (int i = 0; i < 10; i++) {
            map.put(i, "Name" + 1);
        }
        Assert.assertEquals(10, map.size());
    }

    @Test
    public void whenPut10ElementsAndThenPut10ElementSize20() {
        for (int i = 0; i < 10; i++) {
            map.put(i, "Name" + 1);
        }
        Assert.assertEquals(10, map.size());
        for (int i = 10; i < 20; i++) {
            map.put(i, "Name" + 1);
        }
        Assert.assertEquals(20, map.size());
    }

    @Test
    public void whenPutTwice() {
        map.put(1, "Name1");
        assertFalse(map.put(1, "Name1"));
    }

    @Test
    public void whenRemoveTrue() {
        for (int i = 0; i < 10; i++) {
            map.put(i, "Name" + i);
        }
        Assert.assertEquals(10, map.size());
        Assert.assertTrue(map.remove(5));
    }

    @Test
    public void whenRemoveOnlyOnce() {
        for (int i = 0; i < 10; i++) {
            map.put(i, "Name" + i);
        }
        Assert.assertEquals(10, map.size());
        Assert.assertTrue(map.remove(5));
        Assert.assertEquals(9, map.size());
        Assert.assertFalse(map.remove(5));
    }


    @Test
    public void whenGetReturnRightValue() {
        for (int i = 0; i < 10; i++) {
            map.put(i, "Name" + i);
        }
        Assert.assertEquals("Name5", map.get(5));
    }

    @Test
    public void whenSize16AndExpandTable() {
        for (int i = 0; i < 16; i++) {
            map.put(i, "Name" + i);
        }
        Assert.assertEquals(16, map.size());
        Assert.assertTrue(map.put(16, "Name16"));
        Assert.assertTrue(map.put(17, "Name17"));
    }
}