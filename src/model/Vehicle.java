package model;

import enums.VehicleType;

public class Vehicle {
    private String plateNumber;
    private VehicleType type;
    private MonthlyCard monthlyCard; // Optional, only for vehicles with monthly cards

    public Vehicle(String plateNumber, VehicleType type) {
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public VehicleType getType() {
        return type;
    }

}
