/**
 * 
 */
package model;

import enums.VehicleType;

/**
 * 
 */
public class ParkingArea {
	private String areaId;
	private String areaName;
	private VehicleType vehicleType;
	/**
	 * @param areaId
	 * @param areaName
	 * @param vehicleType
	 */
	public ParkingArea(String areaId, String areaName, VehicleType vehicleType) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.vehicleType = vehicleType;
	}
	String getAreaId() {
		return areaId;
	}
	void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	String getAreaName() {
		return areaName;
	}
	void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	VehicleType getVehicleType() {
		return vehicleType;
	}
	void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
}
