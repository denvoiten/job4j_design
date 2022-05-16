package ru.job4j.odd.isp;

/**
 * Сейчас в это трудно поверить, но бывают телефоны без камеры, но ему все же придется реализовать метод takePhoto();
 */

public interface Phone {

    void call();

    void sms();

    void takePhoto();
}
