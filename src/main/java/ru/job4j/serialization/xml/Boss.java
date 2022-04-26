package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "boss")
public class Boss {
    @XmlAttribute
    private String phoneNumber;
    @XmlAttribute
    private String name;

    public Boss() {

    }

    public Boss(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Boss{"
                + "phoneNumber='" + phoneNumber + '\''
                + ", name='" + name + '\'' + '}';
    }
}
