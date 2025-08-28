package com.airport.view;

import com.airport.data.CSVUtil;
import com.airport.domain.model.*;
import com.airport.domain.reservation.ReservationSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


public class AirportTerminalApp {

    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        // Create aircraft
        CommercialAircraft ca = new CommercialAircraft("Boeing 737", 180, 26000, "American Airlines");
        PrivateJet pj = new PrivateJet("Gulfstream G650", 12, 15000, true, 900);

        // Create flights
        Flight flight1 = new Flight("AA101", LocalDate.of(2025, 8, 1), new BigDecimal("500.00"), ca);
        Flight flight2 = new Flight("PJ001", LocalDate.of(2025, 9, 15), new BigDecimal("5000.00"), pj);

        // Add flights to the system
        system.addFlight(flight1);
        system.addFlight(flight2);

        // Create passengers
        Passenger p1 = new Passenger("Alice Smith", "P12345");
        Passenger p2 = new Passenger("John Doe", "P67890");

        // Add reservations
        system.addReservation("AA101", p1);
        system.addReservation("PJ001", p2);

        // Save to CSV
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        CSVUtil.saveReservationsToCSV("data/reservations.csv", system, flights);

        System.out.println("Reservations saved to CSV.");

        // Display reservations
        for (String flightNum : system.getAllReservations().keySet()) {
            System.out.println("Flight " + flightNum + ":");
            for (Passenger p : system.getPassengersForFlight(flightNum)) {
                System.out.println(" - " + p.getName() + " (" + p.getPassportNumber() + ")");
            }
            System.out.println();
        }
    }
}
