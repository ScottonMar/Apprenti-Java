package com.airport.domain.model;

public class CommercialAircraft extends Aircraft {
    private String airlineName;

    public CommercialAircraft(String model, int capacity, double fuelCapacity, String airlineName) {
        super(model, capacity, fuelCapacity);
        this.airlineName = airlineName;
    }

    public String getAirlineName() {
        return airlineName;
    }

    @Override
    public String getAircraftType() {
        return "Commercial";
    }

    @Override
    public String toString() {
        return super.toString() + " - " + airlineName;
    }
}
