package com.engeto.Lekce07Project1;

import java.util.ArrayList;
import java.util.List;

public class MyException extends Exception {

    private final List<String> errorMessages;

    public MyException(List<String> errorMessages) {
        super("Upozornění! Při čtení ze souboru nastaly chyby!");
        this.errorMessages = new ArrayList<>(errorMessages);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

}