package com.engeto.Lekce07Project1;

import java.util.Scanner;
public class Settings {

    private static final String INPUT = "vat-eu.csv";
    private static final String DELIMITER = "\t";
    private static double LIMIT = 20;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static String OUTPUT = "vat-over-" + LIMIT + ".txt";

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

    public static void setLimit() {
        LIMIT = readLimit();
        OUTPUT = "vat-over-" + LIMIT + ".txt";
    }

    private static double readLimit() {
    double limit = LIMIT;
    String inputText = SCANNER.nextLine();
    try {
        limit = Double.parseDouble(inputText);
    } catch (NumberFormatException e) {
        System.err.println("Zadaný vstup nelze převést na číslo (" + inputText + "). Použita výchozí hodnota " + LIMIT + " %.\n");
    }
        return limit;
    }

}