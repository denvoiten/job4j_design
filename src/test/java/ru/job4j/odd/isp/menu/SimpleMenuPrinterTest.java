package ru.job4j.odd.isp.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SimpleMenuPrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenPrintMenu() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Приготовить ужин", STUB_ACTION);
        menu.add("Приготовить ужин", "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить мясо", STUB_ACTION);
        menu.add("Купить продукты", "Купить соус", STUB_ACTION);
        menu.add(Menu.ROOT, "Сходить на тренировку", STUB_ACTION);
        menu.add(Menu.ROOT, "Съездить в парк", STUB_ACTION);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new SimpleMenuPrinter().print(menu);
        System.setOut(System.out);
        String expected = String.join(System.lineSeparator(),
                "1.Приготовить ужин",
                "    1.1.Сходить в магазин",
                "        1.1.1.Купить продукты",
                "            1.1.1.1.Купить мясо",
                "            1.1.1.2.Купить соус",
                "2.Сходить на тренировку",
                "3.Съездить в парк" + System.lineSeparator());
        assertEquals(expected, out.toString());
    }

}