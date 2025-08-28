package com.airport.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Flight {
    private final String flightNumber;
    private final LocalDate departureDate;
    private final BigDecimal ticketPrice;
    private final Aircraft aircraft;

    public Flight(String flightNumber, LocalDate departureDate, BigDecimal ticketPrice, Aircraft aircraft) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.ticketPrice = ticketPrice;
        this.aircraft = aircraft;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }
}
