package ru.job4j.odd.isp.menu;

import java.util.List;
import java.util.Scanner;

public class TODOApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        work(scanner, menu, menuPrinter);
    }

    private static void work(Scanner scanner, Menu menu, MenuPrinter menuPrinter) {
        boolean exit = false;
        while (!exit) {
            showMenu();
            System.out.println("Ваш выбор: ");
            String next = scanner.nextLine();
            exit = isExit(scanner, menu, menuPrinter, exit, next);
        }
    }

    private static boolean isExit(Scanner scanner, Menu menu, MenuPrinter menuPrinter, boolean exit, String next) {
        switch (next) {
            case "1":
                System.out.println("Введите имя новой задачи: ");
                menu.add(Menu.ROOT, scanner.nextLine(), System.out::println);
                break;
            case "2":
                System.out.println("Введите имя задачи: ");
                String parentTask = scanner.nextLine();
                System.out.println("Введите имя подзадачи: ");
                menu.add(parentTask, scanner.nextLine(), System.out::println);
                break;
            case "3":
                menuPrinter.print(menu);
                break;
            case "4":
                exit = true;
                break;
            default:
                System.out.println("Уажите значение от 1 до 4");
                break;
        }
        return exit;
    }

    private static void showMenu() {
        List.of("1. Добавить новую задачу", "2. Добавить подзадачу", "3. Показать список задач", "4. Выход")
                .forEach(System.out::println);
    }
}
