package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean available;
    @XmlAttribute
    private int yearsOfExperience;
    private Boss boss;

    @XmlElementWrapper(name = "infos")
    @XmlElement(name = "info")
    private String[] info;

    public Employee() {
    }

    public Employee(String name, boolean available, int yearsOfExperience, Boss boss, String[] info) {
        this.name = name;
        this.available = available;
        this.yearsOfExperience = yearsOfExperience;
        this.boss = boss;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", available=" + available
                + ", yearsOfExperience=" + yearsOfExperience
                + ", boss=" + boss + ", info="
                + Arrays.toString(info) + '}';
    }
}
