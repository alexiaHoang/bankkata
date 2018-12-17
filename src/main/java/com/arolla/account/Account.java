package com.arolla.account;

import com.arolla.exception.IllegalAmountException;

public interface Account {

    void deposit(Amount amout);

    void withdraw(Amount amount) throws IllegalAmountException;

    String printHistory();
}
