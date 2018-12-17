package com.arolla.account;

import com.arolla.exception.IllegalAmountException;
import com.arolla.transaction.DatePrinter;
import com.arolla.transaction.TransactionHistory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerAccountTest {

    private Account account;
    @Mock
    private DatePrinter datePrinter;

    public static final String DATE = "2018/12/16";

    @Before
    public void setUp() {
        TransactionHistory transactionHistory = new TransactionHistory(datePrinter);
        account = new CustomerAccount(transactionHistory);
    }


    @Test
    public void transactionHistory_should_contain_all_transactions() throws IllegalAmountException {
        when(datePrinter.printDate()).thenReturn(DATE);
        final BigDecimal bigDecimal = createValue("100");
        account.deposit(new Amount(bigDecimal));
        account.deposit(new Amount(bigDecimal));
        account.deposit(new Amount(bigDecimal));
        account.withdraw(new Amount(createValue("50")));

        String result = "2018/12/16  100.00 100.00\n" +
                "2018/12/16  100.00 200.00\n" +
                "2018/12/16  100.00 300.00\n" +
                "2018/12/16  -50.00 250.00";

        assertEquals(result, account.printHistory());

    }

    @Test(expected = IllegalAmountException.class)
    public void withdraw_bigger_amount_than_balance_should_raise_an_exception() throws IllegalAmountException {
        when(datePrinter.printDate()).thenReturn(DATE);
        account.deposit(new Amount(createValue("100")));
        account.withdraw(new Amount(createValue("200")));
    }

    private BigDecimal createValue(String s) {
        return new BigDecimal(s).setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2);
    }
}
