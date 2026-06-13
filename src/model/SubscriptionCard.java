package model;

import java.time.LocalDate;

import enums.VehicleType;

public class SubscriptionCard {
	private String cardId;
	private String plateNumber;
	private String ownerName;
	private VehicleType vehicleType;
	private LocalDate expiryDate;

	/**
	 * @param cardId
	 * @param plateNumber
	 * @param ownerName
	 * @param vehicleType
	 * @param expiryDate
	 */
	public SubscriptionCard(String cardId, String plateNumber, String ownerName, VehicleType vehicleType,
			LocalDate expiryDate) {
		super();
		this.cardId = cardId;
		this.plateNumber = plateNumber;
		this.ownerName = ownerName;
		this.vehicleType = vehicleType;
		this.expiryDate = expiryDate;
	}
	/**
	 * Kiem tra the thang con han khong
	 * @return
	 */
	public boolean isValid() {
		if (expiryDate == null) return false ;
		return LocalDate.now().isBefore(expiryDate) || LocalDate.now().isEqual(expiryDate);
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
