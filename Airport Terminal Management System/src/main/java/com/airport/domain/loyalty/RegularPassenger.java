package com.airport.domain.loyalty;

import java.math.BigDecimal;

public class RegularPassenger implements LoyaltyProgram {
    @Override
    public BigDecimal applyDiscount(BigDecimal ticketPrice) {
        return ticketPrice; // No discount
    }
}
