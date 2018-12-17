package com.arolla.transaction;

import com.arolla.account.Amount;
import com.arolla.account.CustomerAccount;
import com.arolla.exception.IllegalAmountException;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TransactionHistoryTest {

    private TransactionHistory transactionHistory;
    private CustomerAccount account;
    private DatePrinter datePrinter = new DatePrinter();

    @Before
    public void setUp() {
        transactionHistory = new TransactionHistory(datePrinter);
        account = new CustomerAccount(transactionHistory);
    }

    @Test
    public void transactionHistory_should_contain_all_transactions() throws IllegalAmountException {
        account.deposit(new Amount(createValue("100")));
        account.deposit(new Amount(createValue("100")));
        account.deposit(new Amount(createValue("100")));
        account.deposit(new Amount(createValue("100")));
        account.withdraw(new Amount(createValue("50")));
        assertEquals(5, transactionHistory.getTransactions().size());
        assertEquals(new Amount((createValue("350"))), transactionHistory.getBalance());
    }

    private BigDecimal createValue(String s) {
        return new BigDecimal(s).setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2);
    }

}