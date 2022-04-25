package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String error = "Server down";
        int age = 33;
        byte weight = 72;
        short height = 181;
        long numberOf = 9379992;
        float countOf = 155.5F;
        double salary = 5000;
        boolean booleAn = true;
        char symbol = 'M';
        LOG.error("Trace info : error : {}, booleAn : {}, symbol : {}", error, booleAn, symbol);
        LOG.debug("Debug info : age : {}, weight : {}, height : {}", age, weight, height);
        LOG.info("The info : numberOf : {}, countOf : {}, salary : {}", numberOf, countOf, salary);
    }
}
