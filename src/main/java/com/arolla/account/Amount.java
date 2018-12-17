package com.arolla.account;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {

    private BigDecimal value;

    public Amount(BigDecimal value) {
        this.value = value;
    }

    public Amount plus(Amount other) {
        return new Amount(this.value.add(other.value));
    }

    public Amount minus(Amount other) {
        return new Amount(this.value.subtract(other.value));
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isGreaterThan(Amount other) {
        return this.value.compareTo(other.value) >= 0;
    }

    public Amount negative() {
        return new Amount(this.value.negate());
    }
}
