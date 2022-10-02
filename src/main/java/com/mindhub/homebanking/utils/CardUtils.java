package com.mindhub.homebanking.utils;

public final class CardUtils {
    static int min = 1000;
    static int max = 9999;
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getStringRandomNumber() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    public static String getRandomCardNumber(){
        String cardNumber = "";
        for (int i = 0; i < 4; i++) {
            String part = getStringRandomNumber();
            cardNumber += ("-" + part);
        }
        return cardNumber.substring(1);
    }

    public static int getRandomCvv(){
        return getRandomNumber(100, 999);
    }
}
