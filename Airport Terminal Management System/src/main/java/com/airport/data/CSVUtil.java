package com.airport.data;

import com.airport.domain.model.*;
import com.airport.domain.loyalty.*;
import com.airport.domain.reservation.ReservationSystem;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class CSVUtil {

    // Save reservations and loyalty info to CSV
    public static void saveReservationsToCSV(String filePath, ReservationSystem reservationSystem, List<Flight> flights) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Flight flight : flights) {
                String flightNumber = flight.getFlightNumber();
                List<Passenger> passengers = reservationSystem.getPassengersForFlight(flightNumber);
                for (Passenger p : passengers) {
                    String loyalty = "None";
                    BigDecimal price = flight.getTicketPrice();

                    // Determine loyalty type (optional)
                    BigDecimal discounted = reservationSystem.getDiscountedPrice(p.getPassportNumber(), price);
                    if (discounted.compareTo(price) < 0) {
                        loyalty = "VIP";
                    } else {
                        loyalty = "Regular";
                    }

                    String line = String.join(",",
                            flight.getFlightNumber(),
                            flight.getDepartureDate().toString(),
                            flight.getTicketPrice().toString(),
                            p.getName(),
                            p.getPassportNumber(),
                            flight.getAircraft().getModel(),
                            (flight.getAircraft() instanceof CommercialAircraft ? "Commercial" : "PrivateJet"),
                            loyalty
                    );
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + filePath);
            e.printStackTrace();
        }
    }

    // Load reservations and loyalty info from CSV
    public static ReservationSystem loadReservationsFromCSV(String filePath) {
        ReservationSystem reservationSystem = new ReservationSystem();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<String, Flight> flightCache = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 8) continue; // Ensure all columns exist

                String flightNumber = parts[0];
                LocalDate departureDate = LocalDate.parse(parts[1]);
                BigDecimal ticketPrice = new BigDecimal(parts[2]);
                String passengerName = parts[3];
                String passportNumber = parts[4];
                String aircraftModel = parts[5];
                String aircraftType = parts[6];
                String loyaltyType = parts[7];

                Aircraft aircraft;
                if (aircraftType.equalsIgnoreCase("Commercial")) {
                    aircraft = new CommercialAircraft(aircraftModel, 150, 26000, "Unknown Airline");
                } else {
                    aircraft = new PrivateJet(aircraftModel, 12, 15000, true, 900);
                }

                // Use cached Flight object if available
                Flight flight = flightCache.get(flightNumber);
                if (flight == null) {
                    flight = new Flight(flightNumber, departureDate, ticketPrice, aircraft);
                    flightCache.put(flightNumber, flight);
                }

                Passenger passenger = new Passenger(passengerName, passportNumber);
                reservationSystem.addReservation(flightNumber, passenger);

                // Set loyalty program based on column
                if (loyaltyType.equalsIgnoreCase("VIP")) {
                    reservationSystem.setLoyaltyProgram(passportNumber, new VIPPassenger());
                } else if (loyaltyType.equalsIgnoreCase("Regular")) {
                    reservationSystem.setLoyaltyProgram(passportNumber, new RegularPassenger());
                }
            }

        } catch (IOException e) {
            System.err.println("Error loading reservations from CSV: " + filePath);
            e.printStackTrace();
        }

        return reservationSystem;
    }
}
