package com.airport;

import com.airport.domain.model.*;
import com.airport.domain.reservation.ReservationSystem;
import com.airport.data.CSVUtil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AirportTerminalTest {

    @Test
    public void testSaveReservationsToCSV() {
        // Create Aircraft
        Aircraft commercialAircraft = new CommercialAircraft("Boeing 737", 180, 26000, "American Airlines");
        Aircraft privateJet = new PrivateJet("Gulfstream G650", 12, 15000, true, 900);

        // Create Flights
        Flight flight1 = new Flight("AA101", LocalDate.of(2025, 7, 15), new BigDecimal("350.00"), commercialAircraft);
        Flight flight2 = new Flight("PJ001", LocalDate.of(2025, 8, 5), new BigDecimal("4500.00"), privateJet);

        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        // Create Passengers
        Passenger passenger1 = new Passenger("Alice Smith", "P12345");
        Passenger passenger2 = new Passenger("Bob Jones", "P67890");

        // Reservation System
        ReservationSystem system = new ReservationSystem();
        system.addFlight(flight1);
        system.addFlight(flight2);
        system.addReservation("AA101", passenger1);
        system.addReservation("PJ001", passenger2);

        // Save to CSV
        CSVUtil.saveReservationsToCSV("data/reservations.csv", system, flights);

        // Basic checks
        List<Passenger> passengersAA101 = system.getPassengersForFlight("AA101");
        List<Passenger> passengersPJ001 = system.getPassengersForFlight("PJ001");

        assertNotNull(passengersAA101);
        assertNotNull(passengersPJ001);
        assertEquals(1, passengersAA101.size());
        assertEquals(1, passengersPJ001.size());
        assertEquals("Alice Smith", passengersAA101.get(0).getName());
        assertEquals("Bob Jones", passengersPJ001.get(0).getName());
    }
}
