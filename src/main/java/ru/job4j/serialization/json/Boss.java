package ru.job4j.serialization.json;

public class Boss {
    private String phoneNumber;
    private String name;

    public Boss(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Boss{"
                + "phoneNumber='" + phoneNumber + '\''
                + ", name='" + name + '\'' + '}';
    }
}
