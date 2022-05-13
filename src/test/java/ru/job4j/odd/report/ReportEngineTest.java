package ru.job4j.odd.report;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringJoiner;

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
                .append(worker.getSalary() / ReportForAccounting.EXCHANGE_RATE).append("$").append(";")
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

    @Test
    public void whenJSONGenerated() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportJSON(store);
        String expect = "[{\"name\":\"" + worker.getName()
                + "\",\"hired\":{\"year\":" + now.get(Calendar.YEAR)
                + ",\"month\":" + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":" + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":" + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":" + now.get(Calendar.MINUTE)
                + ",\"second\":" + now.get(Calendar.SECOND)
                + "},\"fired\":{\"year\":" + now.get(Calendar.YEAR)
                + ",\"month\":" + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":" + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":" + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":" + now.get(Calendar.MINUTE)
                + ",\"second\":" + now.get(Calendar.SECOND)
                + "},\"salary\":" + worker.getSalary() + "}]";
        System.out.println(expect);
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenXMLGenerated() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String dateToString = date.format(now.getTime());
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Sergey", now, now, 1000);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportXML(store);
        StringJoiner expect = new StringJoiner("\n");
        expect.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .add("<Employees>")
                .add("    <employee>")
                .add(String.format("        <fired>%s</fired>", dateToString))
                .add(String.format("        <hired>%s</hired>", dateToString))
                .add("        <name>Ivan</name>")
                .add("        <salary>100.0</salary>")
                .add("    </employee>")
                .add("    <employee>")
                .add(String.format("        <fired>%s</fired>", dateToString))
                .add(String.format("        <hired>%s</hired>", dateToString))
                .add("        <name>Sergey</name>")
                .add("        <salary>1000.0</salary>")
                .add("    </employee>")
                .add("</Employees>\n");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}