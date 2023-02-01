package com.engeto.Lekce07Project1;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        CountryList countryList = new CountryList();

        try {
            countryList.readCountryFromFile(Support.input());
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
        Support.setLimitAndOutput();

        List<Country> countriesAboveLimit = new ArrayList<>();
        List<Country> countriesToLimit = new ArrayList<>();

        for (Country country : countryList.getCountries()) {
            if (country.getVatFull() > Support.getLimit() && ! country.isSpecialVat()) {
                countriesAboveLimit.add(country);
            } else countriesToLimit.add(country);
        }

        countriesAboveLimit.sort(new CountryVatFullComparator());
        Collections.sort(countriesToLimit);

        System.out.println("Výpis států se základní sazbou daně nad " + Support.getLimit() + " % seřazen podle základní sazby daně sestupně:");

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(Support.output())))) {
            for (Country country : countriesAboveLimit) {
                System.out.println(country.getDescription());
                writer.println(country.getDescription());
            }
            System.out.println("====================");
            writer.println("====================");
            System.out.print("Sazba VAT " + Support.getLimit() + " % nebo nižší nebo používají speciální sazbu: ");
            writer.print("Sazba VAT " + Support.getLimit() + " % nebo nižší nebo používají speciální sazbu: ");
            for (Country country : countriesToLimit) {
                System.out.print(country.getCode() + ", ");
                writer.print(country.getCode() + ", ");
            }
        } catch (IOException e) {
                System.err.println("Nastala chyba při zápisu do souboru!");
        }

    }

}