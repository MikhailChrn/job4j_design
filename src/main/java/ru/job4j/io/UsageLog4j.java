package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        short randomShort = 40;
        byte randomByte = 83;
        long randomLongNumber = 1234567890987654321L;
        int numberOfCurrentYear = 2023;
        double randomDoubleNumber = 99.99;
        float randomFloat = 77.77F;
        char anyChar = 'a';
        boolean isReady = true;

        LOG.debug("short : {}, byte : {}, long : {}, int : {}, double {}, "
                        + "float : {}, char : {}, boolean : {}",
                randomShort, randomByte, randomLongNumber,
                numberOfCurrentYear, randomDoubleNumber,
                randomFloat, anyChar, isReady);
    }
}
