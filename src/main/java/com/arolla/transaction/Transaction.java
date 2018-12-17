package com.arolla.transaction;

import com.arolla.account.Amount;


public class Transaction {

    private final Amount amount;
    private final Amount balance;
    private String date;


    public Transaction(Amount amount, Amount balance, String date) {
        this.amount = amount;
        this.balance = balance;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public String print() {
        return date + "  " + amount.toString() + " " + balance.toString();
    }
}
