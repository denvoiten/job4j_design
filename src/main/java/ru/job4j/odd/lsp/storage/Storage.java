package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Storage {

    boolean accept(Food food);

    boolean add(Food food);

    List<Food> getFoodList();

    default double getFreshness(Food food) {
        return (double) ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now())
                / ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate()) * 100;
    }
}
