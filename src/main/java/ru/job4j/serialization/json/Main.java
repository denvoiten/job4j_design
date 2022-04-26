package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        JSONObject jsonBoss = new JSONObject("{\"phoneNumber\":\"+7(924)111-111-11-11\", \"name\":\"Denis\"}");

        List<String> list = new ArrayList<>();
        list.add("male");
        list.add("developer");
        JSONArray jsonInfo = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", employee.getName());
        jsonObject.put("available", employee.isAvailable());
        jsonObject.put("yearsOfExperience", employee.getYearsOfExperience());
        jsonObject.put("boss", jsonBoss);
        jsonObject.put("info", jsonInfo);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(employee));
    }
}

