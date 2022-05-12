package ru.job4j.odd.srp.report;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReportEngineTest {
    private final MemStore store = new MemStore();
    private final Calendar now = Calendar.getInstance();
    private final String ls = System.lineSeparator();

    @Test
    public void whenOldGenerated() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(ls)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(ls);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForDevs() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForDevs(store);
        StringBuilder expect = new StringBuilder()
                .append("<html>").append(ls)
                .append("<head>").append(ls)
                .append("<title>").append(ls)
                .append("Report for Programmers").append(ls)
                .append("</title>").append(ls)
                .append("</head>").append(ls)
                .append("<body>").append(ls)
                .append("Name; Hired; Fired; Salary;").append(ls)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";").append(ls)
                .append("</body>").append(ls)
                .append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForAccounting() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForAccounting(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(ls)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / 68.84).append("$").append(";")
                .append(ls);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForHR() {
        Employee worker = new Employee("Ivan", 1000);
        Employee worker2 = new Employee("Sergey", 2000);
        Employee worker3 = new Employee("Alex", 3000);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportForHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name;Salary;").append(ls)
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(ls)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(ls)
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(ls);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}