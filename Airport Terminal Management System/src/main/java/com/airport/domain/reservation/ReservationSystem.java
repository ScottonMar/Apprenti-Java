package com.airport.domain.reservation;

import com.airport.domain.model.Passenger;
import com.airport.domain.loyalty.LoyaltyProgram;

import java.math.BigDecimal;
import java.util.*;

public class ReservationSystem {
    // Map flight number → list of passengers
    private Map<String, List<Passenger>> reservations = new HashMap<>();

    // Map passport number → LoyaltyProgram
    private Map<String, LoyaltyProgram> loyaltyMap = new HashMap<>();

    // Add a passenger to a flight
    public void addReservation(String flightNumber, Passenger passenger) {
        reservations.computeIfAbsent(flightNumber, k -> new ArrayList<>()).add(passenger);
    }

    // Get all passengers for a flight
    public List<Passenger> getPassengersForFlight(String flightNumber) {
        return reservations.getOrDefault(flightNumber, new ArrayList<>());
    }

    // Set a loyalty program for a passenger (by passport number)
    public void setLoyaltyProgram(String passportNumber, LoyaltyProgram program) {
        loyaltyMap.put(passportNumber, program);
    }

    // Get discounted ticket price based on loyalty program
    public BigDecimal getDiscountedPrice(String passportNumber, BigDecimal ticketPrice) {
        LoyaltyProgram program = loyaltyMap.get(passportNumber);
        if (program != null) {
            return program.applyDiscount(ticketPrice);
        }
        return ticketPrice; // no discount if not in loyaltyMap
    }

    // Get internal reservations map (useful for CSV writing)
    public Map<String, List<Passenger>> getAllReservations() {
        return reservations;
    }
}
