package ru.job4j.odd.dip;

/**
 * Класс Music зависит от YandexMusic, если нужно будет добавить воспроизведение музыки из ВК то придется изменять класс.
 */

public class Music {
    private YandexMusic yandexMusic;

    public void play() {
        yandexMusic = new YandexMusic();
        yandexMusic.getMusic();
    }
}

class YandexMusic {
    public void getMusic() {
        System.out.println("Play music from YandexMusic");
    }
}
