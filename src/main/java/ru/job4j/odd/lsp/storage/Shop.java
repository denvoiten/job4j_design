package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return getFreshness(food) >= 25 && getFreshness(food) < 100;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            if (getFreshness(food) > 75) {
                food.setPrice(food.getDiscount());
            }
            foodList.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(foodList);
    }

    @Override
    public void removeAll() {
        foodList.clear();
    }
}
