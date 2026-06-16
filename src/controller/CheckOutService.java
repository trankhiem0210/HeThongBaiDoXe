package controller;

import java.time.LocalDateTime;
import model.ParkingSlot;
import model.ParkingTicket;
import model.PricingPolicy;
import model.HourlyPricingStrategy;
import model.SubscriptionCard;
import repository.DataStorage;

public class CheckOutService {
	private final DataStorage dataStorage;
	private final PricingPolicy pricingPolicy;

	public CheckOutService() {
		this.dataStorage = DataStorage.getInstance();
		this.pricingPolicy = new PricingPolicy(new HourlyPricingStrategy());
	}

	public String performCheckOut(String slotId, String ticketId) {
		// Kiem tra vi tri do
		ParkingSlot foundSlot = null;
		for (ParkingSlot slot : dataStorage.getParkingSlots()) {
			if (slot.getSlotId().equalsIgnoreCase(slotId)) {
				foundSlot = slot; break;
			}
		}
		if (foundSlot == null || !foundSlot.isOccupied()) {
			return "Lỗi: Không tìm thấy vị trí đỗ hoặc vị trí đang trống!";
		}

		// Kiem tra ve xe
		ParkingTicket foundTicket = null;
		for (ParkingTicket ticket : dataStorage.getParkingTickets()) {
			if (ticket.getTicketId().equalsIgnoreCase(ticketId)) {
				foundTicket = ticket; break;
			}
		}
		if (foundTicket == null) {
			return "Lỗi: Không tìm thấy vé xe trên hệ thống!";
		}

		// Kiem tra the thang
		boolean hasValidSubscription = false;
		for (SubscriptionCard card : dataStorage.getSubscriptionCards()) {
			if (card.getPlateNumber().equalsIgnoreCase(foundTicket.getPlateNumber()) && card.isValid()) {
				hasValidSubscription = true; break;
			}
		}

		// Gio ra va tinh tien
		foundTicket.setExitTime(LocalDateTime.now());
		double totalFee = pricingPolicy.calculateFee(foundTicket, hasValidSubscription);
		
		foundTicket.setTotalFee(totalFee);
		foundTicket.setCheckout(true); 

		// Cap nhat lai kho du lieu
		dataStorage.getParkingTickets().remove(foundTicket);
		dataStorage.getParkingTicketsHistory().add(foundTicket);
		foundSlot.setOccupied(false);

		return "Check-out thành công! Số tiền thanh toán: " + totalFee + " VNĐ";
	}
}
