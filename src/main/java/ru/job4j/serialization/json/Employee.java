package ru.job4j.serialization.json;

import java.util.Arrays;

public class Employee {
    private String name;
    private boolean available;
    private int yearsOfExperience;
    private Boss boss;
    private String[] info;

    public Employee(String name, boolean available, int yearsOfExperience, Boss boss, String[] info) {
        this.name = name;
        this.available = available;
        this.yearsOfExperience = yearsOfExperience;
        this.boss = boss;
        this.info = info;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
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
