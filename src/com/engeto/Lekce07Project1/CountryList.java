package com.engeto.Lekce07Project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public void readCountryFromFile (String input) throws FileNotFoundException {

        int lineNumber = 0;
        String nextLine;
        String[] items;
        Country newCountry;

        String code;
        String name;
        String vatHighString;
        String vatLowString;
        String newVatHighString;
        String newVatLowString;
        double vatHigh;
        double vatLow;
        boolean specialVat;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(input)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                nextLine = scanner.nextLine();
                items = nextLine.split(Settings.delimiter());
                code = items[0];
                name = items[1];
                vatHighString = items[2];
                vatLowString = items[3];
                if (vatHighString.contains(",")) {
                    newVatHighString = vatHighString.replace(",", ".");
                } else newVatHighString = vatHighString;
                if (vatLowString.contains(",")) {
                    newVatLowString = vatLowString.replace(",", ".");
                } else newVatLowString = vatLowString;
                specialVat = Boolean.parseBoolean(items[4]);
                vatHigh = Double.parseDouble(newVatHighString);
                vatLow = Double.parseDouble(newVatLowString);
                newCountry = new Country(code, name, vatHigh, vatLow, specialVat);
                countries.add(newCountry);
            }

        }

    }

}