package ru.job4j.odd.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        menu.forEach(x -> System.out.println("    ".repeat(x.getNumber().split("\\.").length - 1)
                        + x.getNumber()
                        + x.getName()));
    }
}
