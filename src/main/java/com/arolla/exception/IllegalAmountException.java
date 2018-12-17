package com.arolla.exception;

import com.arolla.account.Amount;

public class IllegalAmountException extends Exception {

    private static final long serialVersionUID = -9204191749972551931L;
    private Amount amount;

    public IllegalAmountException(Amount amount) {
        this.amount = amount;
    }

    public String toString() {
        return "Illegal amount : " + amount.toString();
    }
}
