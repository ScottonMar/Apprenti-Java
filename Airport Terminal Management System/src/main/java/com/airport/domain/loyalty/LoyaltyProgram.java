package com.airport.domain.loyalty;

import java.math.BigDecimal;

public interface LoyaltyProgram {
    BigDecimal applyDiscount(BigDecimal ticketPrice);
}
