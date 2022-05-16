package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    public void resort(List<Storage> storageList) {
        List<Food> foodList = new ArrayList<>();
        for (Storage storage : storageList) {
            foodList.addAll(storage.getFoodList());
            storage.removeAll();
        }
        distribution(foodList, storageList);
    }

    public void distribution(List<Food> foodList, List<Storage> storageList) {
        for (Food food : foodList) {
            for (Storage storage : storageList) {
                if (storage.accept(food)) {
                    storage.add(food);
                    break;
                }
            }
        }
    }
}
