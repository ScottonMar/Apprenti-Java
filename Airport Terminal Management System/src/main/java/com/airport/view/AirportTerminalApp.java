package com.airport.view;

import com.airport.domain.loyalty.*;
import com.airport.domain.model.*;
import com.airport.domain.reservation.ReservationSystem;
import com.airport.data.CSVUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AirportTerminalApp {

    public static void main(String[] args) {
        // Create Aircrafts
        CommercialAircraft commercialAircraft = new CommercialAircraft("Boeing 737", 160, 26000, "American Airlines");
        PrivateJet privateJet = new PrivateJet("Gulfstream G650", 12, 15000, true, 900);

        // Create Flights
        Flight flight1 = new Flight("AA101", LocalDate.of(2025, 8, 1), new BigDecimal("500.00"), commercialAircraft);
        Flight flight2 = new Flight("PJ001", LocalDate.of(2025, 8, 1), new BigDecimal("500.00"), privateJet);

        // Create Passengers
        Passenger passenger1 = new Passenger("Alice Smith", "P12345");
        Passenger passenger2 = new Passenger("Bob VIP", "P67890");

        // Create Reservation System and add loyalty info
        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.setLoyaltyProgram(passenger1.getPassportNumber(), new RegularPassenger());
        reservationSystem.setLoyaltyProgram(passenger2.getPassportNumber(), new VIPPassenger());

        // Add reservations
        reservationSystem.addReservation(flight1.getFlightNumber(), passenger1);
        reservationSystem.addReservation(flight2.getFlightNumber(), passenger2);

        // Create a list of flights
        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Save reservations to CSV
        CSVUtil.saveReservationsToCSV("data/reservations.csv", reservationSystem, flights);
        System.out.println("Reservations saved to CSV.");

        // Load reservations from CSV
        ReservationSystem loadedSystem = CSVUtil.loadReservationsFromCSV("data/reservations.csv");

        // Display passengers per flight
        for (Flight flight : flights) {
            System.out.println("Flight " + flight.getFlightNumber() + ":");
            List<Passenger> passengers = loadedSystem.getPassengersForFlight(flight.getFlightNumber());
            for (Passenger p : passengers) {
                System.out.println(" - " + p.getName() + " (" + p.getPassportNumber() + ")");
            }
            System.out.println();
        }

        // Show loyalty discounts
        BigDecimal price1 = flight1.getTicketPrice();
        BigDecimal finalPrice1 = loadedSystem.getDiscountedPrice(passenger1.getPassportNumber(), price1);
        System.out.println(passenger1.getName() + "'s final price: $" + finalPrice1);

        BigDecimal price2 = flight2.getTicketPrice();
        BigDecimal finalPrice2 = loadedSystem.getDiscountedPrice(passenger2.getPassportNumber(), price2);
        System.out.println(passenger2.getName() + "'s final price (VIP): $" + finalPrice2);
    }
}
