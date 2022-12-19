package ru.abakumova.appealsapp.exceptions;

import ru.abakumova.appealsapp.models.Account;

public class NoEntityException extends Exception {

    public NoEntityException(Class classNotFound) {
        super("the " + classNotFound.getName() + " object was not found");
    }
}
