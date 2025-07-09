package com.airport.domain.model;

public abstract class Aircraft {
    private String model;
    private int capacity;
    private double fuelCapacity;

    public Aircraft(String model, int capacity, double fuelCapacity) {
        this.model = model;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public abstract String getAircraftType();

    @Override
    public String toString() {
        return model + " (" + getAircraftType() + ")";
    }
}
