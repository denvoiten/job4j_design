package ru.job4j.odd.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleParkingTest {

    @Test
    public void whenCarAndPlacesAvailableThenPark() {
        Car car = new Car();
        Parking simpleParking = new SimpleParking(1, 0);
        assertTrue(simpleParking.park(car));
    }

    @Test
    public void whenTruckAndPlacesAvailableThenPark() {
        Truck truck = new Truck(3);
        Parking simpleParking = new SimpleParking(0, 1);
        assertTrue(simpleParking.park(truck));
    }

    @Test
    public void whenTruckAndPlacesNotAvailableThenNotPark() {
        Truck truck = new Truck(2);
        Parking simpleParking = new SimpleParking(1, 0);
        assertFalse(simpleParking.park(truck));
    }

    @Test
    public void whenCarAndPlacesNotAvailableThenNotPark() {
        Car car = new Car();
        Parking simpleParking = new SimpleParking(0, 4);
        assertFalse(simpleParking.park(car));
    }

    @Test
    public void whenTwoCarsTwoTruckAndPlacesAvailableThenPark() {
        Car car = new Car();
        Car car2 = new Car();
        Truck truck = new Truck(3);
        Truck truck2 = new Truck(2);
        Parking simpleParking = new SimpleParking(6, 0);
        assertTrue(simpleParking.park(car));
        assertTrue(simpleParking.park(car2));
        assertTrue(simpleParking.park(truck));
        assertFalse(simpleParking.park(truck2));
    }

    @Test
    public void whenTwoCarsAndPlacesNotAvailableThenNotPark() {
        Car car = new Car();
        Car car2 = new Car();
        Parking simpleParking = new SimpleParking(1, 3);
        assertTrue(simpleParking.park(car));
        assertFalse(simpleParking.park(car2));
    }
}