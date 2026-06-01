package model;

import java.time.LocalDateTime;

import enums.VehicleType;

public class ParkingSession {
    private String sessionId;
    private String ticketId;
    private String plateNumber;
    private VehicleType vehicleType;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private SessionStatus status;
    private Ticket ticket; // Reference to the associated ticket
}
