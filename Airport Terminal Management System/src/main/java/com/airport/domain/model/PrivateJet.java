package com.airport.domain.model;

public class PrivateJet extends Aircraft {
    private boolean hasLuxuryService;
    private int maxSpeed;

    public PrivateJet(String model, int capacity, double fuelCapacity, boolean hasLuxuryService, int maxSpeed) {
        super(model, capacity, fuelCapacity);
        this.hasLuxuryService = hasLuxuryService;
        this.maxSpeed = maxSpeed;
    }

    public boolean hasLuxuryService() {
        return hasLuxuryService;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String getAircraftType() {
        return "PrivateJet";
    }

    @Override
    public String toString() {
        return super.toString() + " - Max Speed: " + maxSpeed + " km/h";
    }
}
