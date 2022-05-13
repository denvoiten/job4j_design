package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (LocalDate.now().isAfter(food.getExpiryDate())) {
            foodList.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> toList() {
        return foodList;
    }
}
