package com.airport.domain.loyalty;

import java.math.BigDecimal;

public class VIPPassenger implements LoyaltyProgram {
    @Override
    public BigDecimal applyDiscount(BigDecimal ticketPrice) {
        BigDecimal discount = ticketPrice.multiply(BigDecimal.valueOf(0.20));
        return ticketPrice.subtract(discount);
    }
}
