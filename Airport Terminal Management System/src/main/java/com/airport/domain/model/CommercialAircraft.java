package com.airport.domain.model;

public class CommercialAircraft extends Aircraft {
    private final String airlineName;

    public CommercialAircraft(String model, int capacity, double fuelCapacity, String airlineName) {
        super(model, capacity, fuelCapacity);
        this.airlineName = airlineName;
    }

    public String getAirlineName() {
        return airlineName;
    }
}