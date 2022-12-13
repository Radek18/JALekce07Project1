package com.engeto.Lekce07Project1;

import java.util.Comparator;

public class CountryVatHighComparator implements Comparator<Country> {

    @Override
    public int compare(Country firstCountry, Country secondCountry) {
        return secondCountry.getVatHigh().compareTo(firstCountry.getVatHigh());
    }

}