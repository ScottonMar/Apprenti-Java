package com.airport.domain.reservation;

import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationSystem {
    private final Map<String, List<Passenger>> reservations = new HashMap<>();
    private final Map<String, Flight> flights = new HashMap<>();

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }

    public Flight getFlightByNumber(String flightNumber) {
        return flights.get(flightNumber);
    }

    public void addReservation(String flightNumber, Passenger passenger) {
        reservations.computeIfAbsent(flightNumber, k -> new ArrayList<>()).add(passenger);
    }

    public List<Passenger> getPassengersForFlight(String flightNumber) {
        return reservations.getOrDefault(flightNumber, new ArrayList<>());
    }

    public Map<String, List<Passenger>> getAllReservations() {
        return reservations;
    }

    public Map<String, Flight> getAllFlights() {
        return flights;
    }
}
