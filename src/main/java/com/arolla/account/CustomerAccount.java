package com.arolla.account;

import com.arolla.exception.IllegalAmountException;
import com.arolla.transaction.TransactionHistory;

import java.util.stream.Collectors;

public class CustomerAccount implements Account {

    private TransactionHistory transactionHistory;

    public CustomerAccount(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    @Override
    public void deposit(Amount amount) {
        transactionHistory.addDeposit(amount);

    }

    @Override
    public void withdraw(Amount amount) throws IllegalAmountException {
        if (!isValidAmountForWithdraw(amount)) {
            throw new IllegalAmountException(amount);
        }
        transactionHistory.addWithdraw(amount);
    }

    @Override
    public String printHistory() {
        return transactionHistory.printHistory().stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    private boolean isValidAmountForWithdraw(Amount withdrawnAmount) {
        return transactionHistory.getBalance().isGreaterThan(withdrawnAmount);
    }
}
