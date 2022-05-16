package ru.job4j.odd.isp;

/**
 * Устройство может иметь только один разъем, но ему придется реализовать методы для подключения через отсутствующие
 */

public interface Device {

    void connectTypeC();

    void connectMicroUSB();

    void connectVGA();
}
