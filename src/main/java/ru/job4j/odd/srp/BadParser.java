package ru.job4j.odd.srp;

import java.util.List;
import java.util.Map;

/**
 * При имплементации интерфейса нужно будет риализовать
 * функционал, который может быть не нужен
 */

public interface BadParser {
    List<String> list(String link);

    void saveDB(List<String> list);

    Map<String, String> create();
}

