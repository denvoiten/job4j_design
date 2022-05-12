package ru.job4j.odd.srp.report;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
