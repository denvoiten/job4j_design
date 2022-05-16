package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private final List<Car> cars;
    private final List<Truck> trucks;
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
        if (size > 1 && freeTruckPlaces >= 1) {
            trucks.add((Truck) vehicle);
            freeTruckPlaces--;
            rsl = true;
        } else if (size > 1 && freeTruckPlaces == 0) {
            if (size <= freeCarPlaces) {
                trucks.add((Truck) vehicle);
                freeCarPlaces -= size;
                rsl = true;
            }
        } else if (size == 1 && freeCarPlaces >= size) {
            cars.add((Car) vehicle);
            freeCarPlaces--;
            rsl = true;
        }
        return rsl;
    }
}
