package com.engeto.Lekce07Project1;

import java.io.*;
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

        System.out.println("Zadej sazbu daně jako limit.");
        Scanner sc = new Scanner(System.in);
        String limitString = sc.nextLine();
        Settings.setLimit(Double.parseDouble(limitString));
        System.out.println("Výpis států se základní sazbou daně nad " + Settings.getLimit() + " podle základní sazby daně sestupně:");

        CountryList countriesAboveLimit = new CountryList();
        CountryList countriesToLimit = new CountryList();

        for (Country country : countryList.getCountries()) {
            if (country.getVatHigh() > Settings.getLimit() && ! country.isSpecialVat()) {
                countriesAboveLimit.addCountry(country);
            } else countriesToLimit.addCountry(country);
        }

        List<Country> copyOfCountriesAboveLimit = new ArrayList<>(countriesAboveLimit.getCountries());
        List<Country> copyOfCountriesToLimit = new ArrayList<>(countriesToLimit.getCountries());

        copyOfCountriesAboveLimit.sort(new CountryVatHighComparator());
        Collections.sort(copyOfCountriesToLimit);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(Settings.output())))) {
        for (Country country : copyOfCountriesAboveLimit) {
            System.out.println(country.getDescription());
            writer.println(country.getDescription());
            }
            System.out.println("====================");
            writer.println("====================");
            System.out.print("Sazba VAT " + Settings.getLimit() + " % nebo nižší nebo používají speciální sazbu: ");
            writer.print("Sazba VAT " + Settings.getLimit() + " % nebo nižší nebo používají speciální sazbu: ");
        for (Country country : copyOfCountriesToLimit) {
            System.out.print(country.getCode() + ", ");
            writer.print(country.getCode() + ", ");
            }
        } catch (IOException e) {
                System.err.println("Nastala chyba při zápisu do souboru!");
        }

    }

}