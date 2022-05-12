package ru.job4j.odd.srp.report;

import java.util.function.Predicate;

public class ReportForDevs implements Report {
    private Store store;

    public ReportForDevs(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        String ls = System.lineSeparator();
        text.append("<html>").append(ls)
                .append("<head>").append(ls)
                .append("<title>").append(ls)
                .append("Report for Programmers").append(ls)
                .append("</title>").append(ls)
                .append("</head>").append(ls)
                .append("<body>").append(ls)
                .append("Name; Hired; Fired; Salary;").append(ls);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(ls).append("</body>").append(ls)
                    .append("</html>");
        }
        return text.toString();
    }
}
