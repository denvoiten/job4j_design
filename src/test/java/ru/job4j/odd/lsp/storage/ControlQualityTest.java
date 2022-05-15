package ru.job4j.odd.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {
    private final LocalDate date = LocalDate.now();
    private final Storage warehouse = new Warehouse();
    private final Storage shop = new Shop();
    private final Storage trash = new Trash();
    private final List<Storage> stores = List.of(warehouse, shop, trash);
    private final ControlQuality controlQuality = new ControlQuality();

    @Test
    public void whenDistributionDone() {
        Food milk = new Food("Молоко", date.plusDays(3), date.minusDays(2), 86.99, 55);
        Food chocolate = new Food("Шоколад", date.plusDays(25), date.minusDays(5), 46.99, 35.99);
        Food bread = new Food("Хлеб", date.plusDays(4), date.minusDays(2), 80.99, 40);
        Food tomatoes = new Food("Томаты", date.plusDays(14), date.minusDays(1), 178.99, 128);
        Food fish = new Food("Рыба", date.minusDays(12), date.minusDays(30), 300, 150);
        List<Food> foodList = List.of(fish, milk, chocolate, bread, tomatoes);
        controlQuality.distribution(foodList, stores);
        assertEquals(2, warehouse.getFoodList().size());
        assertEquals(2, shop.getFoodList().size());
        assertEquals(1, trash.getFoodList().size());
        assertFalse(warehouse.getFoodList().contains(fish));
        assertFalse(shop.getFoodList().contains(fish));
        assertTrue(trash.getFoodList().contains(fish));
    }

    @Test
    public void whenHasDiscount() {
        Food chocolate = new Food("Шоколад", date.plusDays(5), date.minusDays(22), 46.99, 35.99);
        List<Food> foodList = List.of(chocolate);
        controlQuality.distribution(foodList, stores);
        assertTrue(shop.getFoodList().contains(chocolate));
        assertEquals(chocolate.getPrice(), chocolate.getDiscount(), 2);
    }

    @Test
    public void whenAllFoodExpired() {
        Food milk = new Food("Молоко", date.minusDays(3), date.minusDays(6), 86.99, 55);
        Food chocolate = new Food("Шоколад", date.minusDays(3), date.minusDays(15), 46.99, 35.99);
        Food bread = new Food("Хлеб", date.minusDays(1), date.minusDays(5), 80.99, 40);
        Food tomatoes = new Food("Томаты", date.minusDays(1), date.minusDays(6), 178.99, 128);
        Food fish = new Food("Рыба", date.minusDays(2), date.minusDays(30), 300, 150);
        List<Food> foodList = List.of(fish, milk, chocolate, bread, tomatoes);
        controlQuality.distribution(foodList, stores);
        assertEquals(5, trash.getFoodList().size());
    }
}