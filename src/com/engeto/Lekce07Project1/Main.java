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
        } catch (MyException e) {
            System.err.println(e.getLocalizedMessage() + "\nSeznam chyb:");
            for (String message : e.getErrorMessages()) {
                System.err.println("- " + message);
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        for (Country country : countryList.getCountries()) System.out.println(country.getDescription());

        System.out.println();

        System.out.println("Zadejte sazbu daně jako limit a stiskněte ENTER:");
        Settings.setLimit();

        CountryList countriesAboveLimit = new CountryList();
        CountryList countriesToLimit = new CountryList();

        for (Country country : countryList.getCountries()) {
            if (country.getVatFull() > Settings.getLimit() && ! country.isSpecialVat()) {
                countriesAboveLimit.addCountry(country);
            } else countriesToLimit.addCountry(country);
        }

        List<Country> copyOfCountriesAboveLimit = new ArrayList<>(countriesAboveLimit.getCountries());
        List<Country> copyOfCountriesToLimit = new ArrayList<>(countriesToLimit.getCountries());

        copyOfCountriesAboveLimit.sort(new CountryVatFullComparator());
        Collections.sort(copyOfCountriesToLimit);

        System.out.println("Výpis států se základní sazbou daně nad " + Settings.getLimit() + " % seřazen podle základní sazby daně sestupně:");

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