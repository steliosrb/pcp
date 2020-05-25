package com.trgint.pcp.producer.helper;

import java.util.Random;

public class RandomHelper {

    public static int randomIntBetween(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
