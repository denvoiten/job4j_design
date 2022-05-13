package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Storage {

    boolean add(Food food);

    List<Food> toList();

    default double getFreshness(Food food) {
        return (double) ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now())
                / ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate()) * 100;
    }
}
