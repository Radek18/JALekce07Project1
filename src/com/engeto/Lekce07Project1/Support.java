package com.engeto.Lekce07Project1;

import java.util.Scanner;
public class Support {

    private static final String INPUT = "vat-eu.csv";
    private static final String DELIMITER = "\t";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static double limit = 20;
    private static String output;

    public static String delimiter() {
        return DELIMITER;
    }

    public static String input() {
        return INPUT;
    }

    public static String output() {
        return output;
    }

    public static double getLimit() {
        return limit;
    }

    public static void setLimitAndOutput() {
        limit = readLimit();
        output = "vat-over-" + limit + ".txt";
    }

    private static double readLimit() {
    double limit = Support.limit;
    String inputText = SCANNER.nextLine();
    try {
        limit = Double.parseDouble(inputText);
    } catch (NumberFormatException e) {
        System.err.println("Zadaný vstup nelze převést na číslo (" + inputText + "). Použita výchozí hodnota " + Support.limit + " %.\n");
    }
        return limit;
    }

    public static double parseDoubleWithReplace(String text) {
        double number;
        if (text.contains(",")) text = text.replace(",", ".");
        number = Double.parseDouble(text);
        return number;
    }

}