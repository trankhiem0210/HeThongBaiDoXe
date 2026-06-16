package model;

import java.time.temporal.ChronoUnit;
import enums.VehicleType;

public class HourlyPricingStrategy implements PricingStrategy {
	public static final double HOURLY_RATE_BICYCLE = 2000.0;   // Xe dap
	public static final double HOURLY_RATE_MOTORBIKE = 5000.0; // Xe may
	public static final double HOURLY_RATE_CAR = 20000.0;      // Xe o to
	
	public static final long MININUM_CHARGE_HOURS = 1; // Gio do xe toi thieu

	@Override
	public double calculateFee(ParkingTicket ticket, boolean hasValidSubscription) {
		// Neu the thang con han -> 0 dong
		if (hasValidSubscription) {
			return 0.0;
		}

		if (ticket.getEntryTime() == null || ticket.getExitTime() == null) {
			return 0.0;
		}

		// Neu khach vang lai so gio lam tron muc toi thieu
		long hours = ChronoUnit.HOURS.between(ticket.getEntryTime(), ticket.getExitTime());
		if (hours < MININUM_CHARGE_HOURS) {
			hours = MININUM_CHARGE_HOURS; 
		}

		// Phan loai phuong tien ap dung gia
		double currentRate = 0.0;
		VehicleType type = ticket.getVehicleType(); 
		
		if (type == VehicleType.CAR) {
			currentRate = HOURLY_RATE_CAR;
		} else if (type == VehicleType.MOTORBIKE) {
			currentRate = HOURLY_RATE_MOTORBIKE;
		} else if (type == VehicleType.BICYCLE) {
			currentRate = HOURLY_RATE_BICYCLE;
		}

		// Tra ve tong tien
		return hours * currentRate;
	}
}