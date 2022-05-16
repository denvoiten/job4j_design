package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private final List<Vehicle> cars;
    private final List<Vehicle> trucks;
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
        boolean rsl = false;
        int size = vehicle.getSize();
        if (size > Car.CAR_SIZE && freeTruckPlaces >= Car.CAR_SIZE) {
            trucks.add(vehicle);
            freeTruckPlaces--;
            rsl = true;
        } else if (size > Car.CAR_SIZE && freeTruckPlaces == 0 && size <= freeCarPlaces) {
            trucks.add(vehicle);
            freeCarPlaces -= size;
            rsl = true;
        } else if (size == Car.CAR_SIZE && freeCarPlaces >= size) {
            cars.add(vehicle);
            freeCarPlaces--;
            rsl = true;
        }
        return rsl;
    }
}
