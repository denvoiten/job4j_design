package ru.job4j.odd.report;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
