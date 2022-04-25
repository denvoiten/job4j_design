package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[]{"Worker", "Married"});
        final Employee employee = new Employee("Denis", true, 2,
                new Boss("999-999", "Bob"),
                new String[]{"male", "developer"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        System.out.println(gson.toJson(employee));

        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";

        final String employeeJson =
                "{"
                        + "\"name\":\"Denis\","
                        + "\"available\":true,"
                        + "\"yearsOfExperience\":2,"
                        + "\"boss\":"
                        + "{"
                        + "\"phoneNumber\":\"999-999\","
                        + "\"name\":\"Bob\""
                        + "},"
                        + "\"info\":"
                        + "[\"male\",\"developer\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        final Employee empMod = gson.fromJson(employeeJson, Employee.class);
        System.out.println(personMod);
        System.out.println(empMod);
    }
}

