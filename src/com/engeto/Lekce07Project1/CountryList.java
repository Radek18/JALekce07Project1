package com.engeto.Lekce07Project1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountryList {

    private List<Country> countries = new ArrayList<>();

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void addCountry (Country country) {
        countries.add(country);
    }

    public void readCountryFromFile (String input) throws Exception {

        int lineNumber = 0;
        String nextLine;
        String[] items;
        Country newCountry;
        List<String> errorMessages = new ArrayList<>();

        String code;
        String name;
        String vatFullText;
        String vatLowText;
        double vatFull;
        double vatLow;
        boolean specialVat;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(input)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                nextLine = scanner.nextLine();
                try {
                    items = nextLine.split(Support.delimiter());
                    code = items[0];
                    name = items[1];
                    vatFullText = items[2];
                    vatLowText = items[3];
                    specialVat = Boolean.parseBoolean(items[4]);
                    vatFull = Support.parseDoubleWithReplace(vatFullText);
                    vatLow = Support.parseDoubleWithReplace(vatLowText);
                    newCountry = new Country(code, name, vatFull, vatLow, specialVat);
                    countries.add(newCountry);
                } catch (NumberFormatException e) {
                    errorMessages.add("Nesprávný formát čísla na řádku " + lineNumber + ": " + e.getLocalizedMessage());
                } catch (Exception e) {
                    errorMessages.add("Jiná chyba na řádku " + lineNumber + ": " + e.getLocalizedMessage());
                }
            }
            if (errorMessages.size() > 0) {
                throw new MyException(errorMessages);
            }
        }

    }

}