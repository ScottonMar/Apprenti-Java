package com.airport.data;

import com.airport.domain.model.*;
import com.airport.domain.reservation.ReservationSystem;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class CSVUtil {

    // Save reservations to a CSV file
    public static void saveReservationsToCSV(String filePath, ReservationSystem reservationSystem, List<Flight> flights) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Flight flight : flights) {
                List<Passenger> passengers = reservationSystem.getPassengersForFlight(flight.getFlightNumber());
                if (passengers != null) {
                    for (Passenger p : passengers) {
                        writer.write(String.format("%s,%s,%s,%s,%s,%s,%s",
                                flight.getFlightNumber(),
                                flight.getDepartureDate(),
                                flight.getTicketPrice(),
                                p.getName(),
                                p.getPassportNumber(),
                                flight.getAircraft().getModel(),
                                flight.getAircraft() instanceof CommercialAircraft ? "Commercial" : "PrivateJet"
                        ));
                        writer.newLine();
                    }
                }
            }
            System.out.println("Reservations saved to CSV.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // Load reservations from a CSV file
    public static void loadReservationsFromCSV(ReservationSystem reservationSystem, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<String, Flight> flightMap = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 7) continue;

                String flightNumber = parts[0];
                LocalDate date = LocalDate.parse(parts[1]);
                BigDecimal price = new BigDecimal(parts[2]);
                String passengerName = parts[3];
                String passport = parts[4];
                String model = parts[5];
                String type = parts[6];

                Aircraft aircraft;
                if (type.equalsIgnoreCase("Commercial")) {
                    aircraft = new CommercialAircraft(model, 180, 26000, "Loaded Airline");
                } else {
                    aircraft = new PrivateJet(model, 12, 15000, true, 800);
                }

                Flight flight;
                if (flightMap.containsKey(flightNumber)) {
                    flight = flightMap.get(flightNumber);
                } else {
                    flight = new Flight(flightNumber, date, price, aircraft);
                    reservationSystem.addFlight(flight);
                    flightMap.put(flightNumber, flight);
                }

                if (!passengerName.equalsIgnoreCase("None")) {
                    Passenger passenger = new Passenger(passengerName, passport);
                    reservationSystem.addReservation(flightNumber, passenger);
                }
            }

            System.out.println("Reservations loaded from CSV.");
        } catch (IOException e) {
            System.err.println("Error loading reservations from CSV: " + e.getMessage());
        }
    }
}
