package ru.job4j.odd.report;

import java.util.function.Predicate;

public class ReportForAccounting implements Report {
    public static final double EXCHANGE_RATE = 68.84;
    private Store store;

    public ReportForAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() / EXCHANGE_RATE).append("$").append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
