package com.engeto.Lekce07Project1;

public class Settings {


    private static final String INPUT = "vat-eu.csv";
    private static double LIMIT = 20;
    private static String OUTPUT = "vat-over-" + LIMIT + ".txt";
    private static final String DELIMITER = "\t";

    public static String delimiter() {
        return DELIMITER;
    }

    public static String input() {
        return INPUT;
    }

    public static String output() {
        return OUTPUT;
    }

    public static double getLimit() {
        return LIMIT;
    }

    public static void setLimit(double limit) {
        LIMIT = limit;
        OUTPUT = "vat-over-" + limit + ".txt";
    }

}