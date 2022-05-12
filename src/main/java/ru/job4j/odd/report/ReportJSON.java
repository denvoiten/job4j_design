package ru.job4j.odd.report;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportJSON implements Report {
    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return new GsonBuilder().create().toJson(store.findBy(filter));
    }
}
