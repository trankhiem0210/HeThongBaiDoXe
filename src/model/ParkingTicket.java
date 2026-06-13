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
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}
	public LocalDateTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}
	public double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	public boolean isCheckout() {
		return isCheckout;
	}
	public void setCheckout(boolean isCheckout) {
		this.isCheckout = isCheckout;
	}
	
}
