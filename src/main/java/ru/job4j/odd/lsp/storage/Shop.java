package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        double freshness = getFreshness(food);
        if (freshness >= 25 && freshness < 100) {
            if (freshness > 75) {
                food.setPrice(food.getDiscount());
            }
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
