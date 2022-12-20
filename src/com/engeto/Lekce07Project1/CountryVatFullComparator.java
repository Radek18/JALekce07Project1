package com.engeto.Lekce07Project1;

import java.util.Comparator;

public class CountryVatFullComparator implements Comparator<Country> {

    @Override
    public int compare(Country firstCountry, Country secondCountry) {
        return secondCountry.getVatFull().compareTo(firstCountry.getVatFull());
    }

}