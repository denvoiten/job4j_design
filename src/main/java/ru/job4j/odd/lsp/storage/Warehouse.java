package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (getFreshness(food) < 25) {
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
