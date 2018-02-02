package com.heimdall.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class StringUtils {
    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static String getRandomPassword() {
        String uppercases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercases = "abcdefghijklmnopqrstuvwxyz";
        String numeric = "0123456789";
        String symbols = "~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String passJoined = RandomStringUtils.random(3, uppercases) +
                RandomStringUtils.random(3, lowercases) +
                RandomStringUtils.random(3, numeric) +
                RandomStringUtils.random(3, symbols);
        char[] lists = passJoined.toCharArray();
        shuffleCharArray(lists);
        return new String(lists);
    }

    private static void shuffleCharArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            char current = array[i];
            int randomPos = RandomUtils.nextInt(0, array.length - 1);
            char newCharAtPos = array[randomPos];
            array[i] = newCharAtPos;
            array[randomPos] = current;
        }
    }
}
