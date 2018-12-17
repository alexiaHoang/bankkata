package com.arolla.transaction;

import com.arolla.account.Amount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class TransactionHistory {
    private DatePrinter datePrinter;
    private Amount balance;
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionHistory(DatePrinter datePrinter) {
        this.datePrinter = datePrinter;
        this.balance = new Amount(BigDecimal.ZERO);
    }

    public Amount getBalance() {
        return balance;
    }

    public void addDeposit(Amount amount) {
        this.balance = this.balance.plus(amount);
        final String date = datePrinter.printDate();
        transactions.add(new Transaction(amount, balance, date));
    }

    public void addWithdraw(Amount amount) {

        this.balance = this.balance.minus(amount);
        final String date = datePrinter.printDate();
        transactions.add(new Transaction(amount.negative(), balance, date));
    }

    public List<Transaction> getTransactions() {
        return unmodifiableList(transactions);
    }

    public List<String> printHistory() {
        return transactions.stream()
                .map(transaction -> transaction.print())
                .collect(Collectors.toList());
    }
}
