package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private List<Car> cars;
    private List<Truck> trucks;
    private int freeCarPlaces;
    private int freeTruckPlaces;

    public SimpleParking(int freeCarPlaces, int freeTruckPlaces) {
        this.freeCarPlaces = freeCarPlaces;
        this.freeTruckPlaces = freeTruckPlaces;
        this.cars = new ArrayList<>(freeCarPlaces);
        this.trucks = new ArrayList<>(freeTruckPlaces);
    }

    @Override
    public boolean park(Vehicle vehicle) {
        return false;
    }
}
