package ru.job4j.odd.lsp.storage;

import java.util.List;

public class ControlQuality {
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
