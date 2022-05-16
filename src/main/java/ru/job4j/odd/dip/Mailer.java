package ru.job4j.odd.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * В классе явно указано хранилище и отправка осуществляется только через почту.
 */

public class Mailer {

    private final List<String> list = new ArrayList<>();

    public boolean send(String email, List<String> list) {
        return true;
    }
}
