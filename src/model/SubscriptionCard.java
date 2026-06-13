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
	String getCardId() {
		return cardId;
	}

	void setCardId(String cardId) {
		this.cardId = cardId;
	}

	String getPlateNumber() {
		return plateNumber;
	}

	void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	String getOwnerName() {
		return ownerName;
	}

	void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	VehicleType getVehicleType() {
		return vehicleType;
	}

	void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	LocalDate getExpiryDate() {
		return expiryDate;
	}

	void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

}
