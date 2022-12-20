package com.engeto.Lekce07Project1;

public class Country implements Comparable<Country>{

    private String code;
    private String name;
    private double vatFull;
    private double vatLow;
    private boolean specialVat;

    public Country(String code, String name, double vatFull, double vatLow, boolean specialVat) {
        this.code = code;
        this.name = name;
        this.vatFull = vatFull;
        this.vatLow = vatLow;
        this.specialVat = specialVat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVatFull() {
        return vatFull;
    }

    public void setVatFull(double vatFull) {
        this.vatFull = vatFull;
    }

    public Double getVatLow() {
        return vatLow;
    }

    public void setVatLow(double vatLow) {
        this.vatLow = vatLow;
    }

    public boolean isSpecialVat() {
        return specialVat;
    }

    public void setSpecialVat(boolean specialVat) {
        this.specialVat = specialVat;
    }

    public String getDescription() {
        return getName() + " (" + getCode() + "): " + getVatFull() + " %";
    }

    @Override
    public int compareTo(Country secondCountry) {
        return this.getCode().compareTo(secondCountry.getCode());
    }

}