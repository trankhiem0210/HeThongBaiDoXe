package controller;

import java.time.LocalDateTime;
import enums.VehicleType;
import model.ParkingSlot;
import model.ParkingTicket;
import repository.DataStorage;

public class CheckInService {
	private final DataStorage dataStorage;
	
	public CheckInService() {
		this.dataStorage = DataStorage.getInstance();
	}
	
	public boolean isValidPlateNumber(String plateNumber) {
		if (plateNumber == null || plateNumber.trim().isEmpty()) {
			return false;
		}
		return plateNumber.matches("^[A-Z]{3}-\\d{3}$");
	}
	
	public boolean isVehicleAlreadyParked(String plateNumber) {
		return dataStorage.getParkingTickets().stream()
				.anyMatch(ticket -> ticket.getPlateNumber().equalsIgnoreCase(plateNumber));
	}
	
	public boolean hasAvailableSlot(VehicleType vehicleType) {
		return dataStorage.getParkingSlots().stream()
				.filter(slot -> slot.getVehicleType() == vehicleType && !slot.isOccupied())
				.findFirst()
				.isPresent();
	}
	
	public ParkingSlot getAvailableSlot(VehicleType vehicleType) {
		return dataStorage.getParkingSlots().stream()
				.filter(slot -> slot.getVehicleType() == vehicleType && !slot.isOccupied())
				.findFirst()
				.orElse(null);
	}
	
	public int getAvailableSlotsCount(VehicleType vehicleType) {
		return (int) dataStorage.getParkingSlots().stream()
				.filter(slot -> slot.getVehicleType() == vehicleType && !slot.isOccupied())
				.count();
	}
	
	public int getTotalSlotsCount(VehicleType vehicleType) {
		return (int) dataStorage.getParkingSlots().stream()
				.filter(slot -> slot.getVehicleType() == vehicleType)
				.count();
	}
}
