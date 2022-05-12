package ru.job4j.odd.report;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Employees")
public class StoreEmployee {
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public StoreEmployee() {
    }

    public StoreEmployee(List<Employee> employees) {
        this.employees = employees;
    }
}
