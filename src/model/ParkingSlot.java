/**
 * 
 */
package model;

import enums.VehicleType;

/**
 * 
 */
public class ParkingSlot {
	private String slotId;
	private VehicleType vehicleType;
	private boolean isOccupied;
	/**
	 * @param slotId
	 * @param vehicleType
	 * @param isOccupied
	 */
	public ParkingSlot(String slotId, VehicleType vehicleType, boolean isOccupied) {
		super();
		this.slotId = slotId;
		this.vehicleType = vehicleType;
		this.isOccupied = isOccupied;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
}
