package com.engeto.Lekce07Project1;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        CountryList countryList = new CountryList();

        try {
            countryList.readCountryFromFile(Settings.input());
        } catch (FileNotFoundException e) {
            System.err.println("Chyba při čtení souboru: " + e.getLocalizedMessage());
        }

        for (Country country : countryList.getCountries()) System.out.println(country.getDescription());

        System.out.println();

        double limit = 20;
        CountryList countriesAboveLimit = new CountryList();
        CountryList countriesToLimit = new CountryList();

        for (Country country : countryList.getCountries()) {
            if (country.getVatHigh() > limit && ! country.isSpecialVat()) {
                countriesAboveLimit.addCountry(country);
            } else countriesToLimit.addCountry(country);
        }

        List<Country> copyOfCountriesAboveLimit = new ArrayList<>(countriesAboveLimit.getCountries());
        List<Country> copyOfCountriesToLimit = new ArrayList<>(countriesToLimit.getCountries());

        copyOfCountriesAboveLimit.sort(new CountryVatHighComparator());
        Collections.sort(copyOfCountriesToLimit);

        for (Country country : copyOfCountriesAboveLimit) System.out.println(country.getDescription());

        System.out.println("====================");

        System.out.print("Sazba VAT 20 % nebo nižší nebo používají speciální sazbu: ");

        for (Country country : copyOfCountriesToLimit) System.out.print(country.getCode() + ", ");

    }

}