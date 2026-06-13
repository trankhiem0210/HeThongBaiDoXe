/**
 * 
 */
package model;

import java.time.LocalDateTime;

import enums.VehicleType;

/**
 * 
 */
public class ParkingTicket {
	private String ticketId;
	private String plateNumber;
	private VehicleType vehicleType;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private double totalFee;
	private boolean isCheckout;
	/**
	 * @param ticketId
	 * @param plateNumber
	 * @param vehicleType
	 * @param entryTime
	 * @param exitTime
	 * @param totalFee
	 * @param isCheckout
	 */
	public ParkingTicket(String ticketId, String plateNumber, VehicleType vehicleType, LocalDateTime entryTime,
			LocalDateTime exitTime, double totalFee, boolean isCheckout) {
		super();
		this.ticketId = ticketId;
		this.plateNumber = plateNumber;
		this.vehicleType = vehicleType;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.totalFee = totalFee;
		this.isCheckout = isCheckout;
	}
	String getTicketId() {
		return ticketId;
	}
	void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	String getPlateNumber() {
		return plateNumber;
	}
	void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	VehicleType getVehicleType() {
		return vehicleType;
	}
	void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	LocalDateTime getEntryTime() {
		return entryTime;
	}
	void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}
	LocalDateTime getExitTime() {
		return exitTime;
	}
	void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}
	double getTotalFee() {
		return totalFee;
	}
	void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	boolean isCheckout() {
		return isCheckout;
	}
	void setCheckout(boolean isCheckout) {
		this.isCheckout = isCheckout;
	}
	
}
