package com.airport;

import com.airport.domain.model.*;
import com.airport.domain.reservation.ReservationSystem;
import com.airport.data.CSVUtil;

import org.junit.jupiter.api.*;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTerminalTest {

    private ReservationSystem reservationSystem;
    private Map<String, Flight> flightMap;

    @BeforeEach
    public void setup() {
        reservationSystem = new ReservationSystem();
        flightMap = new HashMap<>();

        Aircraft aircraft = new CommercialAircraft("Boeing 737", 180, 26000, "Delta");
        Flight flight = new Flight("AA101", LocalDate.of(2024, 8, 15), new BigDecimal("299.99"), aircraft);
        flightMap.put(flight.getFlightNumber(), flight);

        Passenger passenger = new Passenger("Alice Smith", "P12345");
        reservationSystem.addReservation(flight.getFlightNumber(), passenger);
    }

    @Test
    public void testAddReservation() {
        List<Passenger> passengers = reservationSystem.getPassengersForFlight("AA101");
        assertEquals(1, passengers.size());
        assertEquals("Alice Smith", passengers.get(0).getName());
    }

    @Test
    public void testGetPassengersForFlight() {
        List<Passenger> passengers = reservationSystem.getPassengersForFlight("AA101");
        assertFalse(passengers.isEmpty());
        assertEquals("P12345", passengers.get(0).getPassportNumber());
    }

    @Test
    public void testSaveAndLoadCSV() {
        String tempFile = "data/test_reservations.csv";

        // Save
        CSVUtil.saveReservationsToCSV(reservationSystem, flightMap);

        // Load
        ReservationSystem loadedSystem = new ReservationSystem();
        CSVUtil.loadReservationsFromCSV(loadedSystem, "data/reservations.csv");

        assertFalse(loadedSystem.getPassengersForFlight("AA101").isEmpty());
        assertEquals("Alice Smith", loadedSystem.getPassengersForFlight("AA101").get(0).getName());
    }

    @Test
    public void testAircraftTypeDetection() {
        CommercialAircraft commercial = new CommercialAircraft("Airbus A320", 160, 24000, "American Airlines");
        assertEquals("Commercial", commercial.getAircraftType());

        PrivateJet jet = new PrivateJet("Gulfstream G650", 10, 12000, true, 900);
        assertEquals("PrivateJet", jet.getAircraftType());
    }

    @AfterEach
    public void cleanup() {
        // Optional: Clean test file if needed
        File file = new File("data/test_reservations.csv");
        if (file.exists()) {
            file.delete();
        }
    }
}
